package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/14.
 */
 /*
 * 取消收藏
 *
 * */
public class FavdriteShowActivity extends AppCompatActivity implements View.OnClickListener {
    int mPosition;
    WebView mWeb;
    Button mBtn;
    ArrayList<ChildInfo> mQuery;
    PopupWindow mPopupWindow;
    ImageView mImg;
    SqlUtils mSqlUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favdriteshow);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        webView();
        mImg = (ImageView) findViewById(R.id.img_news_favdriteshow);
        mImg.setOnClickListener(this);
        View view = this.getLayoutInflater().inflate(R.layout.popupwindow_3, null);
        mBtn = (Button) view.findViewById(R.id.btn_popupwindow_3);
        mBtn.setOnClickListener(this);
        mPopupWindow = new PopupWindow();
        mPopupWindow.setContentView(view);
        //设置宽高
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        mPopupWindow.setFocusable(true);
        //设置点击取消
        mPopupWindow.setOutsideTouchable(true);
        //设置背景
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

    }

    //新闻内容显示
    private void webView() {
        Intent intent = this.getIntent();
        mPosition = intent.getIntExtra("position", -1);
        mWeb = (WebView) findViewById(R.id.web_favdriteshow);
        mSqlUtils = new SqlUtils(this);
        mQuery = mSqlUtils.query();
        mWeb.loadUrl(mQuery.get(mPosition).getLink());
        // 设置客户端的显示方式
        WebSettings settings = mWeb.getSettings();
        //使用JS代码 自动识别是手机端还是网页端
        settings.setJavaScriptEnabled(true);
        //设置自动适应屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置推荐使用的窗口
        settings.setUseWideViewPort(true);
        //自适应大小 且可以任意放大或缩小
        settings.setLoadWithOverviewMode(true);
        //是否能放大或缩小
        settings.setSupportZoom(true);
        //设置放大缩小按钮显示
        settings.setBuiltInZoomControls(true);
        //设置调整窗口
        settings.setSupportMultipleWindows(true);
        //设置显示控制器
        settings.setDisplayZoomControls(true);
        //设置自己的客户端显示
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_news_favdriteshow:
                mPopupWindow.showAsDropDown(v);
                break;
            case R.id.btn_popupwindow_3:
                for (int i = 0; i < mQuery.size(); i++) {
                    int nid = mQuery.get(i).getNid();
                    mSqlUtils.delete(nid);
                    mQuery.remove(mPosition-1);
                    Toast.makeText(this,"已经取消收藏",Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(FavdriteShowActivity.this,MainActivity.class));
                    finish();
                }
                break;
        }

    }
}
