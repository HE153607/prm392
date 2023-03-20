package com.example.project_prm392_se1614.dao;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.FoodMaterial;
import com.example.project_prm392_se1614.entity.Material;

import java.util.List;

@Dao
public interface FoodDAO {
    @Query("SELECT * FROM Food")
    List<Food> getAllFoods();

    @Query("SELECT * FROM Food WHERE id IN (SELECT id FROM user_food WHERE id IN (:materialIds))")
    List<Food> getFoodsByMaterials(List<Long> materialIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Food food);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMaterials(List<Material> materials);

//    @Transaction
//    default long insertFoodWithMaterials(Context context, Food food, List<Material> materials ) {
//        long foodId = insert(food);
//        for (Material material : materials) {
//            long materialId = MyDatabase.getInstance(context).getmaterialDAO().insert(material);
//            FoodMaterial foodMaterial = new FoodMaterial(foodId, materialId);
//            MyDatabase.getInstance(context).getfoodMaterialDAO().insert(foodMaterial);
//        }
//        return foodId;
//    }
}
