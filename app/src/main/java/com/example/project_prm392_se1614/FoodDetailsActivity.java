package com.example.project_prm392_se1614;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.User;

import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {

    MyDatabase database;

    // fake data getIngredient()
    String[] ListElements = new String[]{
            "Tỏi",
            "Nước Mắm",
            "Bơ Tường An",
            "Khoai tây"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        int userId = 123; // Replace with the ID of the account you want to load
        List<Food > food = database.getInstance(this).getFoodDao().getFoodById(userId);
        User user = database.getInstance(this).getUserDao().getUserById(userId);

// Food image and name
        ImageView imgFoodDetail = findViewById(R.id.imgFoodDetail);
        Glide.with(this)
                .load("https://cdn.tgdd.vn/2021/06/CookProduct/t1-1200x676-10.jpg") //no data img demo
//                .load(food.getFoodImage())
                .into(imgFoodDetail);
        TextView txtFoodDetail = findViewById(R.id.txtFoodDetail);
        txtFoodDetail.setText("Khoai tây chiên bơ tỏi"); // nameFood() demo
//        txtFoodDetail.setText(food.getFoodName()); // get nameFood() from database

// info User
        ImageView imgUserName = findViewById(R.id.imgUserName);
        Glide.with(this)
                .load("https://assets.stickpng.com/images/585e4bcdcb11b227491c3396.png") //no data img demo
//                .load(user.getProfileImage())
                .into(imgUserName);

        TextView txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText("Hoang");// get nameUser() demo
//        txtUserName.setText(user.getName()); // get nameUser() from database

//        TextView txtTagName = findViewById(R.id.txtTagName);
//        txtTagName.setText("@Hoang");

// getInstance list
        ListView listView = findViewById(R.id.listMaterial);
//        TextView textView= (TextView) findViewById(R.id.textList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.instance_list_layout, R.id.textView, ListElements);
        listView.setAdapter(adapter);
//        List<Food> items = database.getInstance(this).getFoodDao().getFoodById(id).getIngredient();
//        MyAdapter adapter = new MyAdapter(this, items);
//        listView.setAdapter((ListAdapter) itemAdapter);

// step cooking
        TextView txtMaking = findViewById(R.id.txtMaking);
        // getStep() demo
        txtMaking.setText("Sơ chế khoai tây\n" +
                "Với 4 củ khoai tây sau khi mua về, bạn rửa thật kỹ và sạch bùn đất bám ở lớp vỏ bên ngoài.\n" +
                "\n" +
                "Bật bếp với lửa vừa, cho 1 lít nước vào nồi đun sôi rồi cho khoai tây vào trụng sơ qua trong 5 phút, sau đó vớt ra, để ráo.\n" +
                "\n" +
                "Cuối cùng, bạn cắt khoai tây thành 4 phần đều nhau là được.");
        // getStep from database
//        txtMaking.setText(food.getStep());

// info post created by user
        ImageView imgByUserName = findViewById(R.id.imgByUser);
        Glide.with(this)
                .load("https://assets.stickpng.com/images/585e4bcdcb11b227491c3396.png") //no data img demo
//                .load(user.getProfileImage())
                .into(imgByUserName);

        TextView txtByUser = findViewById(R.id.txtByUser);
        txtByUser.setText("Hoàng");
//        txtByUser.setText(user.getName());

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

        TextView txtNewFood = findViewById(R.id.newFoodByUser);
        txtNewFood.setText("Những món mới của Hoang");
//        txtNewFood.setText("Những món mới của"+ user.getName());

//        ArrayList foodByUserData=new ArrayList<>();
//        GridView simpleList = (GridView) findViewById(R.id.gridview);
//        foodByUserData.add(new ItemFood("Mon 1",R.drawable.menu_icon));
//        foodByUserData.add(new ItemFood("Mon 2",R.drawable.menu_icon));
//        foodByUserData.add(new ItemFood("Mon 3",R.drawable.menu_icon));
//        foodByUserData.add(new ItemFood("Mon 4",R.drawable.menu_icon));
//        foodByUserData.add(new ItemFood("Mon 5",R.drawable.menu_icon));
//        foodByUserData.add(new ItemFood("Mon 6",R.drawable.menu_icon));
//
//        MyAdapter myAdapter=new MyAdapter(this,R.layout.activity_food_details,foodByUserData);
//        simpleList.setAdapter(myAdapter);
    }
}

