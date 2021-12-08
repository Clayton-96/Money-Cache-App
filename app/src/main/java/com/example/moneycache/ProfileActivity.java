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

public class ProfileActivity extends AppCompatActivity {
    ProfileController controller;
    EditText name, password, email;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("User Profile");
        controller = new ProfileController(this);
        name = findViewById(R.id.profile_Username);
        password = findViewById(R.id.profile_Password);
        email = findViewById(R.id.profile_EmailAddress);
        update = findViewById(R.id.profile_update);
    }

    public void onUpdate (View view) {
        SharedPreferences sp = getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", name.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.commit();
        Toast.makeText(this, "User info updated. Hello " + name.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditBudgetActivity.class);
        startActivity(intent);
    }
}