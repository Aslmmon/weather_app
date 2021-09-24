package com.floriaapp.core.domain.model.orderSuccess


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: Meta
)