package com.yohannes.dev.app.codenews.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yohannes.dev.app.codenews.model.ApiConfig
import com.yohannes.dev.app.codenews.model.Article

class NewsPagingSource(private val apiService: NewsApiService, private val apiConfig: ApiConfig) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getHeadlines(apiConfig.country, apiConfig.apiKey, apiConfig.pageSize, currentLoadingPageKey, apiConfig.category)
            val responseData = mutableListOf<Article>()
            val data = response.body()?.articles.orEmpty()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

}

