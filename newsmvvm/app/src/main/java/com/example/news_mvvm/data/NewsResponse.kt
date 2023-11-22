package com.example.news_mvvm.data

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)