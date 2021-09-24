package com.test.utils.custom

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.test.utils.R


class CustomToolbar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var back:ImageView
    init {
        inflate(context, R.layout.custom_toolbar, this)
        back = findViewById<ImageView>(R.id.iv_back)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.custom_toolbar)
        val text : String? = attributes.getString(R.styleable.custom_toolbar_android_text)
        val isBack :Boolean = attributes.getBoolean(R.styleable.custom_toolbar_isBack,false)

        if (isBack){
            back.visibility = View.VISIBLE
        }else{
            back.visibility = View.GONE
        }


        text?.let { this.findViewById<TextView>(R.id.tv_toolbar).text = it }
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
           (context as Activity).finish()
        }
        val typeface = ResourcesCompat.getFont(context, R.font.din_arabic_bold)
        findViewById<TextView>(R.id.tv_toolbar).typeface = typeface



    }

    fun setTitleText(title:String){
        this.findViewById<TextView>(R.id.tv_toolbar).text = title
    }

}