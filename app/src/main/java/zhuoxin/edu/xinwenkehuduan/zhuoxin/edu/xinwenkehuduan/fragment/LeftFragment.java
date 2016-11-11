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

public class LeftFragment extends Fragment implements View.OnClickListener {
    LinearLayout mLyt_favdrite;
    LinearLayout mLyt_LOCAL;
    LinearLayout mLyt_COMMENT;
    LinearLayout mLyt_PHOTO;
    FavdriteFragment mFavdriteFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.leftfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLyt_favdrite = (LinearLayout) view.findViewById(R.id.lyt_favdrite);
        mLyt_LOCAL = (LinearLayout) view.findViewById(R.id.lyt_LOCAL);
        mLyt_COMMENT = (LinearLayout) view.findViewById(R.id.lyt_COMMENT);
        mLyt_PHOTO = (LinearLayout) view.findViewById(R.id.lyt_PHOTO);
        mLyt_favdrite.setOnClickListener(this);
        mFavdriteFragment = new FavdriteFragment();
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

        }
    }
}
