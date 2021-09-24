package com.floriaapp.core.domain.model.abou


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("content")
    val content: String
)