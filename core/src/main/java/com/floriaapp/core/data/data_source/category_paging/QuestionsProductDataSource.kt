package com.floriaapp.core.data.data_source.category_paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.floriaapp.core.api.categoryApi
import com.floriaapp.core.domain.model.questions.ProductQuestionItem
import com.floriaapp.core.domain.model.questions.ProductQuestions

class QuestionsProductDataSource(
    val categoryApi: categoryApi
) : PagingSource<Int, ProductQuestionItem>() {

    lateinit var categoryProducts: ProductQuestions
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductQuestionItem> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            categoryProducts = categoryApi.getQuestionsProducts(currentLoadingPageKey)
            val response = categoryProducts
            val responseData = mutableListOf<ProductQuestionItem>()
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

    override fun getRefreshKey(state: PagingState<Int, ProductQuestionItem>): Int? {
        return null
    }

}