package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment.CenterFragment;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.SqlUtils;

/**
 * Created by Administrator on 2016/11/1.
 */
/*
* web界面
* */
public class WebActivity extends AppCompatActivity implements View.OnClickListener, OnLoadRegisterListener {

    WebView mWebView;
    int mPosition;
    ImageView mImg_back;
    ImageView mImg_news;
    PopupWindow mPopupWindow;
    Button mBtn;
    Button mBtn1;
    ArrayList<ChildInfo> mList = new ArrayList<>();
    TextView mTxt;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webactivity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initData();
        webUrl();
        popupWindow();
        mRequestQueue = Volley.newRequestQueue(this);
        HttpUtils.connectionGET(HttpInfo.BASE_URL + HttpInfo.COMMENTNUM_URL + "ver=1&nid=" + mList.get(mPosition - 1).getNid(), this, mRequestQueue);
    }

    //popupWindow
    private void popupWindow() {
        mPopupWindow = new PopupWindow();
        //设置view
        View view = this.getLayoutInflater().inflate(R.layout.popupwindow, null);
        mBtn = (Button) view.findViewById(R.id.btn_popupwindow);
        mBtn1 = (Button) view.findViewById(R.id.btn_popupwindow_1);
        mBtn1.setOnClickListener(this);
        mBtn.setOnClickListener(this);
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


    //加载控件
    private void initData() {
        mWebView = (WebView) findViewById(R.id.web_webactivity);
        mImg_back = (ImageView) findViewById(R.id.img_back);
        mImg_news = (ImageView) findViewById(R.id.img_news);
        mTxt = (TextView) findViewById(R.id.txt_4);
        mImg_back.setOnClickListener(this);
        mImg_news.setOnClickListener(this);
        mTxt.setOnClickListener(this);
    }

    //加载网页
    private void webUrl() {
        //接受传递过来的数据
        Intent intent = this.getIntent();
        mPosition = intent.getIntExtra("position", -1);
        mList = CenterFragment.getData();
        //加载网页
        mWebView.loadUrl(mList.get(mPosition - 1).getLink());
        // 设置客户端的显示方式
        WebSettings settings = mWebView.getSettings();
        //使用JS代码 自动识别是手机端还是网页端
        settings.setJavaScriptEnabled(true);
        /*
        *
        * WebSettings.LayoutAlgorithm.NARROW_COLUMNS 尽可能在一行
        * WebSettings.LayoutAlgorithm.NORMAL 普通方式显示
        *WebSettings.LayoutAlgorithm.SINGLE_COLUMN 在一行中自动调整大小
        * */
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
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }


   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                WebActivity.this.finish();
                break;
            case R.id.img_news:
                mPopupWindow.showAsDropDown(v);
                break;
            case R.id.btn_popupwindow:
                SqlUtils sqlUtils = new SqlUtils(this);
                String summary = mList.get(mPosition - 1).getSummary();
                String icon = mList.get(mPosition - 1).getIcon();
                String stamp = mList.get(mPosition - 1).getStamp();

                int nid = mList.get(mPosition - 1).getNid();
                String link = mList.get(mPosition - 1).getLink();
                int type = mList.get(mPosition - 1).getType();
                ArrayList<ChildInfo> query = sqlUtils.query();
                for (int i = 0; i < query.size(); i++) {
                    if (nid == query.get(i).getNid()) {
                        Toast.makeText(this, "请勿重复收藏", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                sqlUtils.insert(summary, icon, stamp, title, nid, link, type);
                Toast.makeText(this, "收藏成功，请去收藏页面查看", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_popupwindow_1:
                ShareSDK.initSDK(this);
                OnekeyShare onekeyShare = new OnekeyShare();
                //关闭sso授权
                onekeyShare.disableSSOWhenAuthorize();
                onekeyShare.setTitle(mList.get(mPosition - 1).getTitle());
                onekeyShare.setTitleUrl(mList.get(mPosition - 1).getLink());
                onekeyShare.setText(mList.get(mPosition - 1).getSummary());
                onekeyShare.show(this);
                break;
            case R.id.txt_4:
                int nid1 = mList.get(mPosition - 1).getNid();
                Intent intent = new Intent(WebActivity.this, CommentActivity.class);
                intent.putExtra("nid", nid1);
                startActivity(intent);
                break;

        }
    }

    String title;
    @Override
    public void getRegister(String message) {
        Log.e("------", message);
        try {
            JSONObject jsonObject = new JSONObject(message);
            String message1 = jsonObject.getString("message");
            int status = jsonObject.getInt("status");
            int data = jsonObject.getInt("data");
            title = mList.get(mPosition - 1).getTitle();
            SharedPreferences shar = this.getSharedPreferences("web", MODE_PRIVATE);
            SharedPreferences.Editor edit = shar.edit();
            edit.putString("title",title);
            edit.commit();
            mTxt.setText(data+"跟帖");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}