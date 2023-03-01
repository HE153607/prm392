package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.UserDao;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("logins", MODE_PRIVATE);
        String name  = sharedPreferences.getString("user", null);
        if(name == null){
            Intent intent =  new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }

    }
}