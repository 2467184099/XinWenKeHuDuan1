package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.File;

import zhuoxin.edu.xinwenkehuduan.R;

/**
 * Created by Administrator on 2016/10/31.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mBack;
    ImageView mImg_useractivity;
    Button mBtn;
    TextView mText1;
    TextView mText2;
    TextView mText3;
    ListView mLst;
    String mName;
    LinearLayout mLyt_take;
    LinearLayout mLyt_sel;
    PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        mName = intent.getStringExtra("name");
        setContentView(R.layout.useractivity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initView();
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

    //初始化view
    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_useractivity_back);
        mBtn = (Button) findViewById(R.id.btn_useractivity);
        mImg_useractivity = (ImageView) findViewById(R.id.img_useractivit);
        mText1 = (TextView) findViewById(R.id.txt_1);
        mText2 = (TextView) findViewById(R.id.txt_2);
        mText3 = (TextView) findViewById(R.id.txt_3);
        mLst = (ListView) findViewById(R.id.lst_useractivity);
        mText1.setText(mName);
        mImg_useractivity.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    String path;
    File file;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_useractivity_back:
                startActivity(new Intent(UserActivity.this, FragmentActivity.class));
                finish();
                break;
            case R.id.btn_useractivity:
                startActivity(new Intent(UserActivity.this, FragmentActivity.class));
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
        Log.e("======","data="+data);
        Log.e("------", "resultCode=" + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    //cropFromFile(file);
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    mImg_useractivity.setImageBitmap(bitmap);
                    /*Bitmap bitmap   = data.getParcelableExtra("data");
                    mImg_useractivit y.setImageBitmap(bitmap);*/
                    break;
                }
                case 2: {
                    //通过内容提供者获取系统中的数据
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
                    mImg_useractivity.setImageBitmap(bitmap);
                   // crop(data.getData());
                    break;
                }
                case 3: {
                    Bitmap bitmap= data.getParcelableExtra("data");
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
      intent.putExtra("circleCrop","true");
        //设置返回数据
        intent.putExtra("return_data", true);
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
   intent.putExtra("circleCrop","true");
        //设置返回数据
        intent.putExtra("return_data", true);
        //启动
        startActivityForResult(intent, 3);


    }
}
