package com.example.project_prm392_se1614.dao;

import android.content.Context;
import android.content.DialogInterface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.FoodMaterial;
import com.example.project_prm392_se1614.entity.Material;

import java.util.List;

@Dao
public interface FoodDAO {
    @Query("SELECT * FROM Food")
    List<Food> getAllFoods();

    @Query("SELECT * FROM Food WHERE id IN (SELECT id FROM material WHERE id IN (:materialIds))")
    List<Food> getFoodsByMaterials(List<Long> materialIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Food food);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMaterials(List<Material> materials);
    @Insert
    void insertFood(Food... food);

    @Query("SELECT * FROM Food")
    List<Food> getAllFood();

    @Query("SELECT * FROM Food WHERE food_name LIKE '%' || :name || '%'")
    List<Food> searchfood(String name);

    @Delete
    void deleteFood(Food food);

    @Query("Delete From Food WHERE id = :foodid")
    void deleteFoodById(long foodid);


    @Query("SELECT * FROM Food ")
    List<Food> getFoods();
    @Update
    void updateFood(Food food);

    @Query("SELECT * FROM Food WHERE id =:userId")
    Food getFoodById(int userId);
    @Query("SELECT * FROM Food WHERE user_id =:uId ")
    List<Food> getListFoodsById(int uId);

    @Transaction
    default long insertFoodWithMaterials(Context context, Food food, List<Material> materials ) {
        long foodId = insert(food);
        for (Material material : materials) {
            long materialId = MyDatabase.getInstance(context).getmaterialDAO().insert(material);
            FoodMaterial foodMaterial = new FoodMaterial(foodId, materialId);
            MyDatabase.getInstance(context).getfoodMaterialDAO().insert(foodMaterial);
        }
        return foodId;
    }
//    @Transaction
//    default long UpdateFoodWithMaterials(Context context, Food food, List<Material> materials ) {
//        long foodId = updateFood(food);
//        for (Material material : materials) {
//            long materialId = MyDatabase.getInstance(context).getmaterialDAO().insert(material);
//            FoodMaterial foodMaterial = new FoodMaterial(foodId, materialId);
//            MyDatabase.getInstance(context).getfoodMaterialDAO().updatMaterial(foodMaterial);
//        }
//        return foodId;
//    }
    @Transaction
    default  void DeleteFood(Context context , long id){
        MyDatabase.getInstance(context).getfoodMaterialDAO().deleteByFoodId((int)id);
        deleteFoodById(id);

    }
}
