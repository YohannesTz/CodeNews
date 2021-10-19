package com.yohannes.dev.app.codenews.ui

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yohannes.dev.app.codenews.R
import com.yohannes.dev.app.codenews.datasource.NewsApiService
import com.yohannes.dev.app.codenews.datasource.NewsPagingSource
import com.yohannes.dev.app.codenews.model.ApiConfig
import com.yohannes.dev.app.codenews.model.Article
import kotlinx.coroutines.flow.Flow

class NewsViewModel(private val apiService: NewsApiService, category: String): ViewModel() {

    private val apiKey: String = Resources.getSystem().getString(R.string.apiKey)
    private val apiConfig:ApiConfig = ApiConfig("us", apiKey, 5, category)

    val news: Flow<PagingData<Article>> = Pager(PagingConfig(pageSize = 5)) {
        NewsPagingSource(apiService, apiConfig)
    }.flow.cachedIn(viewModelScope)


}