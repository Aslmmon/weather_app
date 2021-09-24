package com.floriaapp.core.domain.model.provider


import com.floriaapp.core.domain.model.category.categoryProductItem
import com.google.gson.annotations.SerializedName

data class ProviderDetailsResponse(
    @SerializedName("data")
    val `data`: List<categoryProductItem>?,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("provider")
    val provider: ProviderX
)