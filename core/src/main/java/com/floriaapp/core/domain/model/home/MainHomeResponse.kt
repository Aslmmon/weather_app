package com.floriaapp.core.domain.model.home


import com.google.gson.annotations.SerializedName

data class MainHomeResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("meta")
    val meta:MetaNotification
)

data class Data(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("sliders")
    var sliders: List<Slider>,
    @SerializedName("ads")
    var ads: List<ads>
)

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("in_cart")
    var inCart: Boolean,
    @SerializedName("is_favorited")
    var isFavorited:Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("rate")
    val rate: Int
)

data class Provider(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String
)

data class Category(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
data class MetaNotification(var unread_count:Int,var wallet_balance:Double)

data class Slider(var id: Int, var title: String, var content: String, var image: String,var model:String,var model_id:Int,var url:String)
data class ads(var id: Int, var image: String,var model:String,var model_id:Int,var url:String)