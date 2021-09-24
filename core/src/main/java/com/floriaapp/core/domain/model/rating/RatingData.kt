package com.floriaapp.core.domain.model.rating


import com.google.gson.annotations.SerializedName

data class RatingData(
    @SerializedName("data")
    val `data`: List<RatingDataItem>?,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta
)