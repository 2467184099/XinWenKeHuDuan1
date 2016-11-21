package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.NewcustomTask;

/**
 * Created by Administrator on 2016/11/21.
 */

public class SocietyFragment extends Fragment implements XListView.IXListViewListener,View.OnClickListener,OnLoadNewcustomLlister {
    ImageView mImg;
    TextView mTxt1;
    TextView mTxt2;
    XListView mXlst;
    CenterFragment mFragment;
    android.os.Handler mHandler;
    CenterAdapter mAdapter;
    static ArrayList<ChildInfo> mData;
    public static final String PATA =" http://118.244.212.82:9092/newsClient/path/news_sort?ver=1&imei=10";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.society,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new android.os.Handler();
        initView(view);
        task();

    }

    private void task() {
        NewcustomTask Task = new NewcustomTask();
        Task.setOnLoadNewcustomLlister(this);
        Task.execute(PATA);
    }

    private void initView(View view) {
        mImg= (ImageView) view.findViewById(R.id.img_society);
        mTxt1= (TextView) view.findViewById(R.id.txt1_society);
        mTxt2= (TextView) view.findViewById(R.id.txt2_society);
        mXlst= (XListView) view.findViewById(R.id.xlst_society);
        mTxt1.setOnClickListener(this);
        mFragment =new CenterFragment();
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

    @Override
    public void OnLoadNewcustomLlister(String string) {
        Log.e("-------","string=="+string);
        Gson gson = new Gson();
        GroupInfo info = gson.fromJson(string, new TypeToken<GroupInfo>() {
        }.getType());
        mData = info.getData();
        Log.e("==", "data==" + mData);
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
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.txt1_society:
               FragmentManager Manager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = Manager.beginTransaction();
               fragmentTransaction.replace(R.id.lyt_center,mFragment);
               fragmentTransaction.commit();
               break;
       }
    }


}
