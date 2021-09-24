package com.floriaapp.core.data.data_source.category_paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.floriaapp.core.api.generalApi
import com.floriaapp.core.domain.model.notification.NotificationItem
import com.floriaapp.core.domain.model.notification.NotificationsData

class NotificationDataSource( val generalApi: generalApi) : PagingSource<Int, NotificationItem>() {

    lateinit var notifications: NotificationsData
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NotificationItem> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = generalApi.getUserNotifications(currentLoadingPageKey)
            val responseData = mutableListOf<NotificationItem>()
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

    override fun getRefreshKey(state: PagingState<Int, NotificationItem>): Int? {
        return null
    }

}