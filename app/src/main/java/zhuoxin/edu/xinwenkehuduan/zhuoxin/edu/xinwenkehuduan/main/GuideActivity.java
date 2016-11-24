package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.GuideAdapter;

/**
 * Created by Administrator on 2016/10/27.
 */
/*
* 引导界面
* */
public class GuideActivity extends AppCompatActivity {
    ViewPager mViewPager;
    //四个点
    ImageView[] mImg;
    //滑动的四个图片
    ImageView[] mStr;
    //将图片id放入数组中
    int[] mId = {R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guideactivity);
        initView();
        initData();
        initAdapter();
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mImg.length; i++) {
                    mImg[i].setImageResource(R.mipmap.adware_style_default);
                    if (position >= 3) {
                        Intent intent = new Intent(GuideActivity.this, LogoActivity.class);
                        startActivity(intent);
                        GuideActivity.this.finish();
                        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0F,0.1F);
                        alphaAnimation.setDuration(3000);
                        alphaAnimation.setRepeatCount(1);
                        alphaAnimation.setRepeatMode(Animation.RESTART);
                        mImg[3].startAnimation(alphaAnimation);
                    }


                }
                mImg[position].setImageResource(R.mipmap.red);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //加载控件
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_guide);
        mImg = new ImageView[4];
        mImg[0] = (ImageView) findViewById(R.id.img0_guide);
        mImg[1] = (ImageView) findViewById(R.id.img1_guide);
        mImg[2] = (ImageView) findViewById(R.id.img2_guide);
        mImg[3] = (ImageView) findViewById(R.id.img3_guide);
        mStr = new ImageView[4];
        //ViewPager默认显示第0位的资源  所以初始化 第0位 点位红色
        mImg[0].setImageResource(R.mipmap.red);
    }

    //数据源
    private void initData() {
        for (int i = 0; i < 4; i++) {
            mStr[i] = new ImageView(this);
            mStr[i].setImageResource(mId[i]);

        }
    }

    private void initAdapter() {
        GuideAdapter guideAdapter = new GuideAdapter(mStr);
        mViewPager.setAdapter(guideAdapter);
        guideAdapter.notifyDataSetChanged();
    }
}
