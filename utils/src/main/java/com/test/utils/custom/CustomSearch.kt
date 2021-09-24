package com.test.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.utils.R

class CustomSearch(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var editText :EditText
    var filterIcon:ImageView
    init {
        inflate(context, R.layout.custom_search, this)
        editText = findViewById(R.id.ed_search)
        filterIcon = findViewById(R.id.iv_filter)
    }

    fun getEditTextTitle(): String {
        return editText.text.toString()
    }



}