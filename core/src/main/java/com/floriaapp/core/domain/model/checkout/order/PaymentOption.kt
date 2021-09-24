package com.floriaapp.core.domain.model.checkout.order


import com.google.gson.annotations.SerializedName

data class PaymentOption(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key:String,
    @SerializedName("status")
    val status:String,
)

data class PaymentOptions(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)