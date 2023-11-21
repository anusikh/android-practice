package com.example.mvvmkotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.mvvmkotlin.data.moviedetails.MovieDetails
import com.example.mvvmkotlin.remote.MovieInterface
import com.example.mvvmkotlin.ui.details.MovieDetailsRepository
import com.example.mvvmkotlin.ui.movie.MoviePaging
import com.example.mvvmkotlin.utils.Events
import com.example.mvvmkotlin.utils.Status
import com.example.mvvmkotlin.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: MovieInterface, private val repository: MovieDetailsRepository
) : ViewModel() {
    private val query = MutableLiveData<String>("value")

    // switch map is used to replace the new values within query
    var list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s);
    }

    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails

    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))
    }
}