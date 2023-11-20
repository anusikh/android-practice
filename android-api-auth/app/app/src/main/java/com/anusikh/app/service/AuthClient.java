package com.anusikh.app.service;

import com.anusikh.app.dao.LoginResponse;
import com.anusikh.app.model.Login;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthClient {

    @POST("login")
    Call<LoginResponse> login(@Body Login l);

    @GET("logged")
    Call<ResponseBody> isLogged(@Header("Authorization") String token);

}
