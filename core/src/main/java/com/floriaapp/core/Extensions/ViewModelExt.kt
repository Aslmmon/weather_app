package com.floriaapp.core.Extensions

import android.accounts.NetworkErrorException
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*


fun ViewModel.launchDataLoad(
    execution: suspend CoroutineScope.() -> Unit,
    errorReturned: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: (suspend CoroutineScope.() -> Unit)? = null
) {

    this.viewModelScope.launch {
        try {
            execution()
        } catch (e: Throwable) {
            errorReturned(e)
        } finally {
            finallyBlock?.invoke(this)
        }
    }
}

fun Context?.setLocale(langugeNeeded: String) {
    val locale = Locale(langugeNeeded)
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    this?.resources?.updateConfiguration(
        config,
        this.resources.displayMetrics
    )
}

fun Throwable?.toErrorBody(): String? {
    return when (this) {
        is SocketTimeoutException -> " Check Your Network Connection , Try Again later "
        is ConnectException -> " Check Your Network Connection , Try Again later "
        is UnknownHostException -> "Check Your Network Connection , Try Again later"
        is HttpException -> {
            return "error in Parsing "

        }
        is NetworkErrorException -> "Check Your Network Connection , Try Again later \""
        else -> this?.message.toString()
    }
}