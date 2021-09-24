package com.floriaapp.core.api

import com.floriaapp.core.domain.model.general.GeneralDataResponse
import com.floriaapp.core.domain.model.notification.NotificationsData
import com.floriaapp.core.domain.model.orderSuccess.OrderSuccess
import com.floriaapp.core.domain.model.success.SuccessMessage
import retrofit2.http.*

interface generalApi {


    @GET("/api/v1/general")
    suspend fun getGeneralData(
        @Query("list[]") firstList: String = "countries",
        @Query("list[]") secondList: String = "nationalities",
        @Query("list[]") thirdList: String = "bankTransferSettings",
        @Query("list[]") fourthList: String = "settings"


    ): GeneralDataResponse


    @POST("/api/v1/store-firebase-token")
    @FormUrlEncoded
    suspend fun storeFirebaseToken(@Field("token") token:String,@Field("device_type") deviceType:String = "android"):OrderSuccess



    @GET("/api/v1/user-notifications")
    suspend fun getUserNotifications(@Query("page") page:Int) :NotificationsData


    @POST("/api/v1/user-notifications/{notification_id}/mark-as-read")
    suspend fun markNotifcationAsRead(@Path("notification_id") notificationId:Int):SuccessMessage

    @DELETE("/api/v1/user-notifications/{notification_id}")
    suspend fun deleteNotification(@Path("notification_id") notificationId:Int)

    @POST("/api/v1/enquiries")
    @FormUrlEncoded
    suspend fun enquiry(@Field("name") name:String,@Field("email") email:String,
    @Field("phone") phone:String,@Field("message")message:String) : OrderSuccess


}