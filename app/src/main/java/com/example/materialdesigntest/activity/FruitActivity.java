package com.example.materialdesigntest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialdesigntest.R;

/**
 * Created by KunGe on 2017/9/10.
 */

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent = getIntent();
        String fruitname = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar)findViewById(R.id.id_fruitactivity_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout)findViewById(R.id.id_collapsing_toolbar);
        ImageView imageViewFruit = (ImageView)
                findViewById(R.id.id_imageview_toolbar_fruit_image);
        TextView textViewFruitContent = (TextView)
                findViewById(R.id.id_textview_fruit_content);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(fruitname);
        Glide.with(this).load(fruitImageId).into(imageViewFruit);
        String fruitContent = generateFruirContent(fruitname);
        textViewFruitContent.setText(fruitContent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 填充textview要显示的内容
     * @param fruitname
     * @return
     */
    private String generateFruirContent(String fruitname) {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<500; i++){
            builder.append(fruitname);
        }
        return builder.toString();
    }
}
