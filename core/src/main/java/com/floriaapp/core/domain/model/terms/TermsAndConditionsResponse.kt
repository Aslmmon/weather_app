package com.floriaapp.core.domain.model.terms


import com.google.gson.annotations.SerializedName

data class TermsAndConditionsResponse(
    @SerializedName("data")
    val `data`: List<Data>
)