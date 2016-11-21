package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import zhuoxin.edu.xinwenkehuduan.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CameraShowActivity extends AppCompatActivity {
    ImageView mImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camerashow);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Intent intent = this.getIntent();
        Bitmap bitmap = intent.getParcelableExtra("img");
        mImg= (ImageView) findViewById(R.id.img_show);
        mImg.setImageBitmap(bitmap);
    }
}
