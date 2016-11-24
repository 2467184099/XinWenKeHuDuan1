package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.FollowInfo;

/**
 * Created by Administrator on 2016/11/22.
 */
/*
* 跟帖适配器
* */

public class FollowAdapter extends BaseAdapter<FollowInfo> {
    public FollowAdapter(Context context) {
        super(context);
    }

    @Override
    public View setView(int position, View convertview, ViewGroup parent) {
        Hodler hodler=null;
        if (convertview==null) {
            hodler=new Hodler();
            convertview=mInflater.inflate(R.layout.followadapter,parent,false);
            hodler.mUid= (TextView) convertview.findViewById(R.id.txt_followadapter);
            hodler.mStamp= (TextView) convertview.findViewById(R.id.txt_followadapter1);
            hodler.mCtx= (TextView) convertview.findViewById(R.id.txt_followadapter2);
            hodler.mTitle= (TextView) convertview.findViewById(R.id.txt_followadapter3);
            hodler.mImg= (CircleImageView) convertview.findViewById(R.id.cicr);
            convertview.setTag(hodler);
        }else {
            hodler= (Hodler) convertview.getTag();
        }
        hodler.mUid.setText(mList.get(position).getUid());
        hodler.mStamp.setText(mList.get(position).getStamp());
        hodler.mCtx.setText(mList.get(position).getCtx());
        hodler.mTitle.setText(mList.get(position).getTitle());
        Picasso.with(convertview.getContext()).load(R.mipmap.bb).into(hodler.mImg);
        return convertview;
    }
    static class Hodler{
        TextView mUid;
        TextView mStamp;
        TextView mCtx;
        TextView mTitle;
        CircleImageView mImg;
    }
}
