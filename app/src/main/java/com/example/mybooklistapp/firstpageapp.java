package com.example.mybooklistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class firstpageapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpageapp);

        getSupportActionBar().hide();

    }

    public void onClickFormLogin(View view) {
        Intent intent = new Intent(this,FormLogin.class);
        startActivity(intent);
    }
}