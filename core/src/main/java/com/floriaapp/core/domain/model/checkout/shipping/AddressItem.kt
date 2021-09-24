package com.floriaapp.core.domain.model.checkout.shipping


import com.google.gson.annotations.SerializedName

data class AddressItem(
    @SerializedName("address_type")
    val addressType: String,
    @SerializedName("another_phone")
    val anotherPhone: String?,
    @SerializedName("apartment_number")
    val apartmentNumber: String,
    @SerializedName("city")
    val city: City,
    @SerializedName("contact_name")
    val contactName: String?,
    @SerializedName("country")
    val country: Country,
    @SerializedName("district")
    val district: District,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("region")
    val region: Region,
    @SerializedName("special_marque")
    val specialMarque: String?,
    @SerializedName("street_name")
    val streetName: String,
    var ischecked :Boolean =false
)