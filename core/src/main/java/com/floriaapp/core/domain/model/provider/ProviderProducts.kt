package com.floriaapp.core.domain.model.provider


import com.google.gson.annotations.SerializedName

data class ProviderProducts(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("in_cart")
    val inCart: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("rate")
    val rate: Int
)