package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
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
        super.onContentChanged();
        mVip = (ViewPager) findViewById(R.id.mvp);
        mImg1 = (ImageView) findViewById(R.id.img_left1);
        mImg2 = (ImageView) findViewById(R.id.img_right1);
        mText = (TextView) findViewById(R.id.txt_main1);
        mImg1.setOnClickListener(this);
        mImg2.setOnClickListener(this);
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
    
    public void register() {
        mVip.setCurrentItem(mVip.getCurrentItem() + 1);
        mText.setText("用户注册");
    }

    public void forget() {
        mVip.setCurrentItem(mVip.getCurrentItem() + 2);
        mText.setText("忘记密码");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_left1:
                Intent intent = new Intent(FragmentActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.img_right1:
                Intent intent1 = new Intent(FragmentActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

        }
    }
}
