package com.test.utils.custom

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.test.utils.NAME_SPACE
import com.test.utils.R


@SuppressLint("ClickableViewAccessibility")
class CustomTelephone(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    var editText: EditText


    init {
        inflate(context, R.layout.custom_telephone, this)
        editText = findViewById(R.id.ed_telephone)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.edit_text_box)
        val inputType: Int = attributes.getInt(R.styleable.edit_text_box_android_inputType, EditorInfo.TYPE_NULL)
        val maxLength: Int = attrs.getAttributeIntValue(NAME_SPACE, "maxLength", 50)
        inputType.let { editText.inputType = it }
        editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        findViewById<TextView>(R.id.tv_item).text = attributes.getString(R.styleable.edit_text_box_android_text)



        editText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) findViewById<FrameLayout>(R.id.frame).background = ContextCompat.getDrawable(context,R.drawable.box_tea_blue_rectangle)
            else findViewById<FrameLayout>(R.id.frame).background = ContextCompat.getDrawable(context,R.drawable.box_edit_text)
        }


    }
    fun getText(): String {
        return editText.text.trim().toString()
    }






//    fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
//        when (view.getId()) {
//            R.id.ivPasswordToggle -> when (motionEvent.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    Toast.makeText(context, "show", Toast.LENGTH_SHORT).show()
//                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
//                }
//                MotionEvent.ACTION_UP -> {
//                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
//                    Toast.makeText(context, "hide", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        return true
//    }

}