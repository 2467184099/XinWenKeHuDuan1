package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.ForgetFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.LoginFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.RegisterFragment;

/**
 * Created by Administrator on 2016/11/4.
 */

public class NewActivity extends AppCompatActivity {
    ViewPager mVip;
    ArrayList<Fragment> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mVip = (ViewPager) findViewById(R.id.vip);
        LoginFragment loginFragment = new LoginFragment();
        RegisterFragment registerFragment = new RegisterFragment();
        ForgetFragment forgetFragment = new ForgetFragment();
        mList = new ArrayList<>();
        mList.add(loginFragment);
        mList.add(registerFragment);
        mList.add(forgetFragment);
        mVip.setAdapter(fragmentPagerAdapter);

    }

    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return null == mList ? 0 : mList.size();
        }
    };
}
