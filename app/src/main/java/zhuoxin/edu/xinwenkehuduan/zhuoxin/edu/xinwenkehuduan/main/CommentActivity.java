package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.maxwin.view.XListView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.CommentAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.CgroupInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildCInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CommentActivity extends AppCompatActivity implements OnLoadRegisterListener, XListView.IXListViewListener, View.OnClickListener {
    int mNid;
    ImageView mBack;
    ImageView mImg;
    EditText mEtxt;
    XListView mXlst;
    RequestQueue mRequestQueue;
    ArrayList<ChildCInfo> mData, mlist;
    CommentAdapter mCommentAdapter;
    int mCid;
     Handler mHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentactivity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mlist = new ArrayList<>();
        mHandler=new Handler();
        inter();
        initView();
        mCommentAdapter = new CommentAdapter(CommentActivity.this);
        mCommentAdapter.setData(mlist);
        mXlst.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();


    }

    //初始化
    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_back_comment);
        mEtxt = (EditText) findViewById(R.id.etxt_comment);
        mImg = (ImageView) findViewById(R.id.img_comment);
        mXlst = (XListView) findViewById(R.id.xlst_comment);
        mImg.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }

    //接受传过来的数据
    private void inter() {
        Intent intent = this.getIntent();
        mNid = intent.getIntExtra("nid", -1);
        Log.e("------", "mNid=" + mNid);
        mRequestQueue = Volley.newRequestQueue(this);
        HttpUtils.connectionGET(HttpInfo.BASE_URL + HttpInfo.COMMENTSHOW_URL + "ver=1&nid=" + mNid
                + "&type=1&stamp=yyyyMMdd&cid=0&dir=1", this, mRequestQueue);
    }



    @Override
    public void getRegister(String message) {
        Log.e("------", "" + message);
        Gson gson = new Gson();
        CgroupInfo info = gson.fromJson(message, new TypeToken<CgroupInfo>() {
        }.getType());
        mData = info.getData();
        for (int i = 0; i < mData.size(); i++) {
            mCid = mData.get(i).getCid();
        }
        mlist.addAll(mData);
        //设置两个方法
        //上拉加载
        mXlst.setPullLoadEnable(true);
        //下拉刷新
        mXlst.setPullRefreshEnable(true);
        //上拉刷新和下拉加载都必须设置监听事件
        mXlst.setXListViewListener(CommentActivity.this);

    }

    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
                mlist.clear();
                HttpUtils.connectionGET(HttpInfo.BASE_URL + HttpInfo.COMMENTSHOW_URL + "ver=1&nid=" + mNid + "&type=1&stamp=yyyyMMdd&cid=" + mCid + "&dir=1&cnt=20", CommentActivity.this, mRequestQueue);
            }
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
                HttpUtils.connectionGET(HttpInfo.BASE_URL + HttpInfo.COMMENTSHOW_URL + "ver=1&nid=" + mNid + "&type=1&stamp=yyyyMMdd&cid=" + mCid + "&dir=2&cnt=20", CommentActivity.this, mRequestQueue);
            }
        }, 2000);
    }

    //停止加载和刷新
    public void stop() {
        //停止加载和刷新
        mXlst.stopLoadMore();
        mXlst.stopRefresh();
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXlst.setRefreshTime(dateFormat.format(new Date(System.currentTimeMillis())));
    }
    OkHttpClient mOkHttpClient;
    String mToken;
    String string;
    Handler handler1;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_comment: {
                CommentActivity.this.finish();
            }
            break;
            case R.id.img_comment: {
                String ctx = mEtxt.getText().toString();
                SharedPreferences count = this.getSharedPreferences("count", MODE_PRIVATE);
                mToken = count.getString("token", null);
                Log.e("============", "token==" + mToken);
                mOkHttpClient =new OkHttpClient();
                //构造器模式，构造请求
                FormBody.Builder builder=new FormBody.Builder();
                builder.add("ver","1");
                builder.add("nid",mNid+"");
                builder.add("token",mToken);
                builder.add("imei","0");
                builder.add("ctx",ctx);
                //获取请求体
                RequestBody requestBody = builder.build();
                Request.Builder requestbuilder=new Request.Builder();
                //通过构造器构建请求
                requestbuilder.url(HttpInfo.BASE_URL+HttpInfo.CMT_URL);

                //使用构造器   将请球体放入请求中
                requestbuilder.post(requestBody);
                //构建请求
                Request request = requestbuilder.build();
                //获取call模型
                Call call=mOkHttpClient.newCall(request);
                //执行请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                       //响应体
                        ResponseBody responseBody=response.body();
                        string = responseBody.string();
                        handler1.sendEmptyMessage(1);

                    }
                });
                handler1=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.e("-------","string=="+string);
                        try {
                            JSONObject jsonObject=new JSONObject(string);
                            String message = jsonObject.getString("message");
                            int status = jsonObject.getInt("status");
                            JSONObject data = jsonObject.getJSONObject("data");
                            int result = data.getInt("result");
                            String explain = data.getString("explain");
                            if (status==0) {
                                Toast.makeText(CommentActivity.this,explain,Toast.LENGTH_SHORT).show();
                                mEtxt.setText("");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


            }
            break;
        }
    }
}
