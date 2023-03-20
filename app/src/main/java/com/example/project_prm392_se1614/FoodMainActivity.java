package com.example.project_prm392_se1614;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.dao.MyDatabase;

import java.io.InputStream;

public class FoodMainActivity extends AppCompatActivity {
    private EditText txtNameFood;
    private EditText txtSoNguoi;
    private EditText txtTime;
    private EditText txtNguyenLieu;
    private EditText txtCachLam;
    private Button btnThem;
    private Button btnImg;
    private ImageView imgFood;
    private String selectedImagePath;
    private static final int REQUEST_CODE_STORAGE_PERMISSION =1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private void bindingView(){
        txtNameFood = findViewById(R.id.textNameOrder);
        txtSoNguoi = findViewById(R.id.textPerson);
        txtNguyenLieu = findViewById(R.id.textTime);
        txtTime = findViewById(R.id.textTime);
        txtCachLam = findViewById(R.id.textCachLam);
        btnThem = findViewById(R.id.btnThem);
        btnImg = findViewById(R.id.btnImg);
        imgFood = findViewById(R.id.imgFood);
        selectedImagePath="";
    }

    private void bindingAction(){

        btnThem.setOnClickListener(this::onBtnThemMonAn);
        btnImg.setOnClickListener(this::onBtnImgClick);
    }

    public void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }else{
                Toast.makeText(this, "Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                Uri selectedImageUri = data.getData();
                if(selectedImageUri != null){
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgFood.setImageBitmap(bitmap);
                        imgFood.setVisibility(View.VISIBLE);
                        selectedImagePath = getPathFromUri(selectedImageUri);
                    }catch (Exception e){
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void onBtnImgClick(View view) {
        selectImage();
    }
    private String getPathFromUri(Uri contentUri){
        String filePath;
        Cursor cursor = getContentResolver().query(contentUri,null,null,null,null);
        if(cursor == null){
            filePath = contentUri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
    private void onBtnThemMonAn(View view) {
        Food food = new Food();
        food.setId(1);
        food.setFoodName(txtNameFood.getText().toString());
        food.setTime(txtTime.getText().toString());
        food.setActive(true);
        food.setStep(txtCachLam.getText().toString());
        food.setIngredient(txtNguyenLieu.getText().toString());
        food.setRation(txtSoNguoi.getText().toString());
        food.setImage(selectedImagePath);
        food.setUserId(1);



//        String foodname = txtNameFood.getText().toString();
//        String songuoi = txtSoNguoi.getText().toString();
//        String nguyenlieu = txtNguyenLieu.getText().toString();
//        String thoigian = txtTime.getText().toString();
//        String cachlam = txtCachLam.getText().toString();
//        Food food = new Food(1,foodname,songuoi,thoigian,nguyenlieu,cachlam,null, 1,true);
        MyDatabase.getInstance(this).getFoodDao().insert(food);
        Toast.makeText(this, "Add Succesully", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createfoodlayout);
        bindingView();
        bindingAction();
    }
}