package com.floriaapp.core.domain.model.abou


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("Content-Type")
    val contentType: String
)