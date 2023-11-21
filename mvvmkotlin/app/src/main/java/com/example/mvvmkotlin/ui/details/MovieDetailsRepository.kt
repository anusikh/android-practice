package com.example.mvvmkotlin.ui.details

import com.example.mvvmkotlin.data.moviedetails.MovieDetails
import com.example.mvvmkotlin.remote.MovieInterface
import com.example.mvvmkotlin.utils.Constants
import com.example.mvvmkotlin.utils.Result
import com.example.mvvmkotlin.utils.Status

class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): Result<MovieDetails> {

        return try {
            val response = movieInterface.getMovieDetails(imdbId, Constants.API_KEY)
            if (response.isSuccessful) {
                Result(Status.SUCCESS, response.body(), null)
            } else {
                Result(Status.ERROR, null, null)
            }
        } catch (e: Exception) {
            Result(Status.ERROR, null, null)
        }
    }
}