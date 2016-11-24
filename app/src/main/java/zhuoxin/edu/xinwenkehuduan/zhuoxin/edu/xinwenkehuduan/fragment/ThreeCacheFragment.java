package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.ThreeCacheAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.GroupInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/23.
 */
/*
* 图片缓存界面
*
* */
public class ThreeCacheFragment extends Fragment implements OnLoadRegisterListener {
    RecyclerView mRecycler;
    RequestQueue mRequestQueue;
    ArrayList<ChildInfo> mData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.threecache, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_three);
        //瀑布流显示形式
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //数据源
        //ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20
        mRequestQueue = Volley.newRequestQueue(getContext());
        HttpUtils.connectionGET(HttpInfo.BASE_URL+HttpInfo.NEWS_URL+"ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20",this,mRequestQueue);


    }

    @Override
    public void getRegister(String message) {
        Log.e("--------","message=="+message);
        Gson gson = new Gson();
        GroupInfo info = gson.fromJson(message, new TypeToken<GroupInfo>() {
        }.getType());
        mData = info.getData();
        ThreeCacheAdapter adapter=new ThreeCacheAdapter(mData,getContext());
        mRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
