package com.floriaapp.core.api

import com.floriaapp.core.domain.model.category.CategoriesResponse
import com.floriaapp.core.domain.model.category.CategoryProducts
import com.floriaapp.core.domain.model.orderSuccess.OrderSuccess
import com.floriaapp.core.domain.model.product.ProductDetailsResponse
import com.floriaapp.core.domain.model.questions.ProductQuestions
import com.floriaapp.core.domain.model.rating.RatingData
import com.floriaapp.core.domain.model.tagItems.TageItemResponse
import com.floriaapp.core.domain.model.tagItems.TagsProducts
import retrofit2.http.*

interface categoryApi {

    @GET("/api/v1/categories")
    suspend fun getCategories(): CategoriesResponse

    @GET("/api/v1/categories/{category_id}")
    suspend fun getCategoryProducts(
        @Path("category_id") categoryId: Int?,
        @Query("page") pageNumber: Int
    ): CategoryProducts

    @GET("/api/v1/products/{product_id}")
    suspend fun getProductDetails(@Path("product_id") productId: Int): ProductDetailsResponse


    @GET("/api/v1/order-ratings")
    suspend fun getRatings(@Query("page") pageNumber: Int): RatingData

    @GET("/api/v1/featured-products")
    suspend fun getFeautredProducts(@Query("page") pageNumber: Int): CategoryProducts

    @GET("/api/v1/favorites")
    suspend fun getFavourites(@Query("page") pageNumber: Int): CategoryProducts

    @GET("/api/v1/search")
    suspend fun search(
        @Query("search") searchWord: String,
        @Query("page") pageNumber: Int
    ): CategoryProducts

    @POST("/api/v1/products/{product_id}/favorites")
    suspend fun addToFavourites(@Path("product_id") product_id: Int): OrderSuccess

    @DELETE("/api/v1/products/{product_id}/favorites")
    suspend fun deleteFromFavourites(@Path("product_id") product_id: Int): OrderSuccess


    @GET("/api/v1/products")
    suspend fun getAllProducts(@Query("page") pageNumber: Int): CategoryProducts


    @GET("/api/v1/tags/{tagID}")
    suspend fun getTagProducts(@Path("tagID") tagID:Int): TagsProducts


    @GET("/api/v1/product-questions")
    suspend fun getQuestionsProducts(@Query("page") pageNumber: Int): ProductQuestions

    @POST("/api/v1/product-questions")
    suspend fun submitProuctQuestions(
        @Query("product_id") productId: Int,
        @Query("question") questionAr: String): OrderSuccess

    @GET("/api/v1/search")
    suspend fun filterProducts(
        @Query("category[]") categories: MutableList<Int>?,
        @Query("min_price") minPrice: Int?,
        @Query("max_price") maxPrice: Int?,
        @Query("page") pageNumber: Int
    ): CategoryProducts


}