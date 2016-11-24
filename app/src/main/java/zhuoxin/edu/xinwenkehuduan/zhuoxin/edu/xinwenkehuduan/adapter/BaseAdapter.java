package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
/*
*  封装适配器
* */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    ArrayList<T> mList;
    LayoutInflater mInflater;

    public BaseAdapter(Context context) {
              mInflater = mInflater.from(context);


    }

    public void setData(ArrayList<T> List) {
        if (this.mList != null) {
            this.mList.clear();
        }
        this.mList=List;
    }


    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return setView(position, convertView, parent);
    }

    public abstract View setView(int position, View convertview, ViewGroup parent);
}
