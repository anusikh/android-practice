package com.example.newsjetpack.ui.repository

import com.example.newsjetpack.data.datasource.NewsDataSource
import com.example.newsjetpack.data.entity.NewsResponse
import com.example.utitlities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {
    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadline(country)
            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("error fetching data"))
            }
        }.catch { e -> emit(ResourceState.Error(e.message ?: "error fetching data")) }
    }
}
