package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadCameraListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.view.CameraView;

/**
 * Created by Administrator on 2016/11/14.
 */
/*
* 照相机
* */

public class CameraFragment extends Fragment implements OnLoadCameraListener,View.OnClickListener{
    SurfaceView mSurfaceView;
    SeekBar mSeekBar;
    Button mBtn;
    CameraView mCameraView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* //1获取照相机对象
        Camera open = Camera.open();
        // 2设置参数
        //获取参数对象
        Camera.Parameters parameters = open.getParameters();
        //设置旋转角度
       // parameters.setRotation(120);
        //parameters.setZoom(2);设置焦距倍数
       // parameters.getPictureFormat(ImageFormat.JPEG);设置照片格式
        //parameters.setFlashMode();闪光灯模式
        //parameters.setFocusMode();自动聚焦
        //parameters.setJpegQuality(80);照片质量
        //parameters.setWhiteBalance();自动补光
       //3在第二步基础上进行保存参数
       // open.setParameters(Camera.Parameters);
        //4设置横屏还是竖屏
        //open.setDisplayOrientation(2);
        //5设置holeder
        //open.setPreviewDisplay(SurfaceHolder);
        //6开启预览 必须设置
       // open.startPreview();
        //7拍照 需要添加回调事件
        //8持续拍照 每拍一次需要调一次startpreview
        //9停止拍照  stoppreview
        //10 release 必须释放资源*/
        initView(view);
        //回调
        SurfaceHolder holder = mSurfaceView.getHolder();
        //设置参数
        holder.setSizeFromLayout();
        //设置屏幕常亮
        holder.setKeepScreenOn(true);
        //设置照片格式
        holder.setFormat(PixelFormat.JPEG);
        //SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS 设置surface中的数据来自于照相机
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mCameraView = new CameraView(this);
        holder.addCallback(mCameraView);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               mCameraView.setZoom(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int maxZoom = mCameraView.getMaxZoom();
                mSeekBar.setMax(maxZoom);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    //初始化view
    private void initView(View view) {
        mSurfaceView = (SurfaceView) view.findViewById(R.id.surfaceview);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        mBtn = (Button) view.findViewById(R.id.btn_take);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void getBitmap(Bitmap bitmap) {
//        Intent intent=new Intent(getActivity(), CameraShowActivity.class);
//        intent.putExtra("img",bitmap);
//        startActivity(intent);
        Toast.makeText(getActivity(),"拍照成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
       mCameraView.takePicture();
    }
}
