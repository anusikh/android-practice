package com.example.paging_jetpack.remote

import com.example.paging_jetpack.model.NewsResponse
import com.example.paging_jetpack.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("everything?q=india&sortBy=popularity&apiKey=${Constants.API_KEY}&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse
}