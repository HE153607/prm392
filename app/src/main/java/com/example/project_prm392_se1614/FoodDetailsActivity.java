package com.example.project_prm392_se1614;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.project_prm392_se1614.entity.Review;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {


    private Food foodDetail;
    private List<Food> foodList;
    public RecyclerView discoverList;
    private FoodDetailAdapter foodDetailAdapter;
    private  ImageView imgUserName;
    private TextView txtUserName;
    private  TextView txtTagName;
    private TextView txtMaterial;
    private TextView txtMaking;
    private  ImageView imgByUserName;
    private TextView txtByUser;
    private  TextView txtComment;
    private  TextView txtFoodDetail;
    private  EditText textComment;
    private ImageView imgSend;
    private ImageView imageFood;
    public void bindingView(){
        imgUserName = findViewById(R.id.imgUserName);
         txtUserName = findViewById(R.id.txtUserName);
         txtTagName = findViewById(R.id.txtTagName);
         txtMaterial = findViewById(R.id.txtMaterial);
         txtMaking = findViewById(R.id.txtMaking);
         imgByUserName = findViewById(R.id.imgByUser);
         txtByUser = findViewById(R.id.txtByUser);
         txtComment = findViewById(R.id.txtComment);
         textComment = findViewById(R.id.textViewComment);
         imgSend = findViewById(R.id.imgSend);
        imageFood = findViewById(R.id.imgFoodDetail);
        txtFoodDetail =findViewById(R.id.txtFoodDetail);
    }

    private void bindingAction(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);
        bindingView();
        User user = JWTUtil.extractToken(token);

        foodDetail = (Food) getIntent().getExtras().get("object_detail_food");
        User userID = MyDatabase.getInstance(this).getUserDao().getUserById(user.getId());
        txtUserName.setText(userID.getName());
        txtByUser.setText(userID.getName());
        txtTagName.setText(userID.getEmail());

        if(foodDetail != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodDetail.getImage(), 0, foodDetail.getImage().length);
            imageFood.setImageBitmap(bitmap);
            txtFoodDetail.setText(foodDetail.getFoodName());
            txtMaterial.setText(foodDetail.getIngredient());
            txtMaking.setText(foodDetail.getStep());

        }
        final Review review = new Review();
        txtComment.getText().toString().trim();

//        imgSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                txtComment.setText(textComment.getText());
//                textComment.setText("");
//            }
//        });

        foodDetailAdapter = new FoodDetailAdapter();
        foodList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3); // số cột bạn muốn hiển thị
        discoverList = findViewById(R.id.discoverList);
        discoverList.setLayoutManager(gridLayoutManager);
        discoverList.setAdapter(foodDetailAdapter);
//        foodList = MyDatabase.getInstance(this).getFoodDao().getAllFood();
        foodDetailAdapter.setData(foodList);
    }
}