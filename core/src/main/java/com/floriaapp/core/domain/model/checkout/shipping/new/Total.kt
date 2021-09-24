package com.floriaapp.core.domain.model.checkout.shipping.new


import com.google.gson.annotations.SerializedName

data class Total(
    @SerializedName("barq")
    val barq: Double,
    @SerializedName("streetline")
    val streetline: Double
)