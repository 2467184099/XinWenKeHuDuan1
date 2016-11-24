package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.MainActivity;

/**
 * Created by Administrator on 2016/10/28.
 */
/*
* 左边菜单界面
* */
public class LeftFragment extends Fragment implements View.OnClickListener {
    LinearLayout mLyt_favdrite;
    LinearLayout mLyt_CAMERA;
    LinearLayout mLyt_COMMENT;
    LinearLayout mLyt_PHOTO;
    FavdriteFragment mFavdriteFragment;
    CameraFragment mCameraFragment;
    FollowFragment mFollowFragment;
    ThreeCacheFragment mCacheFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.leftfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLyt_favdrite = (LinearLayout) view.findViewById(R.id.lyt_favdrite);
        mLyt_CAMERA = (LinearLayout) view.findViewById(R.id.lyt_CAMERA);
        mLyt_COMMENT = (LinearLayout) view.findViewById(R.id.lyt_COMMENT);
        mLyt_PHOTO = (LinearLayout) view.findViewById(R.id.lyt_PHOTO);
        mLyt_favdrite.setOnClickListener(this);
        mLyt_CAMERA.setOnClickListener(this);
        mLyt_COMMENT.setOnClickListener(this);
        mLyt_PHOTO.setOnClickListener(this);
        mFavdriteFragment = new FavdriteFragment();
        mCameraFragment = new CameraFragment();
        mFollowFragment = new FollowFragment();
        mCacheFragment = new ThreeCacheFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lyt_favdrite:
                FragmentManager Manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = Manager.beginTransaction();
                fragmentTransaction.replace(R.id.lyt_center, mFavdriteFragment);
                fragmentTransaction.commit();
                MainActivity.data();
                MainActivity.mText_main.setText("收藏新闻");
                break;
            case R.id.lyt_CAMERA:
                FragmentManager Manager1 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = Manager1.beginTransaction();
                fragmentTransaction1.replace(R.id.lyt_center, mCameraFragment);
                fragmentTransaction1.commit();
                MainActivity.data();
                MainActivity.mText_main.setText("自定义照相机");
                break;
            case R.id.lyt_COMMENT:
                FragmentManager Manager2 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = Manager2.beginTransaction();
                fragmentTransaction2.replace(R.id.lyt_center, mFollowFragment);
                fragmentTransaction2.commit();
                MainActivity.data();
                MainActivity.mText_main.setText("跟帖");
                break;
            case R.id.lyt_PHOTO:
                FragmentManager Manager3 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = Manager3.beginTransaction();
                fragmentTransaction3.replace(R.id.lyt_center, mCacheFragment);
                fragmentTransaction3.commit();
                MainActivity.data();
                MainActivity.mText_main.setText("图片");
                break;

        }
    }
}
