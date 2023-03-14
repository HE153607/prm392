package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;

public class UserActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnUpdate;

    private void binddingView(){
        edtName = findViewById(R.id.edit_text_name);
        edtEmail = findViewById(R.id.edit_text_email);
        edtPassword = findViewById(R.id.edit_text_password);
        btnUpdate = findViewById(R.id.btn_update_user);
    }
    private void binddingAction(){
        btnUpdate.setOnClickListener(this::onUpdateClick);
    }

    private void onUpdateClick(View view) {
        Intent i = new Intent(this,LoadFoodActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        binddingView();
        binddingAction();

        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);

        if(token == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        User user = JWTUtil.extractToken(token);

        edtName.setText(user.getName());
        edtEmail.setText(user.getEmail());
        edtPassword.setText(user.getName());

    }
}