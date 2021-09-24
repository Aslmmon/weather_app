package com.floriaapp.core.domain.model.provider


import com.google.gson.annotations.SerializedName

data class Provider(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String
)