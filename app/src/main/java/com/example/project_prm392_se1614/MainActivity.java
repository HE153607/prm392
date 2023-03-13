package com.example.project_prm392_se1614;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Button createlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    createlayout = findViewById(R.id.button2);

    createlayout.setOnClickListener(this::OnclickUpdate);
    }

    private void OnclickUpdate(View view) {
        Intent i = new Intent(MainActivity.this,LoadFoodActivity.class);
        startActivity(i);
    }
}