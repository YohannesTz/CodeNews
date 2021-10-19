package com.yohannes.dev.app.codenews.model

data class MainResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
