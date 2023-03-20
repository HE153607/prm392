package com.example.project_prm392_se1614.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.project_prm392_se1614.converter.RoleConverter;
import com.example.project_prm392_se1614.entity.Evaluate;
import com.example.project_prm392_se1614.entity.FoodMaterial;
import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.repository.EvaluateDao;
import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.repository.FoodDao;
import com.example.project_prm392_se1614.entity.User;
import com.example.project_prm392_se1614.repository.UserDao;

@Database(entities = {User.class, Food.class, Evaluate.class}, version = 1)
@TypeConverters({RoleConverter.class})
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    public abstract EvaluateDao getEvaluateDao();

    public abstract FoodDao getFoodDao();

    public abstract MaterialDAO getmaterialDAO();
    public abstract FoodMaterialDAO getfoodMaterialDAO();
    public abstract ReviewDAO getreviewDAO();
    public abstract RoleDAO getroleDAO();
    public abstract StatusFoodDAO getstatusFoodDAO();
    public abstract UserDAO getuserDAO();
    private static final String DB_NAME = "KFC.db";
    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.
                    databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class,
                            DB_NAME).allowMainThreadQueries().
                    build();
        }
        return instance;
    }
}
