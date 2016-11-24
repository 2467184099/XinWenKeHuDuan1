package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildCInfo;

/**
 * Created by Administrator on 2016/11/18.
 */
/*
* 评论适配器
* */

public class CommentAdapter extends BaseAdapter<ChildCInfo> {
    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public View setView(int position, View convertview, ViewGroup parent) {
        Hodler hodler=null;
        if (convertview==null) {
            hodler=new Hodler();
            convertview= mInflater.inflate(R.layout.commentadapter,parent,false);
            hodler.mUid= (TextView) convertview.findViewById(R.id.txt_commentadapter);
            hodler.mStamp= (TextView) convertview.findViewById(R.id.txt_commentadapter1);
            hodler.mContent= (TextView) convertview.findViewById(R.id.txt_commentadapter2);
            hodler.mPortrait= (ImageView) convertview.findViewById(R.id.img_commentadapter);
            convertview.setTag(hodler);
        }else {
            hodler= (Hodler) convertview.getTag();
        }
        hodler.mUid.setText(mList.get(position).getUid());
        hodler.mStamp.setText(mList.get(position).getStamp());
        hodler.mContent.setText(mList.get(position).getContent());
        Picasso.with(convertview.getContext()).load(mList.get(position).getPortrait()).into(hodler.mPortrait);
        return convertview;
    }

    static class Hodler {
        ImageView mPortrait;
        TextView mUid;
        TextView mStamp;
        TextView mContent;
    }
}
