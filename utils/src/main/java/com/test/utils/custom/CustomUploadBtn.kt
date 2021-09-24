package com.test.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.utils.R

class CustomUploadBtn(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var imageView:ImageView
    init {
        inflate(context, R.layout.custom_upload_btn, this)
        imageView = findViewById(R.id.iv_upload)
    }



}