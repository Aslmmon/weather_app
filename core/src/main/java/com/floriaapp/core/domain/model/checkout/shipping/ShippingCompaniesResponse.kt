package com.floriaapp.core.domain.model.checkout.shipping


import com.google.gson.annotations.SerializedName

data class ShippingCompaniesResponse(
    @SerializedName("data")
    val `data`: List<ShippingCompanyItem>
)
data class ShippingCompanyItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)