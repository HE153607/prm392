package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;


public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ImageView imgUser = findViewById(R.id.imgUser);
        imgUser.setImageResource(R.drawable.banhkhoai);
    }
}