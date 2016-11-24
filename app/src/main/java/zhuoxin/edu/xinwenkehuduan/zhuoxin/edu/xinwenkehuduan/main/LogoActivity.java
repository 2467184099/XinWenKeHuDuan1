package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;


import zhuoxin.edu.xinwenkehuduan.R;

/**
 * Created by Administrator on 2016/10/27.
 */
/*
* logo界面
* */
public class LogoActivity extends AppCompatActivity {
    public static final String sPREFC_NAME = "bb";
    public static final String sIS_FIRST = "first";
    ImageView mImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = this.getSharedPreferences(sPREFC_NAME, MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean(sIS_FIRST, true);
        if (flag) {
            Intent intent = new Intent(LogoActivity.this, GuideActivity.class);
            startActivity(intent);
            LogoActivity.this.finish();
            //获取编辑器对象
            SharedPreferences.Editor edit = sharedPreferences.edit();
            //使用编辑器对象添加数据
            edit.putBoolean(sIS_FIRST, false);
            //提交数据
            edit.commit();

        } else {
            setContentView(R.layout.logoacyivity);
            mImg= (ImageView) findViewById(R.id.img_logo);
            //设置执行状态
            AlphaAnimation alphaAnimation=new AlphaAnimation(0.1F,1.0F);
            //设置执行时间
            alphaAnimation.setDuration(1000);
            //设置执行次数
            alphaAnimation.setRepeatCount(1);
            //设置值型模式
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            //启动动画
            mImg.startAnimation(alphaAnimation);
            //动画设置监听事件
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Intent intent=new Intent(LogoActivity.this,MainActivity.class);
                    startActivity(intent);
                    LogoActivity.this.finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

    }
}
