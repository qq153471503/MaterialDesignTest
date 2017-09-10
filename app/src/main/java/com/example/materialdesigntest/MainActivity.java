package com.example.materialdesigntest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton_Add;
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.fruit_apple),
    new Fruit("Banana", R.drawable.fruit_banana),
    new Fruit("Grape", R.drawable.fruit_grape),
    new Fruit("nectarine", R.drawable.fruit_nectarine),
    new Fruit("orange", R.drawable.fruit_orange),
    new Fruit("peach", R.drawable.fruit_peach),
    new Fruit("pear",R.drawable.fruit_pear),
    new Fruit("pitahaya",R.drawable.fruit_pitahaya),
    new Fruit("strawberry",R.drawable.fruit_strawberry),
    new Fruit("watermelon",R.drawable.fruit_watermelon)};

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter fruitAdapter;
    private RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.item_delete:
                Toast.makeText(this, "You clicked delete ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_uplaod:
                Toast.makeText(this, "You clicked upload ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_setting:
                Toast.makeText(this, "You clicked setting ", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 标题栏初始化
         */
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.item_category);
        }

        /**
         * 滑动出现的布局
         */
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);


        /**
         * 滑动出现的布局显示内容初始化,以及点击监听事件
         */
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this, "You clicked Call.",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_friend:
                        Toast.makeText(MainActivity.this, "You clicked Friend.",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_local:
                        Toast.makeText(MainActivity.this, "You clicked Loacation.",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this, "You clicked Task.",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_email:
                        Toast.makeText(MainActivity.this, "You clicked Email.",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;

                    default:break;
                }
                return true;
            }
        });

        /**
         * 悬浮式按钮控件初始化,并设置点击监听事件
         */
        floatingActionButton_Add = (FloatingActionButton)findViewById(R.id.id_float_button_fab);
        floatingActionButton_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件之后,Toast无发在响应其他事件
//                Toast.makeText(MainActivity.this, "You clicked FloatActionButton. ", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "是否删除?", Snackbar.LENGTH_SHORT).setAction("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"你取消删除.",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
//        navigationView = (NavigationView)findViewById(R.id.nav_view);
//        navigationView.setCheckedItem(R.id.nav_call);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                mDrawerLayout.closeDrawers();
//                return true;
//            }
//        });

        /**
         * 添加水果列表
         */
        fruitList.clear();
        for (int i=0; i<50; i++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }

        /**
         * RecyclerView要显示的水果列表 ,CardView初始化
         */
        recyclerView = (RecyclerView)findViewById(R.id.id_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2: 两列
        recyclerView.setLayoutManager(gridLayoutManager);
        fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }
}
