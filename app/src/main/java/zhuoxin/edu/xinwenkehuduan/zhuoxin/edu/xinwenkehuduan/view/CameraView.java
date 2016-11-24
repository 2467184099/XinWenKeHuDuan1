package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadCameraListener;

/**
 * Created by Administrator on 2016/11/15.
 */
/*
* 自定义照相机
* */
public class CameraView implements SurfaceHolder.Callback {
    Camera mCamera;
    OnLoadCameraListener mOnLoadCameraListener;
    public CameraView( OnLoadCameraListener mOnLoadCameraListener){
        this.mOnLoadCameraListener=mOnLoadCameraListener;
    }
    //获取相机摄像头数量
    public int getNumber(Context context) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Toast.makeText(context, "没有摄像头", Toast.LENGTH_SHORT).show();
        }
        if (numberOfCameras == 1) {
            return 0;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK)
                return i;
        }

        return 0;
    }
    //设置焦距的方法
    public void setZoom(int zoom){
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setZoom(zoom);
        mCamera.setParameters(parameters);
    }
    //拿到最大焦距
    public int getMaxZoom(){
        return mCamera.getParameters().getMaxZoom();
    }
    //拍照
    public void takePicture(){
        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                //字节数组转为照片
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                //存照片
                String parent = Environment.getExternalStorageDirectory().getPath();
                File file=new File(parent+File.separator+System.currentTimeMillis()+".jpg");
                try {
                    FileOutputStream outPutString=new FileOutputStream(file);
                    //压缩照片
                    bitmap.compress(Bitmap.CompressFormat.JPEG,90,outPutString);
                     outPutString.close();
                    if (mOnLoadCameraListener!=null) {
                        mOnLoadCameraListener.getBitmap(bitmap);
                    }
                    mCamera.startPreview();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    //刚刚创建
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //打开照相机
        //防止相机被占用
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        mCamera = Camera.open();
        //设置参数
        Camera.Parameters parameters = mCamera.getParameters();
        //照片质量
        parameters.setJpegQuality(80);
        //聚焦 Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE 持续聚焦
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        //设置闪光等模式
        List<String> supportedFlashModes = mCamera.getParameters().getSupportedFlashModes();
        if (null != supportedFlashModes) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        }

        //设置照片格式
        parameters.setPictureFormat(ImageFormat.JPEG);

        //保存参数
        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开启预览
        mCamera.startPreview();
        //人脸识别
        mCamera.startFaceDetection();

    }

    //改变
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //销毁
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera!=null) {
         mCamera.stopPreview();
            mCamera.release();
            mCamera=null;
        }
    }
}
