package com.floriaapp.core.domain.model.checkout.order.order_details


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("name")
    val name: String
)