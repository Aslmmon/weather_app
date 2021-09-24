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
import com.floriaapp.core.api.categoryApi
import com.floriaapp.core.data.data_source.category_paging.CategoryDataSource
import com.floriaapp.core.data.data_source.category_paging.QuestionsProductDataSource
import com.floriaapp.core.data.data_source.category_paging.RatingDataSource
import com.floriaapp.core.data.data_source.category_paging.TagProductDataSource
import com.floriaapp.core.domain.model.FilterData
import com.floriaapp.core.domain.model.category.CategoriesResponse
import com.floriaapp.core.domain.model.category.categoryProductItem
import com.floriaapp.core.domain.model.orderSuccess.Data
import com.floriaapp.core.domain.model.orderSuccess.Meta
import com.floriaapp.core.domain.model.orderSuccess.OrderSuccess
import com.floriaapp.core.domain.model.product.ProductDetailsResponse
import com.floriaapp.core.domain.model.questions.ProductQuestionItem
import com.floriaapp.core.domain.model.rating.RatingDataItem
import com.floriaapp.core.domain.model.tagItems.TageItemResponse
import com.floriaapp.core.domain.model.tagItems.TagsProducts
import kotlinx.coroutines.flow.Flow

class CategoryViewModel(var categoryApi: categoryApi) : ViewModel() {

    var _CategoriesData = MutableLiveData<CategoriesResponse>()
    var CategoriesData: LiveData<CategoriesResponse> = _CategoriesData

    var _ProductDetails = MutableLiveData<ProductDetailsResponse>()
    var ProductDetails: LiveData<ProductDetailsResponse> = _ProductDetails

    private var _TagProducts = MutableLiveData<TagsProducts>()
    var tagsProducts: LiveData<TagsProducts> = _TagProducts

    var _FavouriteResponse = MutableLiveData<OrderSuccess>()
    var FavouriteResponse: LiveData<OrderSuccess> = _FavouriteResponse


    var _SendQuestionResponse = MutableLiveData<OrderSuccess>()
    var SendQuestionResponse: LiveData<OrderSuccess> = _SendQuestionResponse


    var _Error = MutableLiveData<String>()
    var Error: LiveData<String> = _Error

    fun getCategories() {
        launchDataLoad(execution = {
            _CategoriesData.postValue(categoryApi.getCategories())
        }, errorReturned = {
            Log.e("error", it.message.toString())
            //_Error.postValue(it.toErrorBody())
        })
    }

    fun getProductDetails(productId: Int) {
        launchDataLoad(execution = {
            _ProductDetails.postValue(categoryApi.getProductDetails(productId))
        }, errorReturned = {
            Log.e("error", it.message.toString())
            //_Error.postValue(it.toErrorBody())
        })
    }


    fun submitQuestions(productId: Int, questionAr: String) {
        launchDataLoad(execution = {
            _SendQuestionResponse.postValue(
                categoryApi.submitProuctQuestions(
                    productId = productId,
                    questionAr = questionAr
                )
            )
        }, errorReturned = {
            Log.e("error", it.message.toString())
            //_Error.postValue(it.toErrorBody())
        })
    }


    fun getCategoryProducts(categoryId: Int): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            CategoryDataSource(categoryId, categoryApi, 1)
        }.flow.cachedIn(viewModelScope)
    }


    fun getAllProducts(): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            CategoryDataSource(categoryApi = categoryApi, typeOfApi = 2)
        }.flow.cachedIn(viewModelScope)
    }

    fun getFeaturedProducts(): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            CategoryDataSource(categoryApi = categoryApi, typeOfApi = 3)
        }.flow.cachedIn(viewModelScope)
    }



    fun getTagProducts(tagId:Int): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 2)) {
            TagProductDataSource(categoryApi = categoryApi,tagId = tagId)
        }.flow.cachedIn(viewModelScope)
    }

    fun getTagProductsNormal(tagId: Int) {
        launchDataLoad(execution = {
            _TagProducts.postValue(categoryApi.getTagProducts(tagId))
        }, errorReturned = {
            Log.e("error", it.message.toString())
            _Error.postValue(it.toErrorBody())
        })
    }


    fun getFavoriteProducts(): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            CategoryDataSource(categoryApi = categoryApi, typeOfApi = 4)
        }.flow.cachedIn(viewModelScope)
    }

    fun searchForProducts(searchWord: String): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            CategoryDataSource(categoryApi = categoryApi, typeOfApi = 5, searchWord = searchWord)
        }.flow.cachedIn(viewModelScope)
    }

    fun filterProducts(filter: FilterData): Flow<PagingData<categoryProductItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            CategoryDataSource(categoryApi = categoryApi, typeOfApi = 6, filterData = filter)
        }.flow.cachedIn(viewModelScope)
    }

    fun getProductsQuestions(): Flow<PagingData<ProductQuestionItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            QuestionsProductDataSource(categoryApi = categoryApi)
        }.flow.cachedIn(viewModelScope)
    }

    fun getRatings(): Flow<PagingData<RatingDataItem>> {
        return Pager(PagingConfig(pageSize = 5)) {
            RatingDataSource(categoryApi = categoryApi)
        }.flow.cachedIn(viewModelScope)

    }

    fun favouriteAction(productId: Int, typeOfFav: Int) {
        launchDataLoad(execution = {

            _FavouriteResponse.value = when (typeOfFav) {
                1 -> categoryApi.addToFavourites(productId)
                2 -> categoryApi.deleteFromFavourites(productId)
                else -> OrderSuccess(data = Data("test", meta = Meta(0)))
            }
        }, errorReturned = {
            Log.e("error", it.message.toString())
            //_Error.postValue(it.toErrorBody())
        })

    }


}