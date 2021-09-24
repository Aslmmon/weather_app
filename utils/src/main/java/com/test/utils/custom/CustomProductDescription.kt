package com.test.utils.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Html
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.floriaapp.core.domain.model.product.ProductDetailsReponseItem
import com.floriaapp.core.domain.model.product.tagItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.test.utils.*
import com.test.utils.Ext.loadImage


class CustomProductDescription(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var customCounter:CustomCounter
    var imageProfile:ImageView
    var questionsProduct:TextView
    var haveQuestion:TextView
    var chipset:ChipGroup

    init {
        inflate(context, R.layout.custom_product_description, this)
        imageProfile = findViewById<ImageView>(R.id.iv_profile)
        customCounter = findViewById<CustomCounter>(R.id.custom_counter)
        questionsProduct = findViewById(R.id.tv_quest_product)
        haveQuestion = findViewById(R.id.tv_have_question)
        chipset = findViewById(R.id.chipset)
    }

    @SuppressLint("CutPasteId")
    fun bindDataToView(productDetailsReponseItem: ProductDetailsReponseItem) {
        var expandable = false
        findViewById<TextView>(R.id.tv_product_name).text = productDetailsReponseItem.provider.name
        findViewById<TextView>(R.id.tv_rate).text = productDetailsReponseItem.rate.toString()
        findViewById<TextView>(R.id.tv_description).text = productDetailsReponseItem.description

        if(findViewById<TextView>(R.id.tv_description).lineCount < 3){
            findViewById<TextView>(R.id.tv_see_more).visibility = View.GONE
        }
        if (productDetailsReponseItem.tags.isNotEmpty()) addTags(productDetailsReponseItem.tags,context)
        else chipset.visibility = View.GONE

        findViewById<TextView>(R.id.tv_see_more).setOnClickListener(OnClickListener {
            if (!expandable) {
                expandable= true
                findViewById<TextView>(R.id.tv_description).maxLines = Integer.MAX_VALUE;//your TextView
                findViewById<TextView>(R.id.tv_see_more).text = resources.getString(R.string.showLess)
            } else {
                expandable = false
                findViewById<TextView>(R.id.tv_description).maxLines = 3
                findViewById<TextView>(R.id.tv_see_more).text = resources.getString(R.string.see_more)
        }})


        findViewById<TextView>(R.id.tv_size_value).text = productDetailsReponseItem.weight.toString()
        findViewById<TextView>(R.id.tv_quantity_value).text = productDetailsReponseItem.quantity.toString()
        findViewById<TextView>(R.id.tv_quest_product).text = resources.getString(R.string.questions_on_product_15_question_title)
        imageProfile.loadImage(productDetailsReponseItem.provider.logo)
        val text = Html.fromHtml(" <u><font color='#0199B1'>(${productDetailsReponseItem.productQuestions.size} ${resources.getString(R.string.one_question)})</u></font>")
        findViewById<TextView>(R.id.tv_quest_product).append(" ")
        findViewById<TextView>(R.id.tv_quest_product).append(text)
        customCounter.setPrice(productDetailsReponseItem.price)

    }

    private fun addTags(tags: List<tagItem>, context: Context) {
        chipset.removeAllViews()
        tags.forEachIndexed { index, tagItem ->
            val chip = Chip(context)
            chip.text = tagItem.name
            chip.chipCornerRadius = 4f
            chip.setTextColor(resources.getColor(R.color.teaBlue))
            chip.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.colorBlueWhite))
            chipset.addView(chip)
            chip.setOnClickListener {
                val intent = Intent(context, Class.forName(FAVOURITES_CLASS_NAME))
                intent.putExtra(TAG_ID,tagItem.id)
                context.startActivity(intent)
            }
        }


    }


}