package com.floriaapp.core.domain.model.questions


import com.google.gson.annotations.SerializedName

data class ProductQuestions(
    @SerializedName("data")
    val `data`: List<ProductQuestionItem>?,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta
)