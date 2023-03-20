package com.example.project_prm392_se1614;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392_se1614.dao.MyDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfirmMailPassActivity extends AppCompatActivity {

    MyDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirm_pass);
        database = MyDatabase.getInstance(this);
    }

    void onSaveChange(View view){
        File cacheDir = this.getCacheDir();
        File cacheFile = new File(cacheDir, "uuid_forgot_password.txt");

        try {
            FileInputStream inputStream = new FileInputStream(cacheFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            inputStream.close();
            String content = stringBuilder.toString();
            if(content != null){
                EditText code = findViewById(R.id.code);
                if(!code.getText().toString().trim().equals(content.trim())){
                    Toast.makeText(this, "Verify code incorrect", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(this, "Verify code successful", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
