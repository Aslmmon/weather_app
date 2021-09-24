package com.floriaapp.core.data.data_source.category_paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.floriaapp.core.api.categoryApi
import com.floriaapp.core.domain.model.category.categoryProductItem

class TagProductDataSource(
    val categoryApi: categoryApi,
    val tagId:Int
) : PagingSource<Int, categoryProductItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, categoryProductItem> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            val categoryProducts = categoryApi.getTagProducts(tagID = tagId)
            val responseData = mutableListOf<categoryProductItem>()
            val data = categoryProducts.tageItemResponse.products
//            if (data != null) {
//                responseData.addAll(data)
//            }

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (data?.isEmpty() == true) null else currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            Log.i("tag",e.message.toString())
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, categoryProductItem>): Int? {
        return null
    }

}