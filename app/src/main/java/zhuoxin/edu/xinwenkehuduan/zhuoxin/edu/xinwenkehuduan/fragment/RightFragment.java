package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import zhuoxin.edu.xinwenkehuduan.R;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener{
    ImageView mImg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rightfragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImg= (ImageView) view.findViewById(R.id.img_qq);
        mImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ShareSDK.initSDK(getContext());
        OnekeyShare onekeyShare=new OnekeyShare();
        //关闭sso授权
        onekeyShare.disableSSOWhenAuthorize();
        onekeyShare.setTitle("标题");
        onekeyShare.setTitleUrl("http://sharesdk.cn");
        onekeyShare.setText("新闻客户端");
        onekeyShare.show(getContext());
    }
}
