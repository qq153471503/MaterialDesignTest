package com.example.materialdesigntest.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.materialdesigntest.Fruit;
import com.example.materialdesigntest.FruitAdapter;
import com.example.materialdesigntest.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;//下拉刷新
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
         * 滑动出现的布局显示内容初始化,以及点击监听事件
         */
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_call:
                        /**
                         * 点击之后弹出提示,并且关闭 滑动出现的布局,下同.
                         */
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
                //点击事件之后,Toast无发在响应其他事件所以用Snackbar
//                Toast.makeText(MainActivity.this, "You clicked FloatActionButton. ", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "是否删除?", Snackbar.LENGTH_SHORT).setAction("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"你取消删除.",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        /*
         * 为List添加数据,就是将水果数据添加进去
         */
        initFruits();

        /**
         * RecyclerView要显示的水果列表 ,CardView初始化
         */
        recyclerView = (RecyclerView)findViewById(R.id.id_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2: 两列
        recyclerView.setLayoutManager(gridLayoutManager);
        fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);

        /**
         * 下拉刷新初始化布局以及设置事件监听
         */
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.id_swipe_refresh_layout);
        //设置下拉刷新进度条的颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //设置下拉刷新监听器,有事件发生就会回调onRefresh函数.
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /**
                 * 开一个新线程并且睡眠1500毫秒模拟刷新的时间
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initFruits(); //重新加载数据
                                fruitAdapter.notifyDataSetChanged();    //通知适配器数据发生改变,刷新新的显示
                                swipeRefreshLayout.setRefreshing(false);    //传入false表示刷新事件结束,并隐藏进度条
                            }
                        });
                    }
                }).start();
            }
        });
    }

    /**
     * 添加水果数据
     */
    private void initFruits() {
        fruitList.clear();
        for (int i=0; i<50; i++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
}
