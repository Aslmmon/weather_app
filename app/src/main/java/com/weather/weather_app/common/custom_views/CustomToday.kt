package com.weather.weather_app.common.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.floriaapp.core.entity.ListData
import com.weather.weather_app.R

class CustomToday(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.custom_today_layout, this)

    }
    fun setData(firstItem: ListData) {
        with(firstItem){
            findViewById<TextView>(R.id.tv_date_time).text = this.dtTxt
        }

    }




}