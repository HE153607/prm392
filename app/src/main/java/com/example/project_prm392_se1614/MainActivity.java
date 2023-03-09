package com.example.project_prm392_se1614;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392_se1614.entity.MyDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String token  = sharedPreferences.getString("user", null);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

//        if(name == null){
//            //TODO return login page
//        }
//        else{
//            // TODO forward to home page
//            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
//        }

    }
}