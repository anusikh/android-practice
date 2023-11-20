package com.anusikh.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.anusikh.app.listener.AuthListener;
import com.anusikh.app.model.Login;
import com.anusikh.app.service.AuthManager;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText login_email_txt, login_password_txt;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_txt = findViewById(R.id.login_email_txt);
        login_password_txt = findViewById(R.id.login_password_txt);
        login_btn = findViewById(R.id.login_btn);


        login_btn.setOnClickListener(view -> {
            callLogin();
        });
    }

    AuthListener listener = new AuthListener() {
        @Override
        public void onSuccess() {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }

        @Override
        public void onError() {
            startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        }
    };

    private void callLogin() {
        try {
            if (!login_email_txt.getText().toString().equals("") && !login_password_txt.getText().toString().equals("")) {
                AuthManager authManager = new AuthManager(this);
                Login l = new Login(login_email_txt.getText().toString(), login_password_txt.getText().toString());
                authManager.login(listener, l);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Toast.makeText(this, "something went wrong, try again", Toast.LENGTH_SHORT).show();
        }
    }
}