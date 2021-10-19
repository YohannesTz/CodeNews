package com.yohannes.dev.app.codenews.datasource

import com.yohannes.dev.app.codenews.model.MainResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=5fc13252f95b4945bae05e1667fbfb39
    @GET("/v2/top-headlines")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("categories") category: String
    ): Response<MainResponse>

    companion object {
        var BASE_URL = "https://newsapi.org"
        var apiService: NewsApiService? = null

        fun getInstance(): NewsApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(NewsApiService::class.java)
            }
            return apiService!!
        }
    }
}