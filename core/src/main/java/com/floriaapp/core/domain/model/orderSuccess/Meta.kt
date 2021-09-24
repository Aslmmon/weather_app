package com.floriaapp.core.domain.model.orderSuccess


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("order_id")
    val orderId: Int
)