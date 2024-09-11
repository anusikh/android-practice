package com.example.newsjetpack.data.api

import com.example.newsjetpack.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "9908a284d11342b9819c623043e14fdc",
    ): Response<NewsResponse>
}
