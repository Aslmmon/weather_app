package com.floriaapp.core.api

import com.floriaapp.core.domain.model.checkout.address.AddressBody
import com.floriaapp.core.domain.model.checkout.order.OrderResponse
import com.floriaapp.core.domain.model.checkout.order.order_details.OrderDetailsResponse
import com.floriaapp.core.domain.model.checkout.shipping.AddressesResponse
import com.floriaapp.core.domain.model.checkout.shipping.ShippingCompaniesResponse
import com.floriaapp.core.domain.model.checkout.shipping.new.ShippinCostNeeded
import com.floriaapp.core.domain.model.orderSuccess.OrderSuccess
import com.floriaapp.core.domain.model.success.SuccessMessage
import com.floriaapp.core.domain.model.summary.SummaryOrder
import com.floriaapp.core.domain.model.summary.SummaryOrderResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface checkout {
    @POST("/api/v1/calculate-shipping-fees")
    @FormUrlEncoded
    suspend fun getShippingFees(
        @Field("address") address: Int
    ): ShippinCostNeeded

    @POST("/api/v1/calculate-express-shipping-fees")
    @FormUrlEncoded
    suspend fun getShippingFeesExpress(
        @Field("address") address: Int,
        @Field("product_id") product_id: Int,
        @Field("quantity") quantity: Int

    ): ShippinCostNeeded


    @POST("/api/v1/orders")
    @FormUrlEncoded
    suspend fun submitOrder(
        @Field("shipping") shipping: String?,
        @Field("address") address: Int?,
        @Field("payment_option") paymentOption: Int?,
        @Field("required_at") required_at: String?,
        @FieldMap notes: HashMap<String, String> = hashMapOf("notes[]" to "test"),
        @Field("promo_code") promoCode: String?,
        @Field("other_payment")otherPayment:Int?
    ): OrderSuccess


    @POST("/api/v1/express-checkout")
    @FormUrlEncoded
    suspend fun checkoutExpress(
        @Field("shipping") shipping: String?,
        @Field("address") address: Int?,
        @Field("payment_option") paymentOption: Int?,
        @Field("required_at") required_at: String?,
        @Field("notes") notes: String? = null,
        @Field("promo_code") promoCode: String?,
        @Field("product_id") productId: Int?,
        @Field("quantity") qunatity: Int?,
        @Field("other_payment")otherPayment: Int?

    ): OrderSuccess


    @GET("/api/v1/payment-options")
    suspend fun getPaymentOptions(): ShippingCompaniesResponse

    @GET("/api/v1/shipping-companies")
    suspend fun getShippingCompanies(): ShippingCompaniesResponse


    @GET("/api/v1/addresses")
    suspend fun getAddresses(): AddressesResponse


    @POST("/api/v1/addresses")
    suspend fun submitAddress(@Body address: AddressBody): OrderSuccess


    @GET("/api/v1/orders")
    suspend fun getListOfOrders(
        @Query("status") typeOfOrder: String,
        @Query("page") pageNumber: Int
    ): OrderResponse


    @GET("/api/v1/orders/{order_number}")
    suspend fun getOrderDetails(
        @Path("order_number") orderNumber: Int
    ): OrderDetailsResponse


    @Multipart
    @POST("/api/v1/upload-bank-transfer-attachment/{order_id}")
    suspend fun submitBankTransfer(
        @Path("order_id") orderId: Int, @Part attachment: MultipartBody.Part? = null
    ): SuccessMessage


    @POST("/api/v1/confirm-payment/{order_id}")
    @FormUrlEncoded
    suspend fun confirm_payment(
        @Path("order_id") orderId: Int,
        @Field("tap_id") tapId: String
    ): SuccessMessage

    @POST("/api/v1/cancel-payment/{order_id}")
    suspend fun cancelPayment(
        @Path("order_id") orderId: Int
    ): SuccessMessage


    @POST("/api/v1/order-summary")
    @FormUrlEncoded
    suspend fun summaryOrder(
        @Field("address") addressId: Int,
        @Field("shipping") shipping: String,
        @Field("promo_code") promoCode: String?,
    ): SummaryOrderResponse


    @POST("/api/v1/order-ratings/{order_id}")
    @FormUrlEncoded
    suspend fun rateOrder(
        @Path("order_id") orderId: Int,
        @Field("rate") rate: Int, @Field("notes") notes: String,
        @Field("shipping_rate") shippingRate: Int,
        @Field("shipping_notes") shippingNote: String
    ): SuccessMessage

}
