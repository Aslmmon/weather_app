package com.test.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.floriaapp.core.domain.model.home.Slider
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.smarteist.autoimageslider.SliderViewAdapter
import com.test.utils.*
import com.test.utils.Bases.Communication
import com.test.utils.Ext.openUrl


class SliderAdapter(var context: Context, list: List<Slider>, var communication: Communication) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<Slider> = list as MutableList<Slider>

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: Slider = mSliderItems[position]
        Glide.with(context)
            .load(sliderItem.image)
            .into(viewHolder.imageViewBackground)
        viewHolder.titleText.text = sliderItem.title
        viewHolder.subtitleText.text = sliderItem.content

        val radius = 10f
        viewHolder.imageViewBackground.shapeAppearanceModel =
            viewHolder.imageViewBackground.shapeAppearanceModel
                .toBuilder()
                .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
                .setBottomRightCorner(CornerFamily.ROUNDED, radius)

                .build()


        viewHolder.imageViewBackground.setOnClickListener {
            when (sliderItem.model) {
                GENERAL -> {
                }
                PROVIDER -> {
                    communication.goToVendorsActivity(sliderItem.model_id)
                }
                PRODUCT -> {
                    communication.goToProductDetails(sliderItem.model_id)
                }
                EXTERNAL ->{
                    context.openUrl(sliderItem.url)
                }
            }
        }

    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var imageViewBackground: ShapeableImageView = itemView.findViewById(R.id.iv_auto_image_slider)
        var titleText: TextView = itemView.findViewById(R.id.tv_title)
        var subtitleText: TextView = itemView.findViewById(R.id.subtitle_)

    }

}
