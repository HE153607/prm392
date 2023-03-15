package com.example.project_prm392_se1614;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {

    MyDatabase database;
    private List<Food> foodList;
    public RecyclerView discoverList;
    private FoodDetailAdapter foodDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);
        setContentView(R.layout.activity_food_details);
        User user = JWTUtil.extractToken(token);
        int userid = user.getId();
        foodList = database.getInstance(this).getFoodDao().getFoodById(userid);
        User userID = database.getInstance(this).getUserDao().getUserById(userid);

// Food image and name
        ImageView imgFoodDetail = findViewById(R.id.imgFoodDetail);
        imgFoodDetail.setImageResource(R.drawable.img2);

        TextView txtFoodDetail = findViewById(R.id.txtFoodDetail);
        for (Food food : foodList) {
            txtFoodDetail.setText(food.getFoodName());
        }

// info User
        ImageView imgUserName = findViewById(R.id.imgUserName);
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imgUserName);

        TextView txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(userID.getName()); // get nameUser() from database

        TextView txtTagName = findViewById(R.id.txtTagName);
        txtTagName.setText("@"+userID.getEmail());

// getInstance list
        TextView txtMaterial = findViewById(R.id.txtMaterial);
        for (Food food : foodList) {
            txtMaterial.setText(food.getIngredient());
        }
// step cooking
        TextView txtMaking = findViewById(R.id.txtMaking);
        // getStep from database
        for (Food food : foodList) {
            txtMaking.setText(food.getStep());
        }

// info post created by user
        ImageView imgByUserName = findViewById(R.id.imgByUser);
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imgByUserName);


        TextView txtByUser = findViewById(R.id.txtByUser);
        txtByUser.setText(user.getName());

// comment function
        TextView txtComment = findViewById(R.id.txtComment);
        EditText textComment = findViewById(R.id.textViewComment);
        ImageView imgSend = findViewById(R.id.imgSend);
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtComment.setText(textComment.getText());
                textComment.setText("");
            }
        });

        foodDetailAdapter = new FoodDetailAdapter();
        foodList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3); // số cột bạn muốn hiển thị
        discoverList = findViewById(R.id.discoverList);
        discoverList.setLayoutManager(gridLayoutManager);
        discoverList.setAdapter(foodDetailAdapter);
        foodList = MyDatabase.getInstance(this).getFoodDao().getAllFood();
        foodDetailAdapter.setData(foodList);
    }
}