package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.UserDao;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {

    MyDatabase database;
    String[] listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        int userId = 123; // Replace with the ID of the account you want to load
        Food food = database.getInstance(this).getFoodDao().getFoodById(userId);
        User user = database.getInstance(this).getUserDao().getUserById(userId);

        ImageView imgFoodDetail = findViewById(R.id.imgFoodDetail);
        Glide.with(this)
                .load("https://cdn.tgdd.vn/2021/06/CookProduct/t1-1200x676-10.jpg") //no data img demo
//                .load(food.getFoodImage())
                .into(imgFoodDetail);

        ImageView imgUserName = findViewById(R.id.imgUserName);
        Glide.with(this)
                .load("https://assets.stickpng.com/images/585e4bcdcb11b227491c3396.png") //no data img demo
//                .load(user.getProfileImage())
                .into(imgUserName);

        TextView txtFoodDetail = findViewById(R.id.txtFoodDetail);
        txtFoodDetail.setText("Khoai tây chiên bơ tỏi");
//        txtFoodDetail.setText(food.getFoodName());

        TextView txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText("Hoang");
//        txtUserName.setText(user.getName());

        TextView txtTagName = findViewById(R.id.txtTagName);
        txtTagName.setText("@Hoang");

        ListView listView = findViewById(R.id.listMaterial);
//        TextView textView= (TextView) findViewById(R.id.textList);
        listItem = getResources().getStringArray(R.array.array_technology);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.mylist, R.id.textView, listItem);
        listView.setAdapter(adapter);
//        List<Food> items = database.getInstance(this).getFoodDao().getFoodById(id).getIngredient();
//        MyAdapter adapter = new MyAdapter(this, items);
//        listView.setAdapter((ListAdapter) itemAdapter);

        TextView txtMaking = findViewById(R.id.txtMaking);
        txtMaking.setText("Sơ chế khoai tây\n" +
                "Với 4 củ khoai tây sau khi mua về, bạn rửa thật kỹ và sạch bùn đất bám ở lớp vỏ bên ngoài.\n" +
                "\n" +
                "Bật bếp với lửa vừa, cho 1 lít nước vào nồi đun sôi rồi cho khoai tây vào trụng sơ qua trong 5 phút, sau đó vớt ra, để ráo.\n" +
                "\n" +
                "Cuối cùng, bạn cắt khoai tây thành 4 phần đều nhau là được.");
//        txtMaking.setText(food.getStep());

        ImageView imgByUserName = findViewById(R.id.imgByUser);
        Glide.with(this)
                .load("https://assets.stickpng.com/images/585e4bcdcb11b227491c3396.png") //no data img demo
//                .load(user.getProfileImage())
                .into(imgByUserName);

        TextView txtByUser = findViewById(R.id.txtByUser);
        txtByUser.setText("Hoàng");
//        txtByUser.setText(user.getName());

        TextView txtComment =findViewById(R.id.txtComment);
        EditText textComment =findViewById(R.id.textViewComment);
        ImageView imgSend = findViewById(R.id.imgSend);
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtComment.setText(textComment.getText());
                textComment.setText("");
            }
        });
    }
//    private List<Food> getFood() {
//        list.add(new Model("Linux"));
//        list.add(new Model("Windows7"));
//        list.add(new Model("Suse"));
//        list.add(new Model("Eclipse"));
//        list.add(new Model("Ubuntu"));
//        list.add(new Model("Solaris"));
//        list.add(new Model("Android"));
//        list.add(new Model("iPhone"));
//        list.add(new Model("Java"));
//        list.add(new Model(".Net"));
//        list.add(new Model("PHP"));
//        return list;
//    }
}
