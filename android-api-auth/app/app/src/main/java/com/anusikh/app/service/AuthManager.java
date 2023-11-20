package com.anusikh.app.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.anusikh.app.dao.LoginResponse;
import com.anusikh.app.listener.AuthListener;
import com.anusikh.app.model.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthManager {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    AuthClient authClient = retrofit.create(AuthClient.class);

    public AuthManager(Context context) {
        this.context = context;
    }

    public void login(AuthListener listener, Login l) {
        Call<LoginResponse> call = authClient.login(l);

        try {
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("accessToken", response.body().getAccessToken());
                        editor.putString("refreshToken", response.body().getRefreshToken());
                        editor.putInt("expiresAt", response.body().getExpiresAt());
                        editor.apply();
                        listener.onSuccess();
                    } else {
                        listener.onError();
                        Toast.makeText(context, "something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    listener.onError();
                    Toast.makeText(context, "something went wrong, try again", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "something went wrong, try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void isLogged(AuthListener listener) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        Call<ResponseBody> call = authClient.isLogged(String.format("Bearer %s",
                sharedPreferences.getString("accessToken", "")));

        try {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        listener.onError();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onError();
                }
            });
        } catch (Exception e) {
            listener.onError();
        }


    }
}
