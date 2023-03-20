package com.example.project_prm392_se1614;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.dao.MyDatabase;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {

    MyDatabase database;
    private Food foodList1;
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
        foodList1 = database.getInstance(this).getFoodDao().getFoodById(FoodAdapters.foodId);
        User userID = database.getInstance(this).getUserDao().getUserById(userid);

        Resources resources = getResources();
        int imageResId =resources.getIdentifier(foodList1.getImage(), "drawable", getPackageName());


// Food image and name
        ImageView imgFoodDetail = findViewById(R.id.imgFoodDetail);
        imgFoodDetail.setImageResource(imageResId);

        TextView txtFoodDetail = findViewById(R.id.txtFoodDetail);

            txtFoodDetail.setText(foodList1.getFoodName());


// info User
        ImageView imgUserName = findViewById(R.id.imgUserName);
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imgUserName);

        TextView txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(userID.getName()); // get nameUser() from database

        TextView txtTagName = findViewById(R.id.txtTagName);
        txtTagName.setText("@"+userID.getEmail());

// getInstance list
        TextView txtMaterial = findViewById(R.id.txtMaterial);
            txtMaterial.setText(foodList1.getIngredient());

// step cooking
        TextView txtMaking = findViewById(R.id.txtMaking);
        // getStep from database

            txtMaking.setText(foodList1.getStep());


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