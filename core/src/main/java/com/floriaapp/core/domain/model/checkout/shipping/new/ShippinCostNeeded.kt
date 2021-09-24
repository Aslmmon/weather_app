package com.floriaapp.core.domain.model.checkout.shipping.new


import com.google.gson.annotations.SerializedName

data class ShippinCostNeeded(
    @SerializedName("shipping")
    val shipping: List<Shipping>,
    @SerializedName("subtotal")
    val subtotal: Int,
    @SerializedName("total")
    val total: Total
)