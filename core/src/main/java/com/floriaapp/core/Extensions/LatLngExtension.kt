package com.test.utils.Extensions

import android.content.Context
import android.graphics.Point
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.WindowManager


fun Context?.convertToAddress(latitude: Double, longtitude: Double): String? {
    try {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(latitude, longtitude, 1)
        val address = list.get(0)
        return address.getAddressLine(0) + "-" + address.locality + "-" + address.maxAddressLineIndex + "-" + address.countryName + "-" + address.featureName
    } catch (e: Exception) {
        Log.e("Gecodoer", e.message.toString())
        return null
    }
}

fun Context.getScreenWidth(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

//fun Context?.convertToLatLng(strAddress: String): LatLng? {
//    val coder = Geocoder(this);
//    val address: List<Address>
//    var desiredLatLng: LatLng? = null
//
//    try {
//        address = coder.getFromLocationName(strAddress, 1);
//        if (address == null) {
//            return null;
//        }
//        val location = address[0];
//        location.latitude;
//        location.longitude;
//        return LatLng(location.latitude, location.longitude)
//    } catch (e: Exception) {
//        Log.e("gecodeing", e.message.toString())
//    }
//    return null
//}