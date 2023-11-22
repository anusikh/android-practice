package com.example.news_mvvm.remote

import com.example.news_mvvm.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("/v2/everything")
    suspend fun getAllNews(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

}