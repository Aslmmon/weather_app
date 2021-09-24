package com.floriaapp.core.domain.model.orderSuccess


import com.google.gson.annotations.SerializedName

data class OrderSuccess(
    @SerializedName("data")
    val `data`: Data
)