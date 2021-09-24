package com.floriaapp.core.domain.model.checkout.order.order_details


import com.floriaapp.core.domain.model.checkout.order.PaymentOptions
import com.google.gson.annotations.SerializedName

data class OrderDetailsResponseItem(
    @SerializedName("address")
    val address: Address,
    @SerializedName("created_at")
    val createdAt: createdAt,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("order_id")
    val orderID:Int,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("order_status")
    val orderStatus: OrderStatus,
    @SerializedName("formatted_address")
    val formattedAddress:String,
    @SerializedName("payment_option")
    val paymentOption: PaymentOption,
    @SerializedName("payment_options")
    val paymentOptions: List<PaymentOptions>,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("has_rating")
    val hasRating:Boolean,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("required_at")
    val requiredAt: createdAt,
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
    val totalTax: Double,
    @SerializedName("order_trackings")
    val orderTracking: List<orderTracking>,
    @SerializedName("bank_transfer_attachments")
    val bankTransfer:List<BankTransfer>
)
data class createdAt(var date:String,var time:String)
data class orderTracking(var id:Int,var order_status:String,var created_at: createdAt)
data class BankTransfer(var status:String,var attachment:String)