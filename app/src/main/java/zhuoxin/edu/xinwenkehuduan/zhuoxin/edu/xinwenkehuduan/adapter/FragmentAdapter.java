package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/4.
 */
   /*
   * viewpager适配器
   * */
public class FragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mList;

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }


}
