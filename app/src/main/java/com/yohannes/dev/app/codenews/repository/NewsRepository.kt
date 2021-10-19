package com.yohannes.dev.app.codenews.repository

import com.yohannes.dev.app.codenews.datasource.NewsApiService
import com.yohannes.dev.app.codenews.model.ApiConfig

class NewsRepository (private val newsApiService: NewsApiService, val apiConfig: ApiConfig) {
    //fun getNewsArticle() = newsApiService.getHeadlines()
}