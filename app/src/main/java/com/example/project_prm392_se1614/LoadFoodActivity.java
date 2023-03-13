package com.example.project_prm392_se1614;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoadFoodActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private RecyclerView rv;
    private Button btnAdd;
    private FoodAdapters foodAdapter;
    private List<Food> foodList;
    private EditText edtSearch;
    private void bindingView(){
        rv= findViewById(R.id.rvData);
        btnAdd = findViewById(R.id.btnAdd);
        edtSearch = findViewById(R.id.edt_search);
    }
    private void bindingAction(){
        btnAdd.setOnClickListener(this::onClickButtonThem);
    }
    private void loadData(){
        foodList = MyDatabase.getInstance(this).getFoodDao().getFoods();

    }
    private void onClickButtonThem(View view) {
        Intent intent = new Intent(this,FoodActivity.class);
        startActivity(intent);
    }
    private void clickUpdateFood(Food food) {
        Intent intent = new Intent(this,UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_food",food);
        intent.putExtras(bundle);
        startActivityForResult(intent,MY_REQUEST_CODE);
    }

    private void clickDeleteFood(final Food food){
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete food")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            MyDatabase.getInstance(LoadFoodActivity.this).getFoodDao().deleteFood(food);
                        Toast.makeText(LoadFoodActivity.this, "Delete food successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void SearchFood() {
        String keyword = edtSearch.getText().toString().trim();
        foodList = new ArrayList<>();
//        foodList = MyDatabase.getInstance(this).getFoodDao().searchFood(keyword);
//        foodAdapter.SetData(foodList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem_layout);
        bindingView();
        bindingAction();

        foodList = MyDatabase.getInstance(this).getFoodDao().getFoods();

        foodAdapter = new FoodAdapters(new FoodAdapters.IClickFood() {
            @Override
            public void updateFood(Food food) {
                clickUpdateFood(food);
            }

            @Override
            public void deleteFood(Food food) {
                clickDeleteFood(food);
            }
        });
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    SearchFood();
                }
                return false;
            }


        });
        foodList = new ArrayList<>();
        foodAdapter.SetData(foodList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(foodAdapter);
    }
}