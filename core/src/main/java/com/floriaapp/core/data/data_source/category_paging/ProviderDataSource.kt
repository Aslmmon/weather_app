package com.floriaapp.core.data.data_source.category_paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.floriaapp.core.api.cartApi
import com.floriaapp.core.api.categoryApi
import com.floriaapp.core.domain.model.category.CategoryProducts
import com.floriaapp.core.domain.model.category.categoryProductItem
import com.floriaapp.core.domain.model.provider.ProviderDetailsResponse


class ProviderDataSource(val providerID:Int?=null, val cartApi: cartApi) : PagingSource<Int, categoryProductItem>() {

    lateinit var categoryProducts: ProviderDetailsResponse
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, categoryProductItem> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            categoryProducts = cartApi.getProviderDetails(providerID!!,currentLoadingPageKey)
//            categoryProducts =  when(typeOfApi){
//                1-> c.getCategoryProducts(categoryId,currentLoadingPageKey)
//                2->categoryApi.getAllProducts(currentLoadingPageKey)
//                3->categoryApi.getFeautredProducts(currentLoadingPageKey)
//                else -> categoryProducts
//            }
            val response = categoryProducts
            val responseData = mutableListOf<categoryProductItem>()
            val data = response.data
            if (data != null) {
                responseData.addAll(data)
            }

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (data?.isEmpty() == true) null else currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, categoryProductItem>): Int? {
        return null
    }

}