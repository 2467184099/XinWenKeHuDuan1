package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import zhuoxin.edu.xinwenkehuduan.R;

/**
 * Created by Administrator on 2016/10/31.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mBack;
    ImageView mImg;
    Button mBtn;
    TextView mText1;
    TextView mText2;
    TextView mText3;
    ListView mLst;
    String mName;
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
        mBack = (ImageView) findViewById(R.id.img_useractivity_back);
        mBtn = (Button) findViewById(R.id.btn_useractivity);
        mImg = (ImageView) findViewById(R.id.img_useractivit);
        mText1 = (TextView) findViewById(R.id.txt_1);
        mText2 = (TextView) findViewById(R.id.txt_2);
        mText3 = (TextView) findViewById(R.id.txt_3);
        mLst= (ListView) findViewById(R.id.lst_useractivity);
        mText1.setText(mName);
        mBtn.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_useractivity_back:
                startActivity(new Intent(UserActivity.this, FragmentActivity.class));
                break;
            case R.id.btn_useractivity:
                startActivity(new Intent(UserActivity.this, FragmentActivity.class));
                break;

        }


    }
}
