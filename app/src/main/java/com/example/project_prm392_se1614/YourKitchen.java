package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class YourKitchen extends AppCompatActivity {
    private Button back;
    private void bindingView(){
        back = findViewById(R.id.back);
    }
    private void bindingAction(){
        back.setOnClickListener(this::onBackClick);
    }

    private void onBackClick(View view) {
        Intent intent =  new Intent(YourKitchen.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kitchen);
        bindingView();
        bindingAction();
    }
}