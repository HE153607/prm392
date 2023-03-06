package com.example.project_prm392_se1614;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.MyDatabase;
import com.example.project_prm392_se1614.entity.User;

import java.util.List;

public class UserDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detial);
    }

    void LoadData(){
        // Retrieve the account data from the database using the AccountDao
        int userId = 123; // Replace with the ID of the account you want to load
        User user = MyDatabase.getInstance(this).getUserDao().getUserById(userId);

// Set the values of the views in your layout to the retrieved data
        TextView textViewName = findViewById(R.id.text_view_name);
        textViewName.setText(user.getName());

        TextView textViewEmail = findViewById(R.id.text_view_email);
        textViewEmail.setText(user.getEmail());

        TextView textViewPassword = findViewById(R.id.text_view_password);
        textViewPassword.setText(user.getPassword());

        ImageView imageViewProfile = findViewById(R.id.image_view_profile);
        Glide.with(this)
                .load(user.getProfileImage())
                .into(imageViewProfile);

// Populate the GridView with the user's items
        GridView gridViewItems = findViewById(R.id.grid_view_items);
        List<Food> items = MyDatabase.getInstance(this).getUserDao().getItemsForAccount(userId);
//        ItemAdapter itemAdapter = new ItemAdapter(this, items); // Create an adapter for the items
//        gridViewItems.setAdapter((ListAdapter) itemAdapter); // Set the adapter on the GridView

    }
}
