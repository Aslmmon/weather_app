package com.floriaapp.core.domain.model.checkout.order.order_details


import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)