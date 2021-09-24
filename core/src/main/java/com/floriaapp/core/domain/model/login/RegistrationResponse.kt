package com.floriaapp.core.domain.model.login


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("data")
    val registratedUser: registratedUser,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("message")
    val message: String
)
data class registratedUser(
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
