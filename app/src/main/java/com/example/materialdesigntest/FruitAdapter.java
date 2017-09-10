package com.example.materialdesigntest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.materialdesigntest.activity.FruitActivity;

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

        /**
         * 将ImageView和TextView封装,做缓冲,提高快速滑动的显示效率
         * @param view : RecyclerView子项的最外层布局
         */
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

    /**
     * 用于创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();

        //加载水果信息的布局,即CardView中的控件
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fruit_item, parent, false);

        /**
         * 设置点击时事件,启动FruitActivity
         */
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = fruitList.get(position);
                Intent intent = new Intent(context, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    /**
     * 不加泛型 参数是RecyclerView.ViewHolder holder
     */
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }

    /**
     * 加上泛型,参数就是 泛型的参数
     *
     * 用于对RecyclerView中的子项进行赋值,会在每个子项被滚到屏幕内的时候回调执行
     * @param holder 内部类,封装了CardView上的控件
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.textViewFruitName.setText(fruit.getName());
        //用Glide处理图片并显示
        Glide.with(context).load(fruit.getImageId()).into(holder.imageViewFruit);
    }

    /**
     * 返回RecyclerView一共有多少子项
     * @return
     */
    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}


