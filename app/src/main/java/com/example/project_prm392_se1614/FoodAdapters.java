package com.example.project_prm392_se1614;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392_se1614.entity.Food;

import java.util.List;

public class FoodAdapters extends RecyclerView.Adapter<FoodAdapters.FoodViewHolder>{
    public List<Food> foodList;
    public static int foodId;
    public IClick iClicks;
    public void setData(List<Food> list){
        this.foodList = list;
        notifyDataSetChanged();
    }
    public interface IClick{
        void deatailFood(Food food);
    }
    public FoodAdapters(FoodAdapters.IClick iClicks) {
        this.iClicks = iClicks;
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
//       holder.delete.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               iClicks.deleteFood(food);
//           }
//       });
//       String imagename = food.getImage();
       Context context = holder.itemView.getContext();
//
//       if(food == null){
//           return;
//       }
//
//        Resources resources = context.getResources();
//        int imageResId =resources.getIdentifier(imagename, "drawable", context.getPackageName());
//        if (imageResId != 0) {
//            Drawable drawable = resources.getDrawable(imageResId);
//            holder.foodimage.setBackground(drawable);
            Bitmap bitmap = BitmapFactory.decodeByteArray(food.getImage(), 0, food.getImage().length);
            holder.foodimage.setImageBitmap(bitmap);
            holder.foodimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        iClicks.deatailFood(food);
                }
            });


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

         //public Button delete;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.foodimage);
            fname = itemView.findViewById(R.id.name);

            //delete = itemView.findViewById(R.id.delete);
        }
    }
}
