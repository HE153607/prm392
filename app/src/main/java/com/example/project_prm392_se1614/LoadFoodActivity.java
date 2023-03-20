package com.example.project_prm392_se1614;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.dao.MyDatabase;
import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.jwtutil.JWTUtil;

import java.util.ArrayList;
import java.util.List;

public class LoadFoodActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private RecyclerView rv;
    private Button btnAdd;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private EditText edtSearch;
    private Button addfood,btnLogout;
    private void bindingView(){
        rv = findViewById(R.id.rvData);
        btnAdd = findViewById(R.id.btnAdd);
        addfood = findViewById(R.id.addfood);
        btnLogout = findViewById(R.id.btnlogout);
//        edtSearch = findViewById(R.id.edt_search);


    }
    private void bindingAction(){
//       btnAdd.setOnClickListener(this::onClickButtonThem);
        addfood.setOnClickListener(this::onAddFoodClick);
        btnLogout.setOnClickListener(this::onLogoutClick);
    }

    private void onLogoutClick(View view) {
        SharedPreferences s = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();
        e.clear();
        e.apply();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void onAddFoodClick(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }

    private void loadData(){
        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);
        if(token == null || !JWTUtil.isValid(token)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        User user = JWTUtil.extractToken(token);
        int id = user.getId();
        foodList = MyDatabase.getInstance(this).getFoodDao().getFoods();
        foodAdapter.SetData(foodList);
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
                        loadData();
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
        foodList = MyDatabase.getInstance(this).getFoodDao().searchfood(keyword);
        foodAdapter.SetData(foodList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem_layout);
        bindingView();


        SharedPreferences session = getSharedPreferences("login", MODE_PRIVATE);
        String token = session.getString("user",null);
        if(token == null || !JWTUtil.isValid(token)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        User user = JWTUtil.extractToken(token);
        int userid= user.getId();

        foodAdapter = new FoodAdapter(new FoodAdapter.IClickFood() {
            @Override
            public void updateFood(Food food) {
                loadData();
                clickUpdateFood(food);
            }

            @Override
            public void deleteFood(Food food) {
                clickDeleteFood(food);
            }
        });
//        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if(actionId == EditorInfo.IME_ACTION_SEARCH){
//                    SearchFood();
//                }
//                return false;
//            }
//
//
//        });
        foodList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(foodAdapter);
        if(user.getRole() == Role.ADMIN){
            foodList = MyDatabase.getInstance(this).getFoodDao().getFoods();
        }else{
            foodList = MyDatabase.getInstance(this).getFoodDao().getListFoodsById(user.getId());

        }

        foodAdapter.SetData(foodList);
        bindingAction();
    }
}