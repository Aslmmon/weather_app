package com.floriaapp.core.domain.model.rating


import com.google.gson.annotations.SerializedName

data class Provider(
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String
)