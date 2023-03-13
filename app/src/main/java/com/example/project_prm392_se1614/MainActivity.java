package com.example.project_prm392_se1614;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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
        Intent i = new Intent(MainActivity.this,FoodMainActivity.class);
        startActivity(i);
    }
}