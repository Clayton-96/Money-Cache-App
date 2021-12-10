package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    LoginController controller;
    EditText name, password;
    Button go, newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("MoneyCache Login");
        controller = new LoginController(this);
        name = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        go = findViewById(R.id.go);
        newUser = findViewById(R.id.new_user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //as soon as successful Login has happened, retrieve Bank data for this user
        //TODO: will need to specify user at some point
        //controller.retrieveBankData();

    }

    /**
     * Login check credentials
     * @param view 'Go' button
     * author: Dixie Cravens
     */
    public void loginVerify(View view){
        String enteredName = (name.getText().toString());
        String enteredPassword = (password.getText().toString());
        String savedName = "";
        String savedPassword = "";
        //check what is in SharedPref with what was entered. Should have started with new user prompt.
        SharedPreferences sp = getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        if (sp.contains("username")) {
            savedName = sp.getString("username", "");
        }
        if (sp.contains("password")) {
            savedPassword = sp.getString("password", "");
        }
        if (enteredName.equals(savedName) && enteredPassword.equals(savedPassword)) {
            //if login is correct, open Dashboard Activity view
            controller.start();
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            //probably need to do other things to get data going

        } else {
            Toast.makeText(this, "Sorry. Login failed. Try again.", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * Sends a new user to the Profile Activity to set up a new account
     * @param view 'New User' button
     * author: Dixie Cravens
     */
    public void newUserPrompt(View view) {
        //push user to profile view to set up account
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}