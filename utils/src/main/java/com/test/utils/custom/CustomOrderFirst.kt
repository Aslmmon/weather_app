package com.test.utils.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.floriaapp.core.domain.model.checkout.order.order_details.OrderDetailsResponse
import com.test.utils.AUTO_CANCELLED
import com.test.utils.CUSTOMER_CANCELLED
import com.test.utils.R
import com.test.utils.SHOP_CANCELLD

class CustomOrderFirst(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.custom_order_first, this)
    }


    @SuppressLint("SetTextI18n")
    fun setStepDone(orderTracking: OrderDetailsResponse) {

        val orderData = orderTracking.data.orderTracking.get(orderTracking.data.orderTracking.size -1)
        findViewById<TextView>(R.id.tv_date_first_ball).text = orderTracking.data.orderTracking.get(0).created_at.date
        findViewById<TextView>(R.id.tv_first_ball).text = resources.getString(R.string.done_order)
        findViewById<ImageView>(R.id.first_ball).backgroundTintList = ColorStateList.valueOf(
            context.resources.getColor(R.color.teaBlue)
        )
        findViewById<View>(R.id.first_view).backgroundTintList =
            ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))



        findViewById<TextView>(R.id.tv_second_ball).text = orderData.order_status
        findViewById<TextView>(R.id.tv_date_second_ball).text = orderData.created_at.date

        when(orderData.id){
            AUTO_CANCELLED -> {
                colorizeCancelledStatus()

            }
            CUSTOMER_CANCELLED -> {
                colorizeCancelledStatus()

            }
            SHOP_CANCELLD -> {
                colorizeCancelledStatus()
            }

            else ->   findViewById<ImageView>(R.id.second_ball).backgroundTintList = ColorStateList.valueOf(
                context.resources.getColor(R.color.teaBlue)
            )
        }
//        findViewById<ImageView>(R.id.second_ball).backgroundTintList = ColorStateList.valueOf(
//            context.resources.getColor(R.color.teaBlue)
//        )

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun colorizeCancelledStatus() {
        findViewById<ImageView>(R.id.second_ball).backgroundTintList = ColorStateList.valueOf(
            context.resources.getColor(R.color.red_color)
        )
        findViewById<ImageView>(R.id.second_ball).setImageDrawable(resources.getDrawable(R.drawable.ic_shape_error))
        findViewById<TextView>(R.id.tv_second_ball).setTextColor(resources.getColor(R.color.red_color))
        findViewById<TextView>(R.id.tv_date_second_ball).setTextColor(resources.getColor(R.color.red_color))
    }

}

