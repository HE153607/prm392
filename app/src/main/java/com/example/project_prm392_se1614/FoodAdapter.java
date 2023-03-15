package com.example.project_prm392_se1614;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392_se1614.entity.Food;

import org.w3c.dom.Text;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {


    public List<Food> ListFood;
    private IClickFood iClickFood;
    public interface IClickFood{
        void updateFood(Food food);

        void deleteFood(Food food);
     }

    public FoodAdapter(IClickFood iClickFood) {
        this.iClickFood = iClickFood;
    }

    public void SetData(List<Food>lFood){
        this.ListFood= lFood;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food food = ListFood.get(position);
        if(food == null){
            return;
        }

        holder.tvFoodName.setText(food.getFoodName());
//        holder.tvFood.setText(food.getFoodName());
//        holder.imageView.setImageBitmap(BitmapFactory.decodeFile(food.getImage()));
//        holder.imageView.setVisibility(View.VISIBLE);
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iClickFood.updateFood(food);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickFood.deleteFood(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(ListFood != null){
            return ListFood.size();
        }
        return 0;
    }

    public class  FoodViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFoodName;
        private TextView tvFood;
        private ImageView imageView;
        private Button btnUpdate;
        private Button btnDelete;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
             tvFoodName = itemView.findViewById(R.id.txtNameFood);
//            imageView = itemView.findViewById(R.id.imageItem1);
             btnUpdate = itemView.findViewById(R.id.btnUpdate);
             btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
