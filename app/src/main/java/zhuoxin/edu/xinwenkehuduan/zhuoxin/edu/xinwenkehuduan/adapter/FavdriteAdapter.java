package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadFavdriteListener;

/**
 * Created by Administrator on 2016/11/14.
 */
/*
* 新闻收藏适配器
* */
public class FavdriteAdapter extends RecyclerView.Adapter<FavdriteAdapter.MyViewHodler> {
    ArrayList<ChildInfo> mList;
    Context mContext;
    OnLoadFavdriteListener mOnLoadFavdriteListener;
    public FavdriteAdapter(ArrayList<ChildInfo> mList, Context mContext) {
        this.mList = mList;
        this.mContext=mContext;

    }
    public void setData(OnLoadFavdriteListener mOnLoadFavdriteListener){
        this.mOnLoadFavdriteListener=mOnLoadFavdriteListener;

    }

    //做绑定 将子条目的布局与RecyclerView.ViewHolder进行绑定
    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favdrite, null);
        return new MyViewHodler(view);
    }

    //将数据与viewHolder进行绑定
    @Override
    public void onBindViewHolder(MyViewHodler holder, final int position) {
        Picasso.with(mContext).load(mList.get(position).getIcon()).into(holder.mImg);
        holder.mStamp.setText(mList.get(position).getStamp());
        holder.mSummary.setText(mList.get(position).getSummary());
        holder.mTitle.setText(mList.get(position).getTitle());
        //给每一个hodler.itemView设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLoadFavdriteListener!=null){
                    mOnLoadFavdriteListener.onLoadFavdrite(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHodler extends RecyclerView.ViewHolder {
        CircleImageView mImg;
        TextView mTitle;
        TextView mSummary;
        TextView mStamp;

        public MyViewHodler(View itemView) {
            super(itemView);
            mImg = (CircleImageView) itemView.findViewById(R.id.img_icon1);
            mTitle= (TextView) itemView.findViewById(R.id.txt_title1);
            mSummary= (TextView) itemView.findViewById(R.id.txt_summary1);
            mStamp= (TextView) itemView.findViewById(R.id.txt_stamp1);

        }
    }

}
