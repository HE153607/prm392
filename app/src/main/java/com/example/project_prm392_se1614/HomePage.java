package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView pig, fish, veget, beef, egg, chic, pota, toma, goat, xx, sheep, lang;
    private void bindingView(){
        discover = findViewById(R.id.discover);
        discoverList = findViewById(R.id.discoverList);
        search = findViewById(R.id.search);
        search_buttons = findViewById(R.id.search_button);
        pig = findViewById(R.id.pig);
        fish = findViewById(R.id.fish);
        veget = findViewById(R.id.veget);
        beef = findViewById(R.id.beef);
        egg = findViewById(R.id.egg);
        chic = findViewById(R.id.chic);
        pota = findViewById(R.id.pota);
        toma = findViewById(R.id.toma);
        goat = findViewById(R.id.goat);
        xx = findViewById(R.id.xx);
        sheep = findViewById(R.id.sheep);
        lang = findViewById(R.id.lang);
//        addname = findViewById(R.id.addname);
//        addrecipe = findViewById(R.id.addrecipe);
//        addstep = findViewById(R.id.addstep);
//        images = findViewById(R.id.images);
        //add = findViewById(R.id.add);
    }
    private void bindingAction(){
        discover.setOnClickListener(this::onDiscoverClick);
        search_buttons.setOnClickListener(this::onSearchClick);
        pig.setOnClickListener(this::onSelectpig);
        fish.setOnClickListener(this::onSelectfish);
        veget.setOnClickListener(this::onSelectveget);
        beef.setOnClickListener(this::onSelectbeef);
        egg.setOnClickListener(this::onSelectegg);
        chic.setOnClickListener(this::onSelectchic);
        pota.setOnClickListener(this::onSelectpota);
        toma.setOnClickListener(this::onSelecttoma);
        goat.setOnClickListener(this::onSelectgoat);
        xx.setOnClickListener(this::onSelectxx);
        sheep.setOnClickListener(this::onSelectsheep);
        lang.setOnClickListener(this::onSelectlang);
        //add.setOnClickListener(this::onAdd);
    }

    private void onDiscoverClick(View view) {
        foodAdapter = new FoodAdapters();
        foods = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        discoverList.setLayoutManager(linearLayoutManager);
        discoverList.setAdapter(foodAdapter);
        foods = MyDatabase.getInstance(this).getFoodDao().getAllFood();
        foodAdapter.setData(foods);
    }

    private void onSelectpig(View view) {
        String keyword = pig.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectfish(View view) {
        String keyword = fish.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectveget(View view) {
        String keyword = veget.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectbeef(View view) {
        String keyword = beef.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectegg(View view) {
        String keyword = egg.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectchic(View view) {
        String keyword = chic.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectpota(View view) {
        String keyword = pota.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelecttoma(View view) {
        String keyword = toma.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectgoat(View view) {
        String keyword = goat.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectxx(View view) {
        String keyword = xx.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectsheep(View view) {
        String keyword = sheep.getText().toString().trim();
        SelectList(keyword);
    }
    private void onSelectlang(View view) {
        String keyword = lang.getText().toString().trim();
        SelectList(keyword);
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
<<<<<<< HEAD
        foodAdapter = new FoodAdapters();
=======
//        foodAdapter = new FoodAdapter(new FoodAdapter.IClick() {
//            @Override
//            public void deleteFood(Food food) {
//                clickDeleteFood(food);
//            }
//        });
        foodAdapter = new FoodAdapter();
>>>>>>> parent of 6a5fc2c (Update Final code For HomePage)
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