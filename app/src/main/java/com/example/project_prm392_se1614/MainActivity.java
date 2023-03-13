package com.example.project_prm392_se1614;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392_se1614.jwtutil.JWTUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);
        if(token == null || !JWTUtil.isValid(token)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }else {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }

}