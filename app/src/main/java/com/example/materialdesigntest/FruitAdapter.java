package com.example.materialdesigntest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by KunGe on 2017/9/10.
 */

public class FruitAdapter extends RecyclerView.Adapter <FruitAdapter.ViewHolder>{

    private Context context;
    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageViewFruit;
        TextView textViewFruitName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            imageViewFruit = (ImageView)
                    view.findViewById(R.id.id_imageview_fruit_image);
            textViewFruitName = (TextView)
                    view.findViewById(R.id.id_textview_fruit_name);
        }
    }//end of Class

    public FruitAdapter(List<Fruit> fruitList){
        this.fruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.layout_fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.textViewFruitName.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.imageViewFruit);
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}


