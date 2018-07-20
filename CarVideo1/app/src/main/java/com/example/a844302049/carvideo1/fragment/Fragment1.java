package com.example.a844302049.carvideo1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a844302049.carvideo1.Mp4Activity;
import com.example.a844302049.carvideo1.R;
import com.example.a844302049.carvideo1.shitilei.ContentView_one;
import com.example.a844302049.carvideo1.util.GsonUtils;
import com.example.a844302049.carvideo1.util.HttpUtil;
import com.example.a844302049.carvideo1.util.Image;
import com.example.a844302049.carvideo1.util.MyLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 844302049 on 2018/7/18.
 */

public class Fragment1 extends Fragment{

    private Banner banner;
    private ArrayList<String> list_path;//banner图片集合
    private ArrayList<String> list_title;//banner标题集合
    private List<ContentView_one.DataBean> mList = new ArrayList<>();//listView数据集合
    private ListView listView;
    private myAdapter adapter;

    private String a;
    private String b;

    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup con, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_layout1,con,false);
        initView();//初始化banner
        listView = mView.findViewById( R.id.listview);
        adapter = new myAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Mp4Activity.class);
                intent.putExtra("mp4",mList.get(i).getUrl());
                startActivity(intent);
            }
        });//listView子项点击事件
        listView.setOnScrollListener(new listviewListener());
        initData();//获取并加载ListView界面的URL数据
        return mView;
    }

    private void initView(){
        String address = "http://wx.cheshijie.com/index.php/Home/Api/typeTuijian?type=29";//数据源
        HttpUtil.sendOkHttpRequest(address, new Callback() {//获取，响应数据源
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext(),"获取失败，请检查网络连接",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();//获得请求结果数据
                getActivity().runOnUiThread(new Runnable() {//开启更新UI线程
                    @Override
                    public void run() {
                        Log.i("====",responseData);
                        ContentView_one contentView_one = GsonUtils.parseJSON(responseData,ContentView_one.class);//解析JSON数据
                        final List<ContentView_one.DataBean> data = contentView_one.getData();//定义数据集合
                        banner = mView.findViewById(R.id.banner_1);
                        list_path = new ArrayList<>();
                        list_title = new ArrayList<>();
                        for(int i = 0;i<4;i++){
                            list_path.add(data.get(i).getImagename());
                            list_title.add(data.get(i).getTitle());
                        }//遍历添加四个图片URL和四个标题URL
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        banner.setImageLoader(new MyLoader());//设置图片加载器，图片加载器在下方
                        banner.setImages(list_path);//设置图片网址或地址的集合
                        banner.setBannerAnimation(Transformer.Default);//设置轮播的动画效果，内含多种特效
                        banner.setBannerTitles(list_title);//设置轮播图的标题集合
                        banner.setDelayTime(3000);//设置轮播间隔时间
                        banner.isAutoPlay(true);//设置是否为自动轮播，默认:是。
                        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置，小点点，左中右。
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {//子项点击事件
                                Intent intent = new Intent(getContext(),Mp4Activity.class);
                                intent.putExtra("mp4",data.get(position).getUrl());//传入当前项的URL播放地址
                                startActivity(intent);
                            }
                        }).start();

                    }
                });
            }
        });

    }//banner逻辑
    private void initData(){
        String addressss = "http://wx.cheshijie.com/index.php/home/api/newest?page=1&limit=5";
        HttpUtil.sendOkHttpRequest(addressss, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext(),"获取失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();//获得请求结果数据

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Log.e("====",responseData);
                        ContentView_one contentView_one = GsonUtils.parseJSON(responseData,ContentView_one.class);
                        List<ContentView_one.DataBean> data = contentView_one.getData();
                        mList.addAll(data);
//                        Log.i("====", "run: "+mList.get(0).getImagename());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }//获取并加载数据
    class myAdapter extends BaseAdapter {//适配器
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            myAdapter.viewHodle viewHodle;
            if (convertView == null) {
                viewHodle = new myAdapter.viewHodle();
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout, null);
                convertView.setTag(viewHodle);
            } else {
                viewHodle = (myAdapter.viewHodle) convertView.getTag();
            }


            viewHodle.IMAGE = convertView.findViewById(R.id.image_1);//控件ID
            viewHodle.TEXT = convertView.findViewById(R.id.text_1);


            Image.LoaderNet(getContext(),mList.get(position).getImagename(),viewHodle.IMAGE);//展示图片
            viewHodle.TEXT.setText(mList.get(position).getTitle());

            return convertView;
        }
        class viewHodle {
            private ImageView IMAGE;
            private TextView TEXT;
        }
    }//listView适配器
    class listviewListener implements AbsListView.OnScrollListener{

        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            //判断是否滚到最后一行
            if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                b = null;
                for(int i = 1;i<50;i++){
                    a = "http://wx.cheshijie.com/index.php/home/api/newest?";
                    b = "page="+i+"&limit=5";
                    String address = a+b;
                    HttpUtil.sendOkHttpRequest(address, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(getContext(),"获取失败",Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String responseData = response.body().string();//获得请求结果数据
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Log.e("====",responseData);
                                    ContentView_one contentView_one = GsonUtils.parseJSON(responseData,ContentView_one.class);
                                    List<ContentView_one.DataBean> data = contentView_one.getData();
                                    mList.addAll(data);
//                                    Log.i("====", "run: "+mList.get(0).getImagename());
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    });
                }
            }
        }
    }//ListView滑动监听器
}
