package com.floriaapp.core.domain.model.tagItems


import com.google.gson.annotations.SerializedName

data class TagsProducts(
    @SerializedName("data")
    val tageItemResponse: TageItemResponse
)