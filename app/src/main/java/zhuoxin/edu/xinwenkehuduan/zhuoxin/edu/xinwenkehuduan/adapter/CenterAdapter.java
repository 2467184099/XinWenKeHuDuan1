package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;

/**
 * Created by Administrator on 2016/10/31.
 */
/*
* 主界面适配器
* */
public class CenterAdapter extends BaseAdapter<ChildInfo> {
    public CenterAdapter(Context context) {
        super(context);

    }


    @Override
    public View setView(int position, View convertview, ViewGroup parent) {
        Hodler hodler = null;
        if (null == convertview) {
            hodler = new Hodler();
            convertview = mInflater.inflate(R.layout.centeradapter, parent, false);
            hodler.mImg_icon = (CircleImageView) convertview.findViewById(R.id.img_icon);
            hodler.mText_title = (TextView) convertview.findViewById(R.id.txt_title);
            hodler.mText_summary = (TextView) convertview.findViewById(R.id.txt_summary);
            hodler.mText_stamp = (TextView) convertview.findViewById(R.id.txt_stamp);
            convertview.setTag(hodler);
        } else {
            hodler = (Hodler) convertview.getTag();
        }
        hodler.mText_title.setText(mList.get(position).getTitle());
        hodler.mText_summary.setText(mList.get(position).getSummary());
        hodler.mText_stamp.setText(mList.get(position).getStamp());
       Picasso.with(convertview.getContext()).load(mList.get(position).getIcon()).into(hodler.mImg_icon);
        return convertview;
    }

  /*  ArrayList<ChildInfo> mList;
    Context mContext;
    public CenterAdapter( Context context){
        this.mContext =context;

    }
    public void setData(ArrayList<ChildInfo> mList){
        this.mList=mList;
    }
    @Override
    public int getCount() {
        return null==mList?0:mList.size() ;
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
        Hodler hodler=null;
        if (convertView==null) {
            hodler=new Hodler();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.centeradapter,parent,false);
            hodler.mImg_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            hodler.mText_title = (TextView) convertView.findViewById(R.id.txt_title);
            hodler.mText_summary = (TextView) convertView.findViewById(R.id.txt_summary);
            hodler.mText_stamp = (TextView) convertView.findViewById(R.id.txt_stamp);
            convertView.setTag(hodler);
        }else {
            hodler= (Hodler) convertView.getTag();
        }
        hodler.mText_title.setText(mList.get(position).getTitle());
        hodler.mText_summary.setText(mList.get(position).getSummary());
        hodler.mText_stamp.setText(mList.get(position).getStamp());
        Picasso.with(mContext).load(mList.get(position).getIcon()).into(hodler.mImg_icon);

        return convertView;
    }*/

    public class Hodler {
        CircleImageView mImg_icon;
        TextView mText_title;
        TextView mText_summary;
        TextView mText_stamp;
    }
}
