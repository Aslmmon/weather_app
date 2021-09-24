package com.floriaapp.core.api

import com.floriaapp.core.domain.model.login.RegistrationResponse
import com.floriaapp.core.domain.model.login.SignInResponse
import com.floriaapp.core.domain.model.login.registerBody
import com.floriaapp.core.domain.model.success.SuccessMessage
import kotlinx.android.parcel.RawValue
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface loginApi {

    @POST("/api/v1/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("dial_code") dialCode: String, @Field("phone") phone: String,
        @Field("password") password: String
    ): SignInResponse

    @Multipart
    @POST("/api/v1/register")
    suspend fun register(
        @Part("first_name") first_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: String?,
        @Part("password_confirmation") password_confirmation: String?,
        @Part("country_id") country_id: Int,
        @Part("nationality") nationality: RequestBody,
        @Part("terms") terms: Int = 1,
        @Part("dial_code") dial_code: String = "+966",
        @Part avatar: MultipartBody.Part? = null
    ): RegistrationResponse


    @Multipart
    @POST("/api/v1/profile")
    suspend fun updateProfile(
        @Part("first_name") first_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("country_id") country_id: Int,
        @Part("nationality") nationality: RequestBody,
        @Part("_method")method: RequestBody = RequestBody.create(MediaType.get("text/plain"), "PUT"),

        @Part avatar: MultipartBody.Part? = null
    ): RegistrationResponse

    @Multipart
    @POST("/api/v1/register/validate-first-step")
    suspend fun registerFirstStep(
        @Part("first_name") first_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: String?,
        @Part("password_confirmation") password_confirmation: String?,
        @Part("terms") terms: Int = 1,
        @Part("dial_code") dial_code: String = "+966"
    ): RegistrationResponse


    @POST("/api/v1/verify-phone")
    @FormUrlEncoded
    suspend fun verifyPhone(@Field("code") code: String): SuccessMessage

    @POST("/api/v1/resend-verification-code")
    suspend fun resendVerificationCode(): SuccessMessage


    @POST("/api/v1/forgot-password")
    @FormUrlEncoded
    suspend fun forgetPassword(@Field("phone") phone: String): SuccessMessage


    @POST("/api/v1/confirm-reset-password")
    @FormUrlEncoded
    suspend fun confirmResetPassword(
        @Field("phone") phone: String,
        @Field("code") code: String
    ): SuccessMessage


    @POST("/api/v1/reset-password")
    @FormUrlEncoded
    suspend fun resetPassword(
        @Field("phone") phone: String,
        @Field("code") code: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): SuccessMessage



    @POST("/api/v1/change-password")
    @FormUrlEncoded
    suspend fun changePassword(
        @Field("current_password") currentPassword: String,
        @Field("new_password") newPassword: String,
        @Field("new_password_confirmation") newPassConfirmation: String

    ): SignInResponse


}