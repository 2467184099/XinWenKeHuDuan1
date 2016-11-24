package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;

/**
 * Created by Administrator on 2016/11/24.
 */
/*
* 图片下载适配器
* */
public class ThreeCacheAdapter extends RecyclerView.Adapter<ThreeCacheAdapter.ThreeViewHodler> {
    ArrayList<ChildInfo> mData;
    Context mContext;
    public ThreeCacheAdapter( ArrayList<ChildInfo> mData,Context mContext){
        this.mData=mData;
        this.mContext=mContext;

    }
    //做绑定 将子条目的布局与RecyclerView.ViewHolder进行绑定
    @Override
    public ThreeViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cacheadapter, null);
        return new ThreeViewHodler(view);
    }

    //将数据与viewHolder进行绑定
    @Override
    public void onBindViewHolder(ThreeViewHodler holder, int position) {
        Picasso.with(mContext).load(mData.get(position).getIcon()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return null==mData?0:mData.size();
    }

    static class ThreeViewHodler extends RecyclerView.ViewHolder {
        ImageView mImg;

        public ThreeViewHodler(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img_cacheadapter);
        }
    }


}
