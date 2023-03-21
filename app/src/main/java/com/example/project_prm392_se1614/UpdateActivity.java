package com.example.project_prm392_se1614;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.project_prm392_se1614.entity.Material;
import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    private EditText txtNameFood;
    private EditText txtSoNguoi;
    private EditText txtTime;
    private EditText txtNguyenLieu;
    private EditText txtCachLam;
    private Button btnUpdate;
    private Food lFood;

    private Button btnImg;
    private ImageView imgFood;
    private String selectedImagePath;
    private static final int REQUEST_CODE_STORAGE_PERMISSION =1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private void bindingView(){
        txtNameFood = findViewById(R.id.textNameOrder);
        txtSoNguoi = findViewById(R.id.textPerson);
        txtNguyenLieu = findViewById(R.id.textNguyenLieu);
        txtTime = findViewById(R.id.textTime);
        txtCachLam = findViewById(R.id.textCachLam);
        btnUpdate = findViewById(R.id.btnThem);
        btnImg = findViewById(R.id.btnImg);
        imgFood = findViewById(R.id.imgFood);
        selectedImagePath="";
    }

    private void bindingAction(){

        btnImg.setOnClickListener(this::onBtnImgClick);
    }

    private void onBtnImgClick(View view) {
        selectImage();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        bindingView();
        bindingAction();
        lFood = (Food) getIntent().getExtras().get("object_food");
        if(lFood != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(lFood.getImage(), 0, lFood.getImage().length);
            imgFood.setImageBitmap(bitmap);
            txtNameFood.setText(lFood.getFoodName());
            txtNguyenLieu.setText(lFood.getIngredient());
            txtCachLam.setText(lFood.getStep());

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();
            }
        });
    }
    private byte[] ImageView_To_Byte(ImageView img) {
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();

        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void updateFood(){
        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);
        if(token == null || !JWTUtil.isValid(token)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        User user = JWTUtil.extractToken(token);
//        String foodname  = txtNameFood.getText().toString();
//        String songuoi = txtSoNguoi.getText().toString();
//        String nguyenlieu = txtNguyenLieu.getText().toString();
//        String thoigian = txtTime.getText().toString();
//        String cachlam = txtCachLam.getText().toString();
//
//        lFood.setFoodName(txtNameFood.getText().toString());
//        lFood.setTime(txtTime.getText().toString());
//        lFood.setActive(true);
//        lFood.setStep(txtCachLam.getText().toString());
//        lFood.setIngredient(txtNguyenLieu.getText().toString());
//        lFood.setRation(txtSoNguoi.getText().toString());
//        lFood.setImage(selectedImagePath);
//        lFood.setUserId(user.getId());

        if(user.getRole() == Role.ADMIN){
            lFood.setId(lFood.getId());
            lFood.setFoodName(txtNameFood.getText().toString());
            lFood.setIngredient(txtNguyenLieu.getText().toString());
            lFood.setStatus(1);
            lFood.setImage(ImageView_To_Byte(imgFood));
            lFood.setUserId(user.getId());
            lFood.setStep(txtCachLam.getText().toString());
        }else if(user.getRole() == Role.USER){
            lFood.setId(lFood.getId());
            lFood.setFoodName(txtNameFood.getText().toString());
            lFood.setStep(txtCachLam.getText().toString());
            lFood.setIngredient("a");
            lFood.setImage(ImageView_To_Byte(imgFood));
            lFood.setUserId(user.getId());
            lFood.setStatus(0);

        }
        Material Material  = new Material(lFood.getIngredient(), true);
        Material Material1  = new Material("Cu", true);
        List<Material> a = new ArrayList<>();
        a.add(Material);
        a.add(Material1);

        MyDatabase.getInstance(this).getFoodDao().insertFoodWithMaterials(this,lFood,a);
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent(this,LoadFoodActivity.class);
        startActivity(intentResult);
        setResult(Activity.RESULT_OK,intentResult);
        finish();
    }
}