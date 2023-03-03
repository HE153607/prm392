package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FoodActivity extends AppCompatActivity {

    private EditText txtNameFood;
    private EditText txtSoNguoi;
    private EditText txtTime;
    private EditText txtNguyenLieu;
    private EditText txtCachLam;
    private Button btnThem;

    private void bindingView(){
        txtNameFood = findViewById(R.id.textNameOrder);
        txtSoNguoi = findViewById(R.id.textPerson);
        txtNguyenLieu = findViewById(R.id.textTime);
        txtTime = findViewById(R.id.textTime);
        txtCachLam = findViewById(R.id.textCachLam);
        btnThem = findViewById(R.id.btnThem);
    }

    private void bindingAction(){
            btnThem.setOnClickListener(this::onBtnThemMonAn);
    }

    private void onBtnThemMonAn(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createfoodlayout);
    }
}
