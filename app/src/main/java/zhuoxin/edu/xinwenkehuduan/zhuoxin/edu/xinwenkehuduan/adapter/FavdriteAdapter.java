package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/11.
 */

public class FavdriteAdapter extends BaseAdapter<ChildInfo> {
    public FavdriteAdapter(Context context) {
        super(context);
    }

    @Override
    public View setView(final int position, View convertview, ViewGroup parent) {
       Hodler hodler=null;
        if (null == convertview) {
           hodler=new Hodler();
            convertview = mInflater.inflate(R.layout.favdrite, parent, false);
            hodler.mImg_icon = (CircleImageView) convertview.findViewById(R.id.img_icon1);
            hodler.mText_title = (TextView) convertview.findViewById(R.id.txt_title1);
            hodler.mText_summary = (TextView) convertview.findViewById(R.id.txt_summary1);
            hodler.mText_stamp = (TextView) convertview.findViewById(R.id.txt_stamp1);
            hodler.mBtn= (Button) convertview.findViewById(R.id.btn_favdrite);
            convertview.setTag(hodler);
        } else {
            hodler = (Hodler) convertview.getTag();
        }
        hodler.mText_title.setText(mList.get(position).getTitle());
        hodler.mText_summary.setText(mList.get(position).getSummary());
        hodler.mText_stamp.setText(mList.get(position).getStamp());
        Picasso.with(convertview.getContext()).load(mList.get(position).getIcon()).into(hodler.mImg_icon);
        final View finalConvertview = convertview;
        hodler.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlUtils sqlUtils=new SqlUtils(finalConvertview.getContext());
                int nid = mList.get(position).getNid();
                sqlUtils.delete(nid);
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertview;
    }
    public class Hodler {
        CircleImageView mImg_icon;
        TextView mText_title;
        TextView mText_summary;
        TextView mText_stamp;
        Button mBtn;
    }
}
