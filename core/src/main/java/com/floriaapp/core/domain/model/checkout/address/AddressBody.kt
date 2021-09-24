package com.floriaapp.core.domain.model.checkout.address

data class AddressBody(var address_type:String,
var street_name:String,var apartment_number:String,
var contact_name:String,var another_phone:String,
var special_marque:String,var latitude:Double,var longitude:Double)