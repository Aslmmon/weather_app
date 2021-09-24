package com.floriaapp.core.domain.model.rating


import com.google.gson.annotations.SerializedName

data class CreatedAt(
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String
)