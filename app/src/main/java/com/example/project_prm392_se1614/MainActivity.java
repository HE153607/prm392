package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public TextView discover;
    public TextView urkit;
    public RecyclerView discoverList;
    private FoodAdapter foodAdapter;
    private List<Food> foods;
    private EditText addname;
    private EditText addrecipe;
    private EditText addstep;
    private EditText images;
    private EditText search;
    private Button add;
    private Button search_buttons;
    private void bindingView(){
        discover = findViewById(R.id.discover);
        discoverList = findViewById(R.id.discoverList);
        urkit = findViewById(R.id.urkit);
        search = findViewById(R.id.search);
        search_buttons = findViewById(R.id.search_button);
//        addname = findViewById(R.id.addname);
//        addrecipe = findViewById(R.id.addrecipe);
//        addstep = findViewById(R.id.addstep);
//        images = findViewById(R.id.images);
//        add = findViewById(R.id.add);
    }
    private void bindingAction(){
        urkit.setOnClickListener(this::onUrkitClick);
        search_buttons.setOnClickListener(this::onSearchClick);
        //add.setOnClickListener(this::onAdd);
    }

    private void onSearchClick(View view) {
        String keyword = search.getText().toString().trim();
        foods = new ArrayList<>();
        foods = MyDatabase.getInstance(this).getFoodDao().searchfood(keyword);
        foodAdapter.setData(foods);
    }

    private void onUrkitClick(View view) {
        Intent intent = new Intent(MainActivity.this,YourKitchen.class);
        startActivity(intent);
    }

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
//        foodAdapter = new FoodAdapter(new FoodAdapter.IClick() {
//            @Override
//            public void deleteFood(Food food) {
//                clickDeleteFood(food);
//            }
//        });
        foodAdapter = new FoodAdapter();
        foods = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        discoverList.setLayoutManager(linearLayoutManager);
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