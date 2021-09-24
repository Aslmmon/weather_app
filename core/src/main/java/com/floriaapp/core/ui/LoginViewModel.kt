package com.floriaapp.core.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.floriaapp.core.Extensions.launchDataLoad
import com.floriaapp.core.Extensions.toErrorBody
import com.floriaapp.core.api.loginApi
import com.floriaapp.core.domain.model.Error.ErrorResponse
import com.floriaapp.core.domain.model.login.RegistrationResponse
import com.floriaapp.core.domain.model.login.SignInResponse
import com.floriaapp.core.domain.model.login.registerBody
import com.floriaapp.core.domain.model.success.SuccessMessage
import com.google.gson.GsonBuilder
import retrofit2.HttpException

class LoginViewModel(var loginApi: loginApi) : ViewModel() {

    var _signInData = MutableLiveData<SignInResponse>()
    var SignInResponse: LiveData<SignInResponse> = _signInData

    var _registerResponse = MutableLiveData<RegistrationResponse>()
    var RegisterResponse: LiveData<RegistrationResponse> = _registerResponse


    var _verifyPhone = MutableLiveData<SuccessMessage>()
    var SuccessVerification: LiveData<SuccessMessage> = _verifyPhone


    var _verifyChangePassowrd = MutableLiveData<SuccessMessage>()
    var SuccessVerificationChangePassword: LiveData<SuccessMessage> = _verifyChangePassowrd



    var _ForgetPasswordResponse = MutableLiveData<SuccessMessage>()
    var ForgetPasswordResponse: LiveData<SuccessMessage> = _ForgetPasswordResponse


    var _SuccessSendingVerification = MutableLiveData<SuccessMessage>()
    var SuccessSendingVerification: LiveData<SuccessMessage> = _SuccessSendingVerification


    var _SuccessResetPassword = MutableLiveData<SuccessMessage>()
    var SuccessResetPassword: LiveData<SuccessMessage> = _SuccessResetPassword


    var _Error = MutableLiveData<String>()
    var Error: LiveData<String> = _Error


    var _ErrorConfirmResetPassword = MutableLiveData<String>()
    var ErrorConfirmResetPassword: LiveData<String> = _ErrorConfirmResetPassword


    var _ErrorLogin = MutableLiveData<ErrorResponse>()
    var ErrorLogin: LiveData<ErrorResponse> = _ErrorLogin

    fun signIn(dialCode:String,phone:String,password:String) {

        launchDataLoad(execution = {
            _signInData.postValue(loginApi.login(dialCode,phone,password))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            if (it is HttpException) {
                val errorBodyResponse = it.response()?.errorBody()?.string()
                val gson = GsonBuilder().create()
                val error = gson.fromJson(errorBodyResponse, ErrorResponse::class.java)
                _ErrorLogin.postValue(error)
            }
        })
    }

    fun register(registerBody: registerBody) {
        launchDataLoad(execution = {
            with(registerBody){
                _registerResponse.postValue(loginApi.register(first_name = first_name!!,last_name=last_name!!,
                    phone=phone!!,email = email!!,password = password,password_confirmation = password_confirmation,
                    country_id = country_id!!,nationality = nationality!!,terms = 1,avatar = avatar,gender = gender!!,
                ))

            }
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun updateProfile(registerBody: registerBody) {
        launchDataLoad(execution = {
            with(registerBody){
                _registerResponse.postValue(loginApi.updateProfile(first_name = first_name!!,last_name=last_name!!,
                    phone=phone!!,email = email!! ,country_id = country_id!!,nationality = nationality!!,avatar = avatar,gender = gender!!,
                ))

            }
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }
    fun registerFirstStep(registerBody: registerBody) {
        launchDataLoad(execution = {
            with(registerBody){
                _registerResponse.postValue(loginApi.registerFirstStep(first_name = first_name!!,last_name=last_name!!,
                    phone=phone!!,email = email!!,password = password,password_confirmation = password_confirmation, terms = 1
                ))

            } }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }




    fun verifyPhone(code: String) {
        launchDataLoad(execution = {
            _verifyPhone.postValue(loginApi.verifyPhone(code))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun confirmResetPassword(phone:String,code: String) {
        launchDataLoad(execution = {
            _verifyChangePassowrd.postValue(loginApi.confirmResetPassword(phone,code))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _ErrorConfirmResetPassword.postValue(it.toErrorBody())
        })
    }



    fun changePassowrd(oldPassword: String,newPassword:String,newConfirmPass:String) {
        launchDataLoad(execution = {
            _signInData.postValue(loginApi.changePassword(oldPassword,newPassword,newConfirmPass))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun resetPassword(phone: String,code:String,newPassword:String,confirmNewPassword:String) {
        launchDataLoad(execution = {
            _SuccessResetPassword.postValue(loginApi.resetPassword(phone,code,newPassword,confirmNewPassword))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun resendVerification() {
        launchDataLoad(execution = {
            _SuccessSendingVerification.postValue(loginApi.resendVerificationCode())
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun forgetPassword(phone:String) {
        launchDataLoad(execution = {
            _ForgetPasswordResponse.postValue(loginApi.forgetPassword(phone))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


}