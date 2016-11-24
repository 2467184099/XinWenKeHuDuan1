package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.maxwin.view.XListView;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.CenterAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.GroupInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadNewcustomLlister;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.MainActivity;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.WebActivity;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.NewcustomTask;

/**
 * Created by Administrator on 2016/10/28.
 */
/*
* 新闻列表
* */
public class CenterFragment extends Fragment implements XListView.IXListViewListener, OnLoadNewcustomLlister, AdapterView.OnItemClickListener, View.OnClickListener {
    TextView mText1;
    TextView mText2;
    ImageView mImg;
    XListView mXlst;
    CenterAdapter mAdapter;
    android.os.Handler mHandler;
    static ArrayList<ChildInfo> mData;
    typemoreFragment mFragment;
    String mTitle;
    public static final String PATA = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.centerfragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new android.os.Handler();
        initData(view);
        task();
    }

    //加载控件
    private void initData(View view) {
        mImg = (ImageView) view.findViewById(R.id.img_center);
        mText1 = (TextView) view.findViewById(R.id.txt1_center);
        mText2 = (TextView) view.findViewById(R.id.txt2_center);
        mXlst = (XListView) view.findViewById(R.id.xlst_center);
        mXlst.setOnItemClickListener(this);
        mText2.setOnClickListener(this);
        mImg.setOnClickListener(this);
        mFragment = new typemoreFragment();
    }

    //启动异步
    private void task() {
        //启动异步
        NewcustomTask Task = new NewcustomTask();
        Task.setOnLoadNewcustomLlister(this);
        Task.execute(PATA);
    }

    //实现接口 重写的方法·
    @Override
    public void OnLoadNewcustomLlister(String string) {
         Log.e("=====", "String=" + string);
        Gson gson = new Gson();
        GroupInfo info = gson.fromJson(string, new TypeToken<GroupInfo>() {
        }.getType());
        mData = info.getData();
        SharedPreferences shar=getActivity().getSharedPreferences("center", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shar.edit();
        edit.putString("title",mTitle);
        edit.commit();
        //Log.e("==", "mData==" + mData);
        mAdapter = new CenterAdapter(getContext());
        mAdapter.setData(mData);
        mXlst.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        //设置两个方法
        //上拉加载
        mXlst.setPullLoadEnable(true);
        //下拉刷新
        mXlst.setPullRefreshEnable(true);
        //上拉刷新和下拉加载都必须设置监听事件
        mXlst.setXListViewListener(this);
    }

    //上拉加载
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000);
    }

    //下拉刷新
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000);
    }

    //停止加载和刷新
    public void stop() {
        //停止加载和刷新
        mXlst.stopLoadMore();
        mXlst.stopRefresh();
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXlst.setRefreshTime(dateFormat.format(new Date(System.currentTimeMillis())));
    }

    //跳转页面
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public static ArrayList<ChildInfo> getData() {
        return mData;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_center: {
                FragmentManager Manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = Manager.beginTransaction();
                fragmentTransaction.replace(R.id.lyt_center, mFragment);
                fragmentTransaction.commit();
                MainActivity.mText_main.setText("分类");
            }
            break;
        }
    }
}
