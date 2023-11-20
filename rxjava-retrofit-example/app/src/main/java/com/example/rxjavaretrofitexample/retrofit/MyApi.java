package com.example.rxjavaretrofitexample.retrofit;

import com.example.rxjavaretrofitexample.model.Posts;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApi {
    @GET("posts")
    Observable<List<Posts>> getPosts();
}
