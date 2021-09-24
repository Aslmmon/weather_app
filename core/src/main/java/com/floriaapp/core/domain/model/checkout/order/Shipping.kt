package com.floriaapp.core.domain.model.checkout.order


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("name")
    val name: String
)