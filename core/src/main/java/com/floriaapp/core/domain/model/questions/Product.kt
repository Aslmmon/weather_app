package com.floriaapp.core.domain.model.questions


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("in_cart")
    val inCart: Boolean,
    @SerializedName("is_favorited")
    val isFavorited: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("weight")
    val weight: Int
):Parcelable