package com.example.project_prm392_se1614;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392_se1614.entity.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    public List<Food> foodList;
    public void setData(List<Food> list){
        this.foodList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
       Food food = foodList.get(position);
       if(food == null){
           return;
       }
       holder.fname.setText(food.getFoodName());
       holder.recipe.setText(food.getIngredient());
       holder.step.setText(food.getStep());
    }

    @Override
    public int getItemCount() {
        if(foodList != null){
          return foodList.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
         public ImageView foodimage;
         public TextView fname;
         public TextView recipe;
         public TextView step;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.foodimage);
            fname = itemView.findViewById(R.id.name);
            recipe = itemView.findViewById(R.id.recipe);
            step = itemView.findViewById(R.id.step);
        }
    }
}
