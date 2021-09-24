package com.floriaapp.core.domain.model.questions


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductQuestionItem(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("created_at")
    val createdAt: CreatedAt,
    @SerializedName("id")
    val id: Int,
    @SerializedName("product")
    val product: Product,
    @SerializedName("question")
    val question: String,
    @SerializedName("status")
    val status: String,
    var isExpanded:Boolean=true
):Parcelable