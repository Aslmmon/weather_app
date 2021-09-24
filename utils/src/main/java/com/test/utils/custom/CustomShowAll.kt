package com.test.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.utils.R

class CustomShowAll(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var showAllText:TextView
    init {
        inflate(context, R.layout.custom_show_all, this)
        showAllText = findViewById(R.id.tv_showAll)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.custom_toolbar)
        val text : String? = attributes.getString(R.styleable.custom_toolbar_android_text)
        findViewById<TextView>(R.id.tv_choose_categories).text = text

    }



}