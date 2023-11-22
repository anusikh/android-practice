package com.example.news_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.news_mvvm.remote.NewsInterface
import com.example.news_mvvm.ui.news.NewsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsInterface: NewsInterface
) : ViewModel() {
    // setting value here since it will q=india to get indian news
    private val query = MutableLiveData<String>("india")

    var list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            NewsPaging(query, newsInterface)
        }.liveData.cachedIn(viewModelScope)
    }


}