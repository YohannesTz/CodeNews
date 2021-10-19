package com.yohannes.dev.app.codenews.model

data class ApiConfig(
    val country: String = "us",
    val apiKey: String,
    val pageSize: Int = 5,
    val category: String
)
