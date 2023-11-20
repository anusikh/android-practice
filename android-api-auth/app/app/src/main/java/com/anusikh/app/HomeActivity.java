package com.anusikh.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.anusikh.app.util.ClearSharedPref;

public class HomeActivity extends AppCompatActivity {

    Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(view -> {
            callLogout();
        });
    }

    private void callLogout() {
        ClearSharedPref.clear(this);
        startActivity(new Intent(this, LoginActivity.class));
    }
}