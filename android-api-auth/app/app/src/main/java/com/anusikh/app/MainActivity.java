package com.anusikh.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.anusikh.app.listener.AuthListener;
import com.anusikh.app.service.AuthManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthListener listener = new AuthListener() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }

            @Override
            public void onError() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        };

        AuthManager authManager = new AuthManager(MainActivity.this);
        authManager.isLogged(listener);
    }
}