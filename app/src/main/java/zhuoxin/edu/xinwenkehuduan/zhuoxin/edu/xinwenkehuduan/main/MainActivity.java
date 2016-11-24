package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.CenterFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.LeftFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.LoginFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.RightFragment;
/*
* 主界面
* */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImg_left;
    ImageView mImg_right;
    CenterFragment mCenterFragment;
    LeftFragment mLeftFragment;
    RightFragment mRightFragment;
    LoginFragment mLoginFragment;
    public static DrawerLayout mDrawerLayout;
    ImageView mImg_dark;
    TextView mText_rightfragment;
    public static TextView mText_main;
    LinearLayout mLyt_news;
    String mUid;
    String mPortrait;
    int mResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initData();
        fragment();
        SharedPreferences person = this.getSharedPreferences("person", Context.MODE_PRIVATE);
        mUid = person.getString("uid", null);
        mPortrait = person.getString("portrait", null);
        Log.e("===========", "uid=" + mUid + "portrait=" + mPortrait);
        SharedPreferences count = this.getSharedPreferences("count", MODE_PRIVATE);
        mResult = count.getInt("result", -1);
        Log.e("----------", "result===" + mResult);
        if (mResult == 0 & mUid != null) {
            Picasso.with(this).load(mPortrait).into(mImg_dark);
            mText_rightfragment.setText(mUid);
        }


    }

    private void fragment() {
        mLeftFragment = new LeftFragment();
        mRightFragment = new RightFragment();
        mCenterFragment = new CenterFragment();
        mLoginFragment = new LoginFragment();
        FragmentManager Manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = Manager.beginTransaction();
        fragmentTransaction.replace(R.id.lyt_center, mCenterFragment);
        fragmentTransaction.commit();
    }

    //加载控件及设置监听事件
    private void initData() {

        mImg_left = (ImageView) findViewById(R.id.img_left);
        mImg_right = (ImageView) findViewById(R.id.img_right);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlyt_main);
        mImg_dark = (ImageView) findViewById(R.id.img_dark);
        mText_main = (TextView) findViewById(R.id.txt_main);
        mText_rightfragment = (TextView) findViewById(R.id.txt_rightfragment);
        mLyt_news = (LinearLayout) findViewById(R.id.lyt_news);
        mLyt_news.setOnClickListener(this);
        mText_rightfragment.setOnClickListener(this);
        mImg_dark.setOnClickListener(this);
        mImg_left.setOnClickListener(this);
        mImg_right.setOnClickListener(this);
    }

    //打开菜单点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_left:

                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT) == false) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == true) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }

                break;
            case R.id.img_right:

                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == false) {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);

                } else {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT) == true) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                break;
            case R.id.img_dark:
               /* FragmentManager Manager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = Manager.beginTransaction();
                fragmentTransaction.replace(R.id.lyt_center, mLoginFragment);
                fragmentTransaction.commit();*/
                if (mResult==0&&mUid!=null){
                     Intent intent=new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    startActivity(intent);
                    if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == true) {
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                    mText_main.setText("用户登录");
                }

                break;
            case R.id.txt_rightfragment:
                /*FragmentManager Manager0 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction0 = Manager0.beginTransaction();
                fragmentTransaction0.replace(R.id.lyt_center, mLoginFragment);
                fragmentTransaction0.commit();*/
                if (mResult==0&&mUid!=null){
                    Intent intent=new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    startActivity(intent);
                    if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == true) {
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                    mText_main.setText("用户登录");
                }
                break;
            case R.id.lyt_news:
                FragmentManager Manager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = Manager1.beginTransaction();
                fragmentTransaction1.replace(R.id.lyt_center, mCenterFragment);
                fragmentTransaction1.commit();
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT) == true) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                mText_main.setText("资讯");
                break;

        }
    }

    public static void data() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT) == false) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    public static void data1() {
        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == false) {
            mDrawerLayout.openDrawer(Gravity.RIGHT);
        } else {
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }
    public static void data2(){
        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == true) {
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }
}