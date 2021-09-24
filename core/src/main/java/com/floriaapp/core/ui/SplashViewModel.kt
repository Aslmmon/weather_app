package com.floriaapp.core.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.floriaapp.core.Extensions.launchDataLoad
import com.floriaapp.core.Extensions.toErrorBody
import com.floriaapp.core.api.generalApi
import com.floriaapp.core.data.data_source.category_paging.CategoryDataSource
import com.floriaapp.core.data.data_source.category_paging.NotificationDataSource
import com.floriaapp.core.domain.model.category.categoryProductItem
import com.floriaapp.core.domain.model.general.GeneralDataResponse
import com.floriaapp.core.domain.model.notification.NotificationItem
import com.floriaapp.core.domain.model.orderSuccess.OrderSuccess
import com.floriaapp.core.domain.model.success.SuccessMessage
import kotlinx.coroutines.flow.Flow

class SplashViewModel(var generalApi: generalApi) : ViewModel() {

    var _GeneralData = MutableLiveData<GeneralDataResponse>()
    var GeneralData: LiveData<GeneralDataResponse> = _GeneralData

    var _SuccessSend = MutableLiveData<OrderSuccess>()
    var SuccessSend: LiveData<OrderSuccess> = _SuccessSend


    var _ReadNotification = MutableLiveData<SuccessMessage>()
    var ReadNotification: LiveData<SuccessMessage> = _ReadNotification


    var _Error = MutableLiveData<String>()
    var Error: LiveData<String> = _Error

    fun getGeneralData() {

        launchDataLoad(execution = {
            _GeneralData.postValue(generalApi.getGeneralData())
        }, errorReturned = {
            Log.e("error",it.message.toString())
            //_Error.postValue(it.toErrorBody())
        })
    }


    fun setFirebaseToken(token:String) {

        launchDataLoad(execution = {
            _SuccessSend.postValue(generalApi.storeFirebaseToken(token))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            //_Error.postValue(it.toErrorBody())
        })
    }

    fun enquiry(name:String,email:String,phone:String,message:String) {

        launchDataLoad(execution = {
            _SuccessSend.postValue(generalApi.enquiry(name,email,phone,message))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }

//    fun getNotificationList() {
//
//        launchDataLoad(execution = {
//            _SuccessSend.postValue(generalApi.getUserNotifications())
//        }, errorReturned = {
//            Log.e("error",it.message.toString())
//            _Error.postValue(it.toErrorBody())
//        })
//    }

    fun getNotificationList(): Flow<PagingData<NotificationItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            NotificationDataSource(generalApi)
        }.flow.cachedIn(viewModelScope)
    }


    fun markNotificationAsRead(notificationID:Int) {

        launchDataLoad(execution = {
            _ReadNotification.postValue(generalApi.markNotifcationAsRead(notificationID))
        }, errorReturned = {
            Log.e("error",it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


//
//    fun deleteNotification(notificationID) {
//
//        launchDataLoad(execution = {
//            _SuccessSend.postValue(generalApi.deleteNotification(notificationID))
//        }, errorReturned = {
//            Log.e("error",it.message.toString())
//            _Error.postValue(it.toErrorBody())
//        })
//    }






}