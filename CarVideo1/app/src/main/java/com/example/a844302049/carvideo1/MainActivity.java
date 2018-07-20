package com.example.a844302049.carvideo1;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a844302049.carvideo1.adapter.ViewPagerFragmentAdapter;
import com.example.a844302049.carvideo1.fragment.Fragment1;
import com.example.a844302049.carvideo1.fragment.Fragment2;
import com.example.a844302049.carvideo1.fragment.Fragment3;
import com.example.a844302049.carvideo1.fragment.Fragment4;
import com.example.a844302049.carvideo1.fragment.Fragment5;
import com.example.a844302049.carvideo1.fragment.Fragment6;
import com.example.a844302049.carvideo1.fragment.Fragment7;

import java.util.ArrayList;
import java.util.List;

import crossoverone.statuslib.StatusUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerLayout;//侧滑
    private ImageView imageView;//侧滑按钮
    ViewPager mViewPager;//viewPager界面
    ViewPagerFragmentAdapter mViewPagerFragmentAdapter;//viewpager界面适配fragment的适配器
    FragmentManager mFragmentManager;//处理fragment的事务
    private RadioGroup radioGroup;
    RadioButton raButton1;
    RadioButton raButton2;
    RadioButton raButton3;
    RadioButton raButton4;
    RadioButton raButton5;
    RadioButton raButton6;
    RadioButton raButton7;

    List<Fragment> mFragmentList = new ArrayList<>();//放置fragment的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar1);//Toolbar
        setSupportActionBar(toolbar);//设置头部控件为toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);//在Toolbar上隐藏APP名
        drawerLayout = findViewById(R.id.drawer_layout);//侧滑界面
        imageView = findViewById(R.id.common_iv_test);//侧滑图片按钮
        imageView.setOnClickListener(this);//侧滑图片按钮点击事件
        mFragmentManager = getSupportFragmentManager();//获取处理fragment的事务
        initFragmentList();//初始化碎片的集合
        //new一个创建好的viewpager+fragment的适配器
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
        initView();//顶部菜单栏点击事件
        initViewPager();//初始化界面
        initState();//沉浸式状态栏

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);//toolbar布局就是toolbar右侧显示的图标按钮
        return true;
    }//关联menu布局在Toolbar上
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.chazhao://查找点击事件
                Toast.makeText(this,"功能敬请期待",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }//Toolbar右侧图标按钮点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.common_iv_test://侧滑按钮点击事件
                drawerLayout.openDrawer(GravityCompat.START);//打开侧滑界面
                break;
        }
    }//点击事件
    public void initFragmentList(){
        Fragment1 one = new Fragment1();
        Fragment2 two = new Fragment2();
        Fragment3 three = new Fragment3();
        Fragment4 four = new Fragment4();
        Fragment5 five = new Fragment5();
        Fragment6 six = new Fragment6();
        Fragment7 seven = new Fragment7();
        mFragmentList.add(one);
        mFragmentList.add(two);
        mFragmentList.add(three);
        mFragmentList.add(four);
        mFragmentList.add(five);
        mFragmentList.add(six);
        mFragmentList.add(seven);
    }//初始化碎片集合
    public void initView(){
        mViewPager = findViewById(R.id.ViewPagerLayout);
        radioGroup = findViewById(R.id.ra_0);
        raButton1 = findViewById(R.id.ra_1);
        raButton2 = findViewById(R.id.ra_2);
        raButton3 = findViewById(R.id.ra_3);
        raButton4 = findViewById(R.id.ra_4);
        raButton5 = findViewById(R.id.ra_5);
        raButton6 = findViewById(R.id.ra_6);
        raButton7 = findViewById(R.id.ra_7);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if ( raButton1.getId() == i ){
                    mViewPager.setCurrentItem(0);
                    updateSelect(true,false,false, false,false,false,false);
                }
                if ( raButton2.getId() == i ){
                    mViewPager.setCurrentItem(1);
                    updateSelect(false,true,false, false,false,false,false);
                }
                if ( raButton3.getId() == i ){
                    mViewPager.setCurrentItem(2);
                    updateSelect(false,false,true, false,false,false,false);
                }
                if ( raButton4.getId() == i ){
                    mViewPager.setCurrentItem(3);
                    updateSelect(false,false,false, true,false,false,false);
                }
                if ( raButton5.getId() == i ){
                    mViewPager.setCurrentItem(4);
                    updateSelect(false,false,false, false,true,false,false);
                }
                if ( raButton6.getId() == i ){
                    mViewPager.setCurrentItem(5);
                    updateSelect(false,false,false, false,false,true,false);
                }
                if ( raButton7.getId() == i ){
                    mViewPager.setCurrentItem(6);
                    updateSelect(false,false,false, false,false,false,true);
                }
            }
        });
    }//初始化界面及顶部菜单点击事件
    private void updateSelect(boolean o,boolean t,boolean s,boolean f,boolean v,boolean x,boolean e){
        raButton1.setSelected(o);
        raButton2.setSelected(t);
        raButton3.setSelected(s);
        raButton4.setSelected(f);
        raButton5.setSelected(v);
        raButton6.setSelected(x);
        raButton7.setSelected(e);
    }//给每个点击事件加上一个布尔值，用于判断是否显示碎片
    public void initViewPager(){
        mViewPager.addOnPageChangeListener(new ViewPageOnPageChange());//调用viewPager监听器
        mViewPager.setAdapter(mViewPagerFragmentAdapter);//设置适配器
        mViewPager.setCurrentItem(0);//设置初始化当前项。
        updateSelect(true,false,false,false,false,false,false);//显示首页
        raButton1.setTextColor(android.graphics.Color.parseColor("#EEEE00"));
        raButton2.setTextColor(android.graphics.Color.parseColor("#ffffff"));
        raButton3.setTextColor(android.graphics.Color.parseColor("#ffffff"));
        raButton4.setTextColor(android.graphics.Color.parseColor("#ffffff"));
        raButton5.setTextColor(android.graphics.Color.parseColor("#ffffff"));
        raButton6.setTextColor(android.graphics.Color.parseColor("#ffffff"));
        raButton7.setTextColor(android.graphics.Color.parseColor("#ffffff"));
    }//初始化ViewPager界面
    public void initState(){
        //参数二状态栏色值
        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#ec121c"));
        //参数二是否沉浸，参数三状态栏字体是否为黑色
        StatusUtil.setSystemStatus(this, false, false);
    }
    class ViewPageOnPageChange implements ViewPager.OnPageChangeListener {

        @Override
        //在滑动过程中不停触发该方法
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        //ViewPager跳转到新页面时触发该方法，position表示新页面的位置。
        public void onPageSelected(int position) {
            boolean[] state = new boolean[7];
            state[position] = true;
            updateSelect(state[0],state[1],state[2],state[3],state[4],state[5],state[6]);
            switch (position){//当点击当前按钮时，当前按钮设为黄色，其他按钮为白色
                case 0:
                    raButton1.setTextColor(android.graphics.Color.parseColor("#EEEE00"));
                    raButton2.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton3.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton4.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton5.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton6.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton7.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    break;
                case 1:
                    raButton1.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton2.setTextColor(android.graphics.Color.parseColor("#EEEE00"));
                    raButton3.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton4.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton5.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton6.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton7.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    break;
                case 2:
                    raButton1.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton2.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton3.setTextColor(android.graphics.Color.parseColor("#EEEE00"));
                    raButton4.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton5.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton6.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    raButton7.setTextColor(android.graphics.Color.parseColor("#ffffff"));
                    break;
                case 3:
                    raButton1.setTextColor(Color.parseColor("#ffffff"));
                    raButton2.setTextColor(Color.parseColor("#ffffff"));
                    raButton3.setTextColor(Color.parseColor("#ffffff"));
                    raButton4.setTextColor(Color.parseColor("#EEEE00"));
                    raButton5.setTextColor(Color.parseColor("#ffffff"));
                    raButton6.setTextColor(Color.parseColor("#ffffff"));
                    raButton7.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case 4:
                    raButton1.setTextColor(Color.parseColor("#ffffff"));
                    raButton2.setTextColor(Color.parseColor("#ffffff"));
                    raButton3.setTextColor(Color.parseColor("#ffffff"));
                    raButton4.setTextColor(Color.parseColor("#ffffff"));
                    raButton5.setTextColor(Color.parseColor("#EEEE00"));
                    raButton6.setTextColor(Color.parseColor("#ffffff"));
                    raButton7.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case 5:
                    raButton1.setTextColor(Color.parseColor("#ffffff"));
                    raButton2.setTextColor(Color.parseColor("#ffffff"));
                    raButton3.setTextColor(Color.parseColor("#ffffff"));
                    raButton4.setTextColor(Color.parseColor("#ffffff"));
                    raButton5.setTextColor(Color.parseColor("#ffffff"));
                    raButton6.setTextColor(Color.parseColor("#EEEE00"));
                    raButton7.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case 6:
                    raButton1.setTextColor(Color.parseColor("#ffffff"));
                    raButton2.setTextColor(Color.parseColor("#ffffff"));
                    raButton3.setTextColor(Color.parseColor("#ffffff"));
                    raButton4.setTextColor(Color.parseColor("#ffffff"));
                    raButton5.setTextColor(Color.parseColor("#ffffff"));
                    raButton6.setTextColor(Color.parseColor("#ffffff"));
                    raButton7.setTextColor(Color.parseColor("#EEEE00"));
                    break;
            }
        }
        @Override
        //当页面的滑动状态改变时该方法会被触发，页面的滑动状态有3个：“0”表示什么都不做，“1”表示开始滑动，“2”表示结束滑动。
        public void onPageScrollStateChanged(int state) {

        }
    }//viewPager监听器
}
