package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.FollowAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.FollowInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.MySqlUtils;

/**
 * Created by Administrator on 2016/11/22.
 */
/*
* 跟帖界面
* */

public class FollowFragment extends Fragment{
    ListView mLst;
    ArrayList<FollowInfo> mData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.follow,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLst= (ListView) view.findViewById(R.id.lst_follow);
       MySqlUtils mySqlUtils=new MySqlUtils(getContext());
        mData=mySqlUtils.query();
        FollowAdapter adapter=new FollowAdapter(getContext());
        adapter.setData(mData);
        mLst.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
