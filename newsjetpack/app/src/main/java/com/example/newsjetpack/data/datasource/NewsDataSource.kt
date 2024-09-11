package com.example.newsjetpack.data.datasource

import com.example.newsjetpack.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {
    suspend fun getNewsHeadline(
        country: String,
    ): Response<NewsResponse>
}
