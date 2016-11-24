package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/27.
 */
/*
* 引导界面适配器
* */
public class GuideAdapter extends PagerAdapter {
    ImageView [] mStr;
    public GuideAdapter(ImageView [] mStr){
        this.mStr=mStr;

    }
    @Override
    public int getCount() {
        return null==mStr?0:mStr.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=mStr[position];
        container.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mStr[position]);
    }
}
