package com.floriaapp.core.domain.model.checkout.shipping


import com.google.gson.annotations.SerializedName

data class AddressesResponse(
    @SerializedName("data")
    val `data`: List<AddressItem>
)