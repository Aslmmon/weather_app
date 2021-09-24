package com.test.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.utils.R

class CustomRateOrder(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var rateBtn :Button
    init {
        inflate(context, R.layout.custom_rate, this)
        rateBtn = findViewById(R.id.btn_rate)

    }




}