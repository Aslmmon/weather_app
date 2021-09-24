package com.floriaapp.core.domain.model.faq


import com.google.gson.annotations.SerializedName

data class FaqResponse(
    @SerializedName("data")
    val `data`: List<FaqData>
)