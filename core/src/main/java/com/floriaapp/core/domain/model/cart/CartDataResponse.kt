package com.floriaapp.core.domain.model.cart


import com.google.gson.annotations.SerializedName

data class CartDataResponse(
    @SerializedName("data")
    val `data`: List<CartDataItem>,
    @SerializedName("meta")
    val meta: Meta
)
data class CartDataItem(
    @SerializedName("products")
    val products: List<ProductCartItem>,
    @SerializedName("provider")
    val provider: Provider
)
data class Meta(
    @SerializedName("total")
    val total: Int,
    @SerializedName("cart_count")
    val cartCount:Int?
)

data class ProductCartItem(
    @SerializedName("cart_id")
    val cartId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("total")
    val total: Int,

)
data class Provider(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String
)