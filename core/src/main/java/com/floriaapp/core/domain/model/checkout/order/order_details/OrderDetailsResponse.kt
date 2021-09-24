package com.floriaapp.core.domain.model.checkout.order.order_details


import com.google.gson.annotations.SerializedName

data class OrderDetailsResponse(
    @SerializedName("data")
    val `data`: OrderDetailsResponseItem
)