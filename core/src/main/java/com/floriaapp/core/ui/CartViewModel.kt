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
import com.floriaapp.core.Extensions.SingleLiveEvent
import com.floriaapp.core.Extensions.launchDataLoad
import com.floriaapp.core.Extensions.toErrorBody
import com.floriaapp.core.api.cartApi
import com.floriaapp.core.data.data_source.category_paging.CategoryDataSource
import com.floriaapp.core.data.data_source.category_paging.ProviderDataSource
import com.floriaapp.core.domain.model.abou.AboutResponse
import com.floriaapp.core.domain.model.cart.CartDataResponse
import com.floriaapp.core.domain.model.category.CategoryProducts
import com.floriaapp.core.domain.model.category.categoryProductItem
import com.floriaapp.core.domain.model.faq.FaqResponse
import com.floriaapp.core.domain.model.home.MainHomeResponse
import com.floriaapp.core.domain.model.provider.ProviderDetailsResponse
import com.floriaapp.core.domain.model.provider.ProviderProducts
import com.floriaapp.core.domain.model.success.SuccessMessage
import com.floriaapp.core.domain.model.terms.TermsAndConditionsResponse
import kotlinx.coroutines.flow.Flow

class CartViewModel(var cartApi: cartApi) : ViewModel() {

    var _CartResponse = MutableLiveData<CartDataResponse>()
    var CartResponse: LiveData<CartDataResponse> = _CartResponse

    var _CartMethods = SingleLiveEvent<SuccessMessage>()
    var CartMethods: SingleLiveEvent<SuccessMessage> = _CartMethods


    var _ProviderDetails = SingleLiveEvent<ProviderDetailsResponse>()
    var ProviderDetails: SingleLiveEvent<ProviderDetailsResponse> = _ProviderDetails

    var _HomeData = MutableLiveData<MainHomeResponse>()
    var HomeData: LiveData<MainHomeResponse> = _HomeData


    var _AboutData = MutableLiveData<AboutResponse>()
    var AboutData: LiveData<AboutResponse> = _AboutData


    var _Faqs = MutableLiveData<FaqResponse>()
    var Faqs: LiveData<FaqResponse> = _Faqs

    var _Terms = MutableLiveData<TermsAndConditionsResponse>()
    var Terms: LiveData<TermsAndConditionsResponse> = _Terms


    var _Error = SingleLiveEvent<String>()
    var Error: SingleLiveEvent<String> = _Error




    fun getCartData() {
        launchDataLoad(execution = {
            _CartResponse.postValue(cartApi.getCart())
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun updateCart(cartId: Int, quantity: Int) {
        launchDataLoad(execution = {
            _CartMethods.postValue(cartApi.updateCart(cartId = cartId, quantity = quantity))
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun getHomeData() {
        launchDataLoad(execution = {
            _HomeData.postValue(cartApi.getHomeData())
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }

    fun getAbout() {
        launchDataLoad(execution = {
            _AboutData.postValue(cartApi.getAbout())
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }

    fun getFaq() {
        launchDataLoad(execution = {
            _Faqs.postValue(cartApi.getFaqs())
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }

    fun getTerms() {
        launchDataLoad(execution = {
            _Terms.postValue(cartApi.getTerms())
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }





    fun deleteCart(cartId: Int) {
        launchDataLoad(execution = {
            _CartMethods.postValue(cartApi.deleteCart(cartId))
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }

    fun getProviderDetail(providerID: Int) {
        launchDataLoad(execution = {
            _ProviderDetails.postValue(cartApi.getProviderDetails(providerID))
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun getProviderDetails(providerId:Int) : Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            ProviderDataSource(cartApi =cartApi,providerID = providerId)
        }.flow.cachedIn(viewModelScope)
    }

    fun addToCart(productId: Int, quantity: Int) {
        launchDataLoad(execution = {
            _CartMethods.postValue(cartApi.addToCart(productId, quantity))
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }
}

