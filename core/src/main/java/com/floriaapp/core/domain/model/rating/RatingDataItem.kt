package com.floriaapp.core.domain.model.rating


import com.google.gson.annotations.SerializedName

data class RatingDataItem(
    @SerializedName("created_at")
    val createdAt: CreatedAt,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("reply")
    val reply: String,
    @SerializedName("shipping_notes")
    val shippingNotes: String,
    @SerializedName("shipping_rate")
    val shippingRate: Int,
    @SerializedName("updated_at")
    val updatedAt: UpdatedAt,
    var isExpanded:Boolean = false
)