package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    public TextView discover;
    public RecyclerView discoverList;
    private FoodAdapters foodAdapter;
    private List<Food> foods;
    private EditText addname;
    private EditText addrecipe;
    private EditText addstep;
    private EditText images;
    private EditText search;
    private Button add;
    private Button search_buttons;
    private TextView btn_list_food,urkit;
    private void bindingView(){
        urkit = findViewById(R.id.urkit);
        discover = findViewById(R.id.discover);
        discoverList = findViewById(R.id.discoverList);
        search = findViewById(R.id.search);
        search_buttons = findViewById(R.id.search_button);
        btn_list_food = findViewById(R.id.btn_list_food);
//        addname = findViewById(R.id.addname);
//        addrecipe = findViewById(R.id.addrecipe);
//        addstep = findViewById(R.id.addstep);
//        images = findViewById(R.id.images);
//        add = findViewById(R.id.addfood);
    }
    private void bindingAction(){
        discover.setOnClickListener(this::onDiscoverClick);
        search_buttons.setOnClickListener(this::onSearchClick);
        urkit.setOnClickListener(this::onYourKitchenClick);
//        btn_list_food.setOnClickListener(this::onClickListFood);
        //add.setOnClickListener(this::onAdd);
    }

    private void onYourKitchenClick(View view) {
        Intent i = new Intent(this,LoadFoodActivity.class);
        startActivity(i);
    }

//    private void onClickListFood(View view) {
//        Intent i = new Intent(this,UserActivity.class);
//        startActivity(i);
//    }

//    private void onAdd(View view) {
//        String name = addname.getText().toString().trim();
//        String recipe = addrecipe.getText().toString().trim();
//        String steps = addstep.getText().toString().trim();
//        String imagess = images.getText().toString().trim();
//        Food newf = new Food(name,null,null,recipe,steps,imagess,1,false);
//        MyDatabase.getInstance(this).getFoodDao().insertFood(newf);
////        User newU = new User(name,recipe,steps,false);
////        MyDatabase.getInstance(this).getUserDao().insertUser(newU);
//        Toast.makeText(this,"Add ok",Toast.LENGTH_SHORT).show();
//        addname.setText("");
//        addrecipe.setText("");
//        addstep.setText("");
//        images.setText("");
//    }

    private void onDiscoverClick(View view) {
        foodAdapter = new FoodAdapters();
        foods = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        discoverList.setLayoutManager(gridLayoutManager);
        discoverList.setAdapter(foodAdapter);
        foods = MyDatabase.getInstance(this).getFoodDao().getAllFood();
        foodAdapter.setData(foods);
    }




    private void onSearchClick(View view) {
        String keyword = search.getText().toString().trim();
        SelectList(keyword);
    }
    private void SelectList(String keyword){
        foods = new ArrayList<>();
        foods = MyDatabase.getInstance(this).getFoodDao().searchfood(keyword);
        foodAdapter.setData(foods);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bindingView();
        foodAdapter = new FoodAdapters();
//        foodAdapter = new FoodAdapter(new FoodAdapter.IClick() {
//            @Override
//            public void deleteFood(Food food) {
//                clickDeleteFood(food);
//            }
//        });
        foods = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        discoverList.setLayoutManager(gridLayoutManager);
        discoverList.setAdapter(foodAdapter);
        foods = MyDatabase.getInstance(this).getFoodDao().getAllFood();
        foodAdapter.setData(foods);
        bindingAction();

    }
//    private void LoadData(){
//        foods = MyDatabase.getInstance(this).getFoodDao().getAllFood();
//        foodAdapter.setData(foods);
//    }
//
//    private void clickDeleteFood(Food foodss) {
//         MyDatabase.getInstance(this).getFoodDao().deleteFood(foodss);
//         Toast.makeText(this,"Delete ok",Toast.LENGTH_SHORT).show();
//         LoadData();
//    }
}