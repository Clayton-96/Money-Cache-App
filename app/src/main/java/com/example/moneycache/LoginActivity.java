package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //as soon as successful Login has happened, retrieve Bank data for this user
        //TODO: will need to specify user at some point
        controller.retrieveBankData();

    }
}