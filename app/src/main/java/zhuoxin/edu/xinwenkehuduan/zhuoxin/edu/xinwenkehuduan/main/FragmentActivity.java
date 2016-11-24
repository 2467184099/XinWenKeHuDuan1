package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.FragmentAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.ForgetFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.LoginFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.RegisterFragment;

/**
 * Created by Administrator on 2016/11/4.
 */
/*
* viewpager界面
* */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager mVip;
    ArrayList<Fragment> mList;
    FragmentAdapter mFragmentAdapter;
    ImageView mImg1;
    ImageView mImg2;
    TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentactivity);

    }

    @Override
    public void onContentChanged() {
        initView();
        viewPager();
    }
        //viewpager的数据源
    private void viewPager() {
        LoginFragment loginFragment = new LoginFragment();
        RegisterFragment registerFragment = new RegisterFragment();
        ForgetFragment forgetFragment = new ForgetFragment();
        mList = new ArrayList<>();
        mList.add(loginFragment);
        mList.add(registerFragment);
        mList.add(forgetFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mList);
        mVip.setAdapter(fragmentAdapter);
        fragmentAdapter.notifyDataSetChanged();
    }

    //初始化view
    private void initView() {
        super.onContentChanged();
        mVip = (ViewPager) findViewById(R.id.mvp);
        mImg1 = (ImageView) findViewById(R.id.img_left1);
        mImg2 = (ImageView) findViewById(R.id.img_right1);
        mText = (TextView) findViewById(R.id.txt_main1);
        mImg1.setOnClickListener(this);
        mImg2.setOnClickListener(this);
    }

    public void register() {
        mVip.setCurrentItem(mVip.getCurrentItem() + 1);
        mText.setText("用户注册");
    }

    public void forget() {
        mVip.setCurrentItem(mVip.getCurrentItem() + 2);
        mText.setText("忘记密码");
    }
    public void login(){
        mVip.setCurrentItem(mVip.getCurrentItem() -1);
        mText.setText("用户登陆");
    }

 //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_left1:
                FragmentActivity.this.finish();
                MainActivity.data2();
                break;
            case R.id.img_right1:
                FragmentActivity.this.finish();
                MainActivity.data2();
                break;

        }
    }
}
