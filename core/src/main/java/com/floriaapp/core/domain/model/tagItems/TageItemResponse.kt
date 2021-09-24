package com.floriaapp.core.domain.model.tagItems


import com.floriaapp.core.domain.model.category.categoryProductItem
import com.google.gson.annotations.SerializedName

data class TageItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("products")
    val products: List<categoryProductItem>?
)