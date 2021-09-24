package com.floriaapp.core.domain.model.login


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class SignInResponse(
    @SerializedName("data")
    val signInResponseItem: SignInResponseItem,
    @SerializedName("meta")
    val meta: Meta
)
data class Meta(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String
)
data class Nationality(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val value: String
)
data class Country(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
data class SignInResponseItem(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("country")
    val country: Country,
    @SerializedName("dial_code")
    val dialCode: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("nationality")
    val nationality: Nationality,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("verified")
    val verified: Boolean,
    @SerializedName("wallet_balance")
    val walletBalance:Double
)

@Parcelize
data class registerBody(
    var first_name:@RawValue RequestBody?, var last_name:@RawValue RequestBody?, var gender: @RawValue RequestBody?,
    var dial_code:String?="+966",
    var phone:@RawValue RequestBody?, var email:@RawValue RequestBody?, var password:String?=null,
    var password_confirmation:String?=null, var country_id:Int?, var nationality:@RawValue RequestBody?,
    var terms:Int=1, var avatar: @RawValue MultipartBody.Part?
):Parcelable

@Parcelize
data class registerBodyString(
    var first_name:String, var last_name:String, var gender: String?,
    var dial_code:String?="+966",
    var phone:String, var email:String, var password:String,
    var password_confirmation:String, var country_id:Int?, var nationality:String?,
    var terms:Int=1, var avatar: String?
):Parcelable