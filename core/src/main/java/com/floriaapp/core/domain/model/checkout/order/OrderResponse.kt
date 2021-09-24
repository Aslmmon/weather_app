package com.floriaapp.core.domain.model.checkout.order


import com.floriaapp.core.domain.model.checkout.order.order_details.createdAt
import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val `data`: List<OrderResponseItem>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta
)
data class OrderResponseItem(
    @SerializedName("address")
    val address: Address,
    @SerializedName("created_at")
    val createdAt: createdAt,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("order_status")
    val orderStatus: OrderStatus,
    @SerializedName("payment_option")
    val paymentOption: PaymentOption,
    @SerializedName("payment_options")
    val paymentOptions: List<PaymentOptions>,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("required_at")
    val requiredAt: createdAt?,
    @SerializedName("shipping")
    val shipping: Shipping,
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