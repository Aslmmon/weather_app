package com.floriaapp.core.domain.model.Error


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("phone")
    val phone: List<String>? = null,
    @SerializedName("password")
    val password: List<String>? = null,
    @SerializedName("email")
    val email: List<String>? = null,
    @SerializedName("code")
    val code: List<String>? = null,
    @SerializedName("avatar")
    val avatar: List<String>? = null,
    @SerializedName("dial_code")
    val dial_code: List<String>? = null,
    @SerializedName("apartment_number")
    val apartment_number: List<String>? = null,
    @SerializedName("another_phone")
    val another_phone: List<String>? = null,
    @SerializedName("message")
    val message: List<String>? = null,
    @SerializedName("new_password")
    val newPasswordError:List<String>?=null,
    @SerializedName("promo_code")
    val promoCode:List<String>?=null,
    @SerializedName("notes")
    val notes:List<String>?=null,
    @SerializedName("shipping_notes")
    val shipping_notes:List<String>?=null)

