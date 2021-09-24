package com.floriaapp.core.domain.model.faq


import com.google.gson.annotations.SerializedName

data class FaqData(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("question")
    val question: String,
    var isExpanded:Boolean = false
)