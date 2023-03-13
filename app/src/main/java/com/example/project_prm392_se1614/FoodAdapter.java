package com.example.project_prm392_se1614;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
       String imagename = food.getImage();
       Context context = holder.itemView.getContext();
       if(food == null){
           return;
       }
        Resources resources = context.getResources();
        int imageResId =resources.getIdentifier(imagename, "drawable", context.getPackageName());
        if (imageResId != 0) {
            Drawable drawable = resources.getDrawable(imageResId);
            holder.foodimage.setBackground(drawable);
        }
       holder.fname.setText(food.getFoodName());

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

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.foodimage);
            fname = itemView.findViewById(R.id.name);
        }
    }
}
