package com.test.utils.custom

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.floriaapp.core.domain.model.checkout.order.order_details.OrderDetailsResponseItem
import com.floriaapp.core.domain.model.checkout.shipping.new.ShippinCostNeeded
import com.floriaapp.core.domain.model.checkout.shipping.new.Shipping
import com.floriaapp.core.domain.model.summary.SummaryOrder
import com.google.android.material.button.MaterialButton
import com.test.utils.Bases.BaseActivity
import com.test.utils.Ext.showToast
import com.test.utils.R
import com.test.utils.TOTAL_ORDER_MONEY
import kotlin.math.roundToInt

class CustomOrderPrice(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    val next: MaterialButton

    init {
        inflate(context, R.layout.custom_order_price, this)
        next = findViewById(R.id.btn_next)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.order_price)
        val isOrder = attributes.getBoolean(R.styleable.order_price_isOrder, false)
        if (isOrder){
            findViewById<TextView>(R.id.tv_orderTitle).text =resources.getString(R.string.money_needed)
            findViewById<TextView>(R.id.tv_promoCodeTitle).text =resources.getString(R.string.sha7n)
            findViewById<TextView>(R.id.tv_promoCodeTitle).setTextColor(resources.getColor(R.color.blackish93))
            findViewById<TextView>(R.id.tv_promoCodeTitle_value).setTextColor(resources.getColor(R.color.blackish93))

            findViewById<TextView>(R.id.tv_deliveryPriceTitle).text =resources.getString(R.string.discount)
            findViewById<TextView>(R.id.tv_deliveryPriceTitle).setTextColor(resources.getColor(R.color.teaBlue))
            findViewById<TextView>(R.id.tv_delivery_value).setTextColor(resources.getColor(R.color.teaBlue))
            next.visibility  = View.GONE

        }

    }
    @SuppressLint("SetTextI18n")
    fun setDataOrder(data: OrderDetailsResponseItem) {
        findViewById<TextView>(R.id.tv_orderPrice_value).text = data.subtotal.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_promoCodeTitle_value).text = data.shippingFees.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_delivery_value).text = data.discount.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_totalMoneyValue).text = data.total.toString() + " " + resources.getString(R.string.egp)

        findViewById<TextView>(R.id.tv_tax_value).text = data.totalTax.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_tax_value).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tv_tax).visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun setData(
        shippingCostResponse: ShippinCostNeeded,
        shippingSlug: Shipping,
        sharedPreferences: SharedPreferences.Editor?=null
    ) {
        var subtTotalNeeded = 0
        var delivery = 0

        shippingCostResponse.shipping.forEach {
            if (it.slug == shippingSlug.slug) {
                if (it.slug == "barq") subtTotalNeeded = shippingCostResponse.total.barq.roundToInt()
                else subtTotalNeeded = shippingCostResponse.total.streetline.roundToInt()
                delivery = it.fees.roundToInt()
            }
        }
        findViewById<TextView>(R.id.tv_orderPrice_value).text =
            shippingCostResponse.subtotal.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_promoCodeTitle_value).text = 0.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_tax_value).text = 0.toString() + " " + resources.getString(R.string.egp)

        findViewById<TextView>(R.id.tv_delivery_value).text = delivery.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_totalMoneyValue).text =
            subtTotalNeeded.toString() + " " + resources.getString(R.string.egp)
        sharedPreferences?.putInt(TOTAL_ORDER_MONEY,subtTotalNeeded)?.commit()



    }


    @SuppressLint("SetTextI18n")
    fun setDataAfterPromoCode(
        shippingCostResponse: SummaryOrder,
        sharePrefrence: SharedPreferences.Editor
    ) {
//        var subtTotalNeeded = 0
//        var delivery = 0

        findViewById<TextView>(R.id.tv_orderPrice_value).text = shippingCostResponse.subtotal.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_promoCodeTitle_value).text = shippingCostResponse.discount.toString() + " " + resources.getString(R.string.egp)

        findViewById<TextView>(R.id.tv_delivery_value).text = shippingCostResponse.shippingFees.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_tax_value).text = shippingCostResponse.totalTax.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_tax_value).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tv_tax).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tv_totalMoneyValue).text = shippingCostResponse.total.toString() + " " + resources.getString(R.string.egp)
        sharePrefrence.putInt(TOTAL_ORDER_MONEY,shippingCostResponse.total.toInt())?.commit()


    }

    fun getTotalDataValue(): String {
        val digits =  findViewById<TextView>(R.id.tv_totalMoneyValue).text
        val s2 = digits.replace("[^0-9]".toRegex(), "")
        return s2
    }

    @SuppressLint("SetTextI18n")
    fun setDataToTotalValues(orderPrice: Int) {
        findViewById<TextView>(R.id.tv_totalMoneyValue).text =
            orderPrice.toString() + " " + resources.getString(R.string.egp)
        findViewById<TextView>(R.id.tv_orderPrice_value).text =
            orderPrice.toString() + " " + resources.getString(R.string.egp)


    }

    fun removeVisiblityofViews() {
        findViewById<Group>(R.id.group).visibility = View.GONE
    }

    fun showVisibilty() {
        findViewById<Group>(R.id.group).visibility = View.VISIBLE
    }


}