package com.floriaapp.core.domain.model.product


import android.os.Parcelable
import com.floriaapp.core.domain.model.category.CategoryResponseItem
import com.floriaapp.core.domain.model.category.Provider
import com.floriaapp.core.domain.model.category.categoryProductItem
import com.floriaapp.core.domain.model.questions.ProductQuestionItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ProductDetailsResponse(
    @SerializedName("data")
    val productDetailsReponseItem: ProductDetailsReponseItem
)

data class ProductDetailsReponseItem(
    @SerializedName("category")
    val category: CategoryResponseItem,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("in_cart")
    val inCart: Boolean,
    @SerializedName("is_favorited")
    val is_favorited: Boolean,
    @SerializedName("media")
    val media: List<image>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_questions")
    val productQuestions: ArrayList<ProductQuestionItem>,
    @SerializedName("provider")
    val provider: Provider,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("tags")
    val tags: List<tagItem>
)
data class image(var image:String)
data class tagItem(var id:Int,var name:String)
@Parcelize
data class productQuestions(var id:Int, var product: categoryProductItem) :Parcelable