package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.adapter.UserAdapter;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.LoginlogInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;

/**
 * Created by Administrator on 2016/10/31.
 */
/*
* 用户中心
* */
public class UserActivity extends AppCompatActivity implements View.OnClickListener, OnLoadRegisterListener {
    ImageView mBack;
    ImageView mImg_useractivity;
    Button mBtn;
    TextView mText1;
    TextView mText2;
    TextView mText3;
    ListView mLst;
    LinearLayout mLyt_take;
    LinearLayout mLyt_sel;
    PopupWindow mPopupWindow;
    RequestQueue mRequestQueue;
    String mToken;
    ArrayList<LoginlogInfo> mList;
    String mUid;
    String mIntegration;
    String path;
    File file;
    String mPortrait;
    String mComnum;
    String mLoginlog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useractivity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initView();
        popupwindow();
        volley();
    }

    //初始化view
    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_useractivity_back);
        mBtn = (Button) findViewById(R.id.btn_useractivity);
        mImg_useractivity = (ImageView) findViewById(R.id.img_useractivit);
        mText1 = (TextView) findViewById(R.id.txt_1);
        mText2 = (TextView) findViewById(R.id.txt_2);
        mText3 = (TextView) findViewById(R.id.txt_3);
        mLst = (ListView) findViewById(R.id.lst_useractivity);
        mImg_useractivity.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    private void popupwindow() {
        mPopupWindow = new PopupWindow();
        View view = this.getLayoutInflater().inflate(R.layout.popupwindow1, null);
        mPopupWindow.setContentView(view);
        mLyt_take = (LinearLayout) view.findViewById(R.id.lyt_take);
        mLyt_sel = (LinearLayout) view.findViewById(R.id.lyt_sel);
        mLyt_take.setOnClickListener(this);
        mLyt_sel.setOnClickListener(this);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    private void volley() {
        mRequestQueue = Volley.newRequestQueue(this);
        SharedPreferences count = this.getSharedPreferences("count", MODE_PRIVATE);
        mToken = count.getString("token", null);
        HttpUtils.connectionGET(HttpInfo.BASE_URL + HttpInfo.USER_URL + "ver=" + 1 + "&imei=" + 0 + "&token=" + mToken, this, mRequestQueue);
    }


    @Override
    public void getRegister(String message) {
        Log.e("=======", "message===" + message);
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONObject data = jsonObject.getJSONObject("data");
            mUid = data.getString("uid");
            mIntegration = data.getString("integration");
            Log.e("=====", "integration==" + mIntegration);
            mPortrait = data.getString("portrait");
            Log.e("=====", "portrait==" + mPortrait);
            mComnum = data.getString("comnum");
            Log.e("=====", "comnum==" + mComnum);
            SharedPreferences shar=this.getSharedPreferences("person",MODE_PRIVATE);
            SharedPreferences.Editor edit = shar.edit();
            edit.putString("uid",mUid);
            edit.putString("portrait",mPortrait);
            edit.commit();
            mLoginlog = data.getString("loginlog");
            Log.e("======", "loginlog====" + mLoginlog);
            mText1.setText(mUid);
            mText3.setText(mComnum);
            mText2.setText(mIntegration);
            Picasso.with(UserActivity.this).load(mPortrait).into(mImg_useractivity);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<LoginlogInfo>>() {
            }.getType();
            mList = gson.fromJson(mLoginlog, type);
            adapter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void adapter() {
        UserAdapter adapter = new UserAdapter(UserActivity.this);
        adapter.setData(mList);
        mLst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_useractivity_back:
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                UserActivity.this.finish();

                break;
            case R.id.btn_useractivity:
                SharedPreferences shar = this.getSharedPreferences("count", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = shar.edit();
                edit.clear();
                edit.putInt("result",-1);
                edit.commit();
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                UserActivity.this.finish();

                break;
            case R.id.img_useractivit:
                mPopupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.popupwindow1, null), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.lyt_take: {
                //调用系统相机
                //MediaStore.ACTION_IMAGE_CAPTURE相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //路径 用于保存照片
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    path = Environment.getExternalStorageDirectory().getPath();
                }
                //文件
                file = new File(path + File.separator + System.currentTimeMillis() + ".jpg");
                //设置好哦保存路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                //启动回调
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.lyt_sel: {
                //从图库中获取资源
                // Intent.ACTION_PICK 进入图库获取照片意图
                Intent intent = new Intent(Intent.ACTION_PICK);
                //设置类型
                intent.setType("image/*");
                startActivityForResult(intent, 2);
                break;
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("======", "data=" + data);
        Log.e("------", "resultCode=" + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    cropFromFile(file);
                   /* Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    mImg_useractivity.setImageBitmap(bitmap);*/
                    /*Bitmap bitmap   = mData.getParcelableExtra("mData");
                    mImg_useractivit y.setImageBitmap(bitmap);*/
                    break;
                }
                case 2: {
                   /* //通过内容提供者获取系统中的数据
                    ContentResolver contentResolver = getContentResolver();
                    //根据地制值拿数据
                    Uri uri = data.getData();
                    String[] array = {MediaStore.Images.Media.DATA};
                    Cursor cursor = contentResolver.query(uri, array, null, null, null);
                    //将游标移到第一位
                    cursor.moveToFirst();
                    String path = cursor.getString(cursor.getColumnIndex(array[0]));
                    cursor.close();
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    mImg_useractivity.setImageBitmap(bitmap);*/
                    crop(data.getData());
                    break;
                }
                case 3: {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    mImg_useractivity.setImageBitmap(bitmap);
                    break;
                }
            }

        }

    }

    public void crop(Uri uri) {
        //使用意图剪切照片
        Intent intent = new Intent();
        //设置要剪切的资源文件和类型
        intent.setDataAndType(uri, "image/*");
        //设置剪切
        intent.setAction("com.android.camera.action.CROP");
        //开启剪切
        intent.putExtra("crop", "true");
        //设置 裁剪框比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置裁剪后输出的照片大小
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        //设置剪切圆形图片
        intent.putExtra("circleCrop", "true");
        //设置返回数据
        intent.putExtra("return-data", true);
        intent.putExtra("scale", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //启动
        startActivityForResult(intent, 3);


    }

    public void cropFromFile(File file) {
        //使用意图剪切照片
        Intent intent = new Intent();
        //设置要剪切的资源文件和类型
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        //设置剪切
        intent.setAction("com.android.camera.action.CROP");
        //开启剪切
        intent.putExtra("crop", "true");
        //设置 裁剪框比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置裁剪后输出的照片大小
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        //设置剪切圆形图片
        intent.putExtra("circleCrop", "true");
        //设置返回数据
        intent.putExtra("return-data", true);
        intent.putExtra("scale", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        //启动
        startActivityForResult(intent, 3);


    }


}
