package com.example.paging_jetpack.ui.screen.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging_jetpack.model.Article
import com.example.paging_jetpack.remote.NewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val newsApi: NewsApi
) : ViewModel() {

    // the definition of this function can be written in a separate repository class
    fun getBreakingNews(): Flow<PagingData<Article>> =
        Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = {
            NewsPagingSource(newsApi)
        }).flow.cachedIn(viewModelScope)
}