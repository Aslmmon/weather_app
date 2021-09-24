package com.floriaapp.core.domain.model.success


import com.google.gson.annotations.SerializedName

data class SuccessMessage(
    @SerializedName("message")
    val message: String,
    @SerializedName("cart_count")
    val cartCount:Int?=null,
    @SerializedName("meta")
    var meta :Meta?=null
)
data class Meta(var order_id:Int)