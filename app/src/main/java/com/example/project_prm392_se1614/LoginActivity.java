package com.example.project_prm392_se1614;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.auth0.jwt.JWO;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.entity.UserDao;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.time.LocalDate;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private static final String KEY_TOKEN = "key_access_token";
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        TextView textView = findViewById(R.id.signup);
        textView.setOnClickListener(this::viewRegisterClicked);

        Button button = findViewById(R.id.cirLoginButton);
        button.setOnClickListener(this::onLogin);

        TextView forgotPass = findViewById(R.id.forgetPass);
        forgotPass.setOnClickListener(this::forgotPassword);

        database = MyDatabase.getInstance(this);
    }

    private void forgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassWordActivity.class);
        startActivity(intent);
    }

    private void onLogin(View view) {
        UserDao dao = database.getUserDao();

        EditText pass = findViewById(R.id.editTextPassword);
        EditText email = findViewById(R.id.editTextEmail);
        User user = dao.getUserByPassAndEmail(pass.getText().toString(), email.getText().toString());

        if(user != null){
        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = session.edit();

        editor.putString("user", JWTUtil.GenToken(user));
        editor.apply();

        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

        }else {
            Toast.makeText(this, "email or password incorrect", Toast.LENGTH_SHORT).show();
        }

    }

    private void viewRegisterClicked(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}