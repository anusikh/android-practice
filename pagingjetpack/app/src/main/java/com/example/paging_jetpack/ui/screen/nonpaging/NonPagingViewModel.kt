package com.example.paging_jetpack.ui.screen.nonpaging

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paging_jetpack.model.Article
import com.example.paging_jetpack.model.NewsResponse
import com.example.paging_jetpack.remote.NewsApi
import com.example.paging_jetpack.util.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NonPagingViewModel @Inject constructor(
    private val newsApi: NewsApi
) : ViewModel() {

    val newsList = mutableStateListOf<Article>()
    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)

    init {
        getNews()
    }

    suspend fun getNews(page: Int): Flow<NewsResponse> = flow {
        try {
            emit(newsApi.getNews(page))
        } catch (error: Exception) {
            emit(NewsResponse(emptyList(), error.message ?: "", 0))
        }
    }.flowOn(Dispatchers.IO)

    fun getNews() = viewModelScope.launch {
        if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
            listState = if (page == 1) ListState.LOADING else ListState.PAGINATING

            getNews(page).collect() {
                if (it.status == "ok") {
                    canPaginate = it.articles.size == 20

                    if (page == 1) {
                        newsList.clear()
                        newsList.addAll(it.articles)
                    } else {
                        newsList.addAll(it.articles)
                    }

                    listState = ListState.IDLE

                    if (canPaginate) page++
                } else {
                    listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                }
            }
        }
    }

    override fun onCleared() {
        page = 1
        listState = ListState.IDLE
        canPaginate = false
        super.onCleared()
    }

}