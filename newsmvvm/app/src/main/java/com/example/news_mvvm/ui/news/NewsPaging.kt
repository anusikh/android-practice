package com.example.news_mvvm.ui.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.news_mvvm.data.Article
import com.example.news_mvvm.remote.NewsInterface
import com.example.news_mvvm.utils.Constants
import java.lang.Exception

class NewsPaging(val q: String, val newsInterface: NewsInterface) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val data = newsInterface.getAllNews(q, page, Constants.API_KEY)
            LoadResult.Page(
                data = data.body()?.articles!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.articles?.isEmpty()!!) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}