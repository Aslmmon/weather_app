package com.floriaapp.core.domain.model.category


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val `data`: List<CategoryResponseItem>
)
data class CategoryResponseItem(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    var isChecked:Boolean = false
)