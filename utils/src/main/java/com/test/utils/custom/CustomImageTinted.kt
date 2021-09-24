package com.test.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.test.utils.Ext.loadImage
import com.test.utils.R


class CustomImageTinted(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

     var imageView: ShapeableImageView
    init {
        inflate(context, R.layout.custom_image_tinted, this)
        imageView = findViewById(R.id.image_)

    }
    fun setImage(src:String,title:String){
        imageView.loadImage(src)
        findViewById<TextView>(R.id.tv_image_title).text = title
//        val radius = 15f
//        imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
//            .toBuilder()
//            .setAllCorners(CornerFamily.ROUNDED, radius)
//            .build()
    }



}