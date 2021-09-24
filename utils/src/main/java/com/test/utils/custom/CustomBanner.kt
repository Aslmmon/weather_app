package com.test.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.floriaapp.core.domain.model.home.Slider
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.test.utils.Bases.Communication
import com.test.utils.R
import com.test.utils.adapter.SliderAdapter
import ru.nikartm.support.ImageBadgeView


class CustomBanner(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var navMenu:ImageView
    var sliderView :SliderView
    var notificationImage: ImageBadgeView
    var searchBar:EditText
    init {
        inflate(context, R.layout.custom_banner, this)
        sliderView = findViewById(R.id.banner_view)
        navMenu = findViewById(R.id.nav_menu)
        notificationImage = findViewById(R.id.iv_notication)
        searchBar = findViewById(R.id.ed)
    }

    fun setSliders(list: List<Slider>, communication: Communication){
        sliderView.setSliderAdapter(SliderAdapter(context,list,communication))
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();
    }

    fun setCounterToNotification(meta: Int) {
        notificationImage.badgeValue = meta
    }




}