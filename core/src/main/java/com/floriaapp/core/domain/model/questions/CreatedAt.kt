package com.floriaapp.core.domain.model.questions


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreatedAt(
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String
):Parcelable