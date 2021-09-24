package com.floriaapp.core.domain.model.abou


import com.google.gson.annotations.SerializedName

data class AboutResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("meta")
    val meta: Meta
)