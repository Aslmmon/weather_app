package com.test.utils.custom

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.floriaapp.core.domain.model.checkout.order.order_details.OrderDetailsResponse
import com.floriaapp.core.domain.model.checkout.order.order_details.orderTracking
import com.test.utils.*

class CustomOrderSteps(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.custom_order_steps, this)
    }


    fun setOrdersSteps(orderDetailsResponse: OrderDetailsResponse) {
        if (orderDetailsResponse.data.orderTracking.isNotEmpty()) {
            orderDetailsResponse.data.orderTracking.forEachIndexed { index, orderTracking ->
                when (orderTracking.id) {
                    PENDING -> {
                        setFirstStepDone(orderDetailsResponse.data.orderTracking, PENDING)
                    }
                    PREPARING ->{
                        setFirstStepDone(orderDetailsResponse.data.orderTracking, PENDING)
                        setSecondStepDone(orderDetailsResponse.data.orderTracking, PREPARING)

                    }
                    DELIVERING ->{
                        setFirstStepDone(orderDetailsResponse.data.orderTracking, PENDING)
                        setSecondStepDone(orderDetailsResponse.data.orderTracking, PREPARING)
                        setThirdStepDone(orderDetailsResponse.data.orderTracking, DELIVERING)

                    }
                    DELIVERED ->{
                        setFirstStepDone(orderDetailsResponse.data.orderTracking, PENDING)
                        setSecondStepDone(orderDetailsResponse.data.orderTracking, PREPARING)
                        setThirdStepDone(orderDetailsResponse.data.orderTracking, DELIVERING)
                        setFourthStepDone(orderDetailsResponse.data.orderTracking, DELIVERED)
                    }

                }
            }

        }

    }

    fun setFirstStepDone(orderTracking: List<orderTracking>, PENDING: Int) {
        orderTracking.forEachIndexed { index, orderTrackings ->
            if (orderTrackings.id == PENDING){
                findViewById<TextView>(R.id.tv_date_first_ball).text = orderTrackings.created_at.date
                findViewById<TextView>(R.id.tv_first_ball).text = orderTrackings.order_status
                findViewById<ImageView>(R.id.first_ball).backgroundTintList = ColorStateList.valueOf(
                    context.resources.getColor(R.color.teaBlue)
                )
                findViewById<View>(R.id.first_view).backgroundTintList =
                    ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
                return@forEachIndexed
            }
        }


    }

    fun setSecondStepDone(orderTracking: List<orderTracking>, PREPARING: Int) {
        orderTracking.forEachIndexed { index, orderTrackings ->
            if (orderTrackings.id == PREPARING){
                findViewById<TextView>(R.id.tv_date_second_ball).text = orderTrackings.created_at.date
                findViewById<TextView>(R.id.tv_second_ball).text = orderTrackings.order_status
                findViewById<ImageView>(R.id.second_ball).backgroundTintList = ColorStateList.valueOf(
                    context.resources.getColor(R.color.teaBlue)
                )
                findViewById<View>(R.id.second_view).backgroundTintList =
                    ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
                return@forEachIndexed
            }
        }

    }


    fun setThirdStepDone(orderTracking: List<orderTracking>, DELIVERING: Int) {
        orderTracking.forEachIndexed { index, orderTrackings ->
            if (orderTrackings.id == DELIVERING){
                findViewById<TextView>(R.id.tv_date_third_ball).text = orderTrackings.created_at.date
                findViewById<TextView>(R.id.tv_third_ball).text = orderTrackings.order_status
                findViewById<ImageView>(R.id.third_ball).backgroundTintList = ColorStateList.valueOf(
                    context.resources.getColor(R.color.teaBlue)
                )
                findViewById<View>(R.id.third_view).backgroundTintList =
                    ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
                return@forEachIndexed
            }
        }


    }


    fun setFourthStepDone(orderTracking: List<orderTracking>, DELIVERED: Int) {
        orderTracking.forEachIndexed { index, orderTrackings ->
            if (orderTrackings.id == DELIVERED){
                findViewById<TextView>(R.id.tv_date_fourth_ball).text = orderTrackings.created_at.date
                findViewById<TextView>(R.id.tv_fourth_ball).text = orderTrackings.order_status
                findViewById<ImageView>(R.id.fourth_ball).backgroundTintList = ColorStateList.valueOf(
                    context.resources.getColor(R.color.teaBlue)
                )
                return@forEachIndexed
            }
        }

    }


}