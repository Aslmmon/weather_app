package com.floriaapp.core.domain.model.Error


import com.floriaapp.core.domain.model.summary.SummaryOrder
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: Errors? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("is_blocked")
    val isBlocked: Boolean?=null,
    @SerializedName("data")
    var dataSummary:SummaryOrder?=null
)
