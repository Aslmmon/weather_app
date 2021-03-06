package com.test.utils.Common.network

import android.content.SharedPreferences
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.utils.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Aslm on 1/1/2020
 */

private val sLogLevel = HttpLoggingInterceptor.Level.BODY

private const val baseUrl = BuildConfig.BASE_URL


val loggingInterceptor = LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BODY)
        .log(Platform.INFO)
        .request("Request")
        .response("Response")
        .build()

private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

fun createNetworkClient() =
        retrofitClient(baseUrl)

private fun okHttpClient2() = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor())
       .addInterceptor(loggingInterceptor).build()

private fun retrofitClient(baseUrl: String): Retrofit =
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient2())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()


fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(
            chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build())
}


private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
        okHttpClientBuilder.apply {
            readTimeout(30L, TimeUnit.SECONDS)
            connectTimeout(30L, TimeUnit.SECONDS)
            writeTimeout(30L, TimeUnit.SECONDS)

        }

class SharedPrefrencesWrapper(private var sharedPrefrence: SharedPreferences) {

    fun saveString(key: String, value: String) {
        sharedPrefrence.edit().putString(key, value).apply()
    }

    fun getString(key: String, defValue: String = ""): String {
        return sharedPrefrence.getString(key, defValue)!!
    }

}