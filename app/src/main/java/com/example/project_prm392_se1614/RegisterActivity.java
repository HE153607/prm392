package com.example.project_prm392_se1614;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.entity.User;

import java.util.logging.Logger;

public class RegisterActivity extends AppCompatActivity {

    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        database = MyDatabase.getInstance(this);

        TextView signIn = findViewById(R.id.signin);
        signIn.setOnClickListener(this::signin);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(this::register);
    }

    private void signin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean formatString(String string){
        return string != null && !string.trim().equals("");
    }

    private void register(View view) {
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.pass);
        EditText cfPass = findViewById(R.id.cfPass);

        if(!formatString(name.getText().toString())){
            Toast.makeText(this, "Name can't skip", Toast.LENGTH_LONG).show();
            return;
        }
        if(!formatString(email.getText().toString())){
            Toast.makeText(this, "Email required", Toast.LENGTH_LONG).show();
            return;
        }
        if(!formatString(pass.getText().toString())){
            Toast.makeText(this, "Password required", Toast.LENGTH_LONG).show();
            return;
        }
        if(!formatString(cfPass.getText().toString())){
            Toast.makeText(this, "Confirm password required", Toast.LENGTH_LONG).show();
            return;
        }
        if(!cfPass.getText().toString().trim().equals(cfPass.getText().toString().trim())){
            Toast.makeText(this, "Confirm password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User(pass.getText().toString().trim(),
                name.getText().toString().trim(),
                email.getText().toString().trim(),
                true);
        user.setRole(Role.USER);
        try {
            if(database.getUserDao().getUserByEmail(user.getEmail()) != null){
                Toast.makeText(this, "Email was available", Toast.LENGTH_SHORT).show();
                return;
            }
            database.getUserDao().insertUser(user);
            Toast.makeText(this, "create account successful", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Error Register ", "can't create account cause "+e.getMessage());
            Toast.makeText(this, "request Register account Failed", Toast.LENGTH_SHORT).show();
        }
    }

}
