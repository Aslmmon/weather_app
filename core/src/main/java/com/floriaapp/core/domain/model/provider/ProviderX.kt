package com.floriaapp.core.domain.model.provider


import com.google.gson.annotations.SerializedName

data class ProviderX(
    @SerializedName("banner")
    val banner: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("provider_type")
    val providerType: String,
    @SerializedName("rating")
    val rating: Int
)