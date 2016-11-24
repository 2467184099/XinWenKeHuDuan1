package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import zhuoxin.edu.xinwenkehuduan.R;

/**
 * Created by Administrator on 2016/11/22.
 */
/*
* 动画
* */
public class typemoreFragment extends Fragment {
    TextView mText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.typemore,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText= (TextView) view.findViewById(R.id.txt_typemore);
        //设置执行状态
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.1F,1.0F);
        //设置执行时间
        alphaAnimation.setDuration(3000);
        //设置执行次数
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        //设置值型模式
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        //启动动画
        mText.startAnimation(alphaAnimation);

    }
}
