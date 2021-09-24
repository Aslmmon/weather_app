package com.floriaapp.core.domain.model.summary


import com.google.gson.annotations.SerializedName



data class SummaryOrderResponse(
    @SerializedName("data")
    var data:SummaryOrder
)
data class SummaryOrder(
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("shipping_fees")
    val shippingFees: Double,
    @SerializedName("subtotal")
    val subtotal: Double,
    @SerializedName("subtotal_after_discount")
    val subtotalAfterDiscount: Double,
    @SerializedName("total")
    val total: Double,
    @SerializedName("total_tax")
    val totalTax: Double
)