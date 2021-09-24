package com.floriaapp.core.data.data_source.category_paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.floriaapp.core.api.checkout
import com.floriaapp.core.domain.model.checkout.order.OrderResponse
import com.floriaapp.core.domain.model.checkout.order.OrderResponseItem

class OrdersDataSource( val checkout: checkout,val type:String) : PagingSource<Int, OrderResponseItem>() {

    lateinit var categoryProducts: OrderResponse
    lateinit var orderProducts: OrderResponse

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OrderResponseItem> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            orderProducts = checkout.getListOfOrders(type,currentLoadingPageKey)
            val response = orderProducts
            val responseData = mutableListOf<OrderResponseItem>()
            val data = response.data
            if (data != null) {
                responseData.addAll(data)
            }

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = if (data.isEmpty()) null else currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, OrderResponseItem>): Int? {
        return null
    }

}