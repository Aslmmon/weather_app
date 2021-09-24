package com.test.utils.Common.network

import android.content.SharedPreferences
import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.utils.*
import com.test.utils.Common.di.getSharedPrefrences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
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

    val token = getSharedPrefrences(androidApplication = App.getAppContext()).getString(TOKEN_USER, "")
    val language = getSharedPrefrences(androidApplication = App.getAppContext()).getString(LANGUAGE_PREFRENCE, ARABIC)

    Log.i("language","lanugage in app is $language")

    chain.proceed(
            chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer $token")
                    .addHeader("Accept-Language", language.toString())
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