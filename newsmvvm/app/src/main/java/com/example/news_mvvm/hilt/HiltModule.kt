package com.example.news_mvvm.hilt

import com.example.news_mvvm.remote.NewsInterface
import com.example.news_mvvm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    fun provideRetrofitInterface(): NewsInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsInterface::class.java)
    }
}