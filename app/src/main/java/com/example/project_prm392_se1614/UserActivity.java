package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_prm392_se1614.dao.MyDatabase;
import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;

public class UserActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnUpdate;
        private User mUser;
    private void binddingView(){
        edtName = findViewById(R.id.edit_text_name);
        edtEmail = findViewById(R.id.edit_text_email);

        btnUpdate = findViewById(R.id.btn_update_user);
    }
    private void binddingAction(){
        btnUpdate.setOnClickListener(this::onUpdateClick);
    }

    private void onUpdateClick(View view) {
        String UserName = edtName.getText().toString();
        String Email = edtEmail.getText().toString();
        String Password = edtPassword.getText().toString();
        if(TextUtils.isEmpty(UserName) ||TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password) ){
            return;
        }
//        mUser.setName(UserName);
//        mUser.setEmail(Email);
//        mUser.setPassword(Password);
//        mUser.setActive(true);
        User user = new User(Password,UserName,Email,true);
        user.setRole(Role.USER);
        if(MyDatabase.getInstance(this).getUserDao().getUserByEmail(user.getEmail()) != null){
            Toast.makeText(this, "Email was available", Toast.LENGTH_SHORT).show();
            return;
        }
        MyDatabase.getInstance(this).getUserDao().updateUser(user);
        Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(this,FoodActivity.class);
//        startActivity(i);
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


    }
}