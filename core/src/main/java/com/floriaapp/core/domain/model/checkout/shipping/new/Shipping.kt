package com.floriaapp.core.domain.model.checkout.shipping.new


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("fees")
    val fees: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: Int
)