package com.test.utils.custom

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.utils.R
import kotlinx.android.synthetic.main.custom_checkout_steps.view.*

class CustomCheckoutSteps(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.custom_checkout_steps, this)
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            (context as Activity).finish()
        }
    }
    fun setFirstStepDone(){
        findViewById<TextView>(R.id.first_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<View>(R.id.first_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<TextView>(R.id.second_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.second_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<TextView>(R.id.third_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.third_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<TextView>(R.id.fourth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.fourth_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))

    }

    fun setSecondStepDone(){
        setFirstStepDone()
        findViewById<TextView>(R.id.second_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<View>(R.id.second_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))

        findViewById<TextView>(R.id.third_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.third_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<TextView>(R.id.fourth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.fourth_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))

    }

    fun setThirdStepDone(){
        setSecondStepDone()
        findViewById<TextView>(R.id.third_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<View>(R.id.third_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<TextView>(R.id.fourth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.fourth_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))

    }

    fun setFourthStepDone(){
        setThirdStepDone()
        findViewById<TextView>(R.id.fourth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<View>(R.id.fourth_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<TextView>(R.id.fifth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))
        findViewById<View>(R.id.fifth_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))

    }

    fun setFifthStepDone(){
        setFourthStepDone()
        findViewById<TextView>(R.id.fifth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<View>(R.id.fifth_view).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))
        findViewById<TextView>(R.id.sixth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorBlueWhite))

    }


    fun setSixthStepDone(){
        setFifthStepDone()
        findViewById<TextView>(R.id.sixth_ball).backgroundTintList = ColorStateList.valueOf(context.resources.getColor(R.color.teaBlue))

    }




}