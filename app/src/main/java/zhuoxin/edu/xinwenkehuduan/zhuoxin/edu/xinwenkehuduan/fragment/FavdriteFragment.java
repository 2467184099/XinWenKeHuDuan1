package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.FavdriteAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadFavdriteListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.FavdriteShowActivity;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/10.
 *//*
 * 收藏新闻   瀑布流
 * */

public class FavdriteFragment extends Fragment  {
    //ListView mLst;
    ArrayList<ChildInfo> mList ;
    RecyclerView mRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favdritefragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler = (RecyclerView) view.findViewById(R.id.recyle_favdrite);
        //瀑布流显示形式
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //数据源
        SqlUtils sqlUtils = new SqlUtils(getContext());
        mList = sqlUtils.query();
        //适配器
        FavdriteAdapter favdrite=new FavdriteAdapter(mList,getContext());
        mRecycler.setAdapter(favdrite);
        favdrite.notifyDataSetChanged();
        favdrite.setData(new OnLoadFavdriteListener() {
            @Override
            public void onLoadFavdrite(int position) {
                Intent intent=new Intent(getActivity(), FavdriteShowActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

       /* mLst= (ListView) view.findViewById(R.id.lst_favdritefragment);
        mLst.setOnItemClickListener(this);
        SqlUtils sqlUtils=new SqlUtils(getContext());
        mList=sqlUtils.query();
        FavdriteAdapter adapter=new FavdriteAdapter(getContext());
        adapter.setData(mList);
        mLst.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/
    }

}
