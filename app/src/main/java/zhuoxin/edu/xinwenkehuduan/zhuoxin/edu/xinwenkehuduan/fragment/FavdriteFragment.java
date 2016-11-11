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
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.FavdriteAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/10.
 */

public class FavdriteFragment extends Fragment {
    ListView mLst;
    ArrayList<ChildInfo> mList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favdritefragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLst= (ListView) view.findViewById(R.id.lst_favdritefragment);
        SqlUtils sqlUtils=new SqlUtils(getContext());
        mList=sqlUtils.query();
        FavdriteAdapter adapter=new FavdriteAdapter(getContext());
        adapter.setData(mList);
        mLst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
