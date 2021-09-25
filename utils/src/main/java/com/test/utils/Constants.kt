package com.test.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri



const val API_OPENWATHER_KEY = "01ed0bf33f0d52e1bad309739903a79b"
const val CITIES_DATA = "cities"
const val MAXIMUM_CITIES= 4
const val CITY_JSON_FILENAME="city.json"


/**
 * login Data
 */
const val VERIFY_MOBILE = "verify_mobile"


const val LOGIN_CLASS_NAME = "com.homemade.login.LoginActivity"
const val SPLASH_CLASS_NAME = "com.homemade.user.SplashActivity"
const val FAVOURITES_CLASS_NAME = "com.homemade.main.menu.favourites.FavouriteActivity"
const val ORDERS_DETAILS_NAME = "com.homemade.orders.order_details.OrderDetailsActivity"
const val TERMS_AND_CONDITIONS = "com.homemade.main.menu.faq.FaqAndTermsActivity"

object NavigationUtils {
    fun goToDestination(ctx1: Context, ctx: Class<*>) {
        val intent = Intent(ctx1, ctx)
        ctx1.startActivity(intent)
        (ctx1 as Activity).finish()
    }

    fun goToDestination2(ctx1: Context, ctx: Class<*>) {
        val intent = Intent(ctx1, ctx)
        ctx1.startActivity(intent)
    }

    fun goToDestinationWithClearTasks(ctx1: Context, ctx: Class<*>) {
        val intent = Intent(ctx1, ctx)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ctx1.startActivity(intent)
    }

    fun getProductDetailsUri(idToPass: Int): Uri {

        return Uri.parse("app://productDetails/${idToPass}")
    }

    fun getCategoryProducts(idToPass: Int, categoryName: String): Uri {
        return Uri.parse("app://categoryProducts/${idToPass}/${categoryName}")
    }
}

