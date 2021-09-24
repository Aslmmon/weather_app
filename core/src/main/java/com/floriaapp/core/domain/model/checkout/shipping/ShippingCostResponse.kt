package com.floriaapp.core.domain.model.checkout.shipping


import com.google.gson.annotations.SerializedName

data class ShippingCostResponse(
    @SerializedName("shipping")
    val shipping: Int,
    @SerializedName("subtotal")
    val subtotal: Int,
    @SerializedName("total")
    val total: Int
)