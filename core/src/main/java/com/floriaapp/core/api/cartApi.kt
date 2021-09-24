package com.floriaapp.core.api

import com.floriaapp.core.domain.model.abou.AboutResponse
import com.floriaapp.core.domain.model.cart.CartDataResponse
import com.floriaapp.core.domain.model.category.CategoryProducts
import com.floriaapp.core.domain.model.faq.FaqResponse
import com.floriaapp.core.domain.model.home.MainHomeResponse
import com.floriaapp.core.domain.model.provider.ProviderDetailsResponse
import com.floriaapp.core.domain.model.success.SuccessMessage
import com.floriaapp.core.domain.model.terms.TermsAndConditionsResponse
import retrofit2.http.*

interface cartApi {


    @GET("/api/v1/carts")
    suspend fun getCart(): CartDataResponse


    @DELETE("/api/v1/carts/{cart_id}")
    suspend fun deleteCart(@Path("cart_id") cartId: Int): SuccessMessage


    @POST("/api/v1/carts")
    @FormUrlEncoded
    suspend fun addToCart(
        @Field("product_id") productId: Int,
        @Field("quantity") quantity: Int
    ): SuccessMessage

    @PUT("/api/v1/carts/{cart_id}")
    @FormUrlEncoded
    suspend fun updateCart(
        @Path("cart_id") cartId: Int,
        @Field("quantity") quantity: Int
    ):SuccessMessage

    @GET("/api/v1/home")
    suspend fun getHomeData():MainHomeResponse


    @GET("/api/v1/about")
    suspend fun getAbout(): AboutResponse


    @GET("/api/v1/faqs")
    suspend fun getFaqs(): FaqResponse

    @GET("/api/v1/terms")
    suspend fun getTerms(): TermsAndConditionsResponse




    @GET("/api/v1/providers/{provider_id}")
    suspend fun getProviderDetails(@Path("provider_id") providerID:Int ,@Query("page") page:Int?=null): ProviderDetailsResponse




}