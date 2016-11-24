package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.LoginlogInfo;

/**
 * Created by Administrator on 2016/11/21.
 */
/*
* 用户中心适配器
* */
public class UserAdapter extends BaseAdapter<LoginlogInfo> {
    public UserAdapter(Context context) {
        super(context);
    }

    @Override
    public View setView(int position, View convertview, ViewGroup parent) {
        Hodler hodler=null;
        if (convertview==null){
            hodler=new Hodler();
            convertview=mInflater.inflate(R.layout.useradapter,parent,false);
            hodler.mTime= (TextView) convertview.findViewById(R.id.txt_time);
            hodler.maddress= (TextView) convertview.findViewById(R.id.txt_address);
             convertview.setTag(hodler);
        }else {
            hodler= (Hodler) convertview.getTag();
        }
        hodler.mTime.setText(mList.get(position).getTime());
        hodler.maddress.setText(mList.get(position).getAddress());
        return convertview;
    }
    static class Hodler{
        TextView mTime;
        TextView maddress;

    }
}
