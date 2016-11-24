package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.FragmentActivity;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.UserActivity;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;

/**
 * Created by Administrator on 2016/10/28.
 */
/*
* 登陆界面
*
* */
public class LoginFragment extends Fragment implements View.OnClickListener, OnLoadRegisterListener {
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    RegisterFragment mRegisterFragment;
    ForgetFragment mForgetFragment;
    EditText mName;
    EditText mPassword;
    RequestQueue mRequestQueue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loginfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtn1 = (Button) view.findViewById(R.id.btn1);
        mBtn2 = (Button) view.findViewById(R.id.btn2);
        mBtn3 = (Button) view.findViewById(R.id.btn3);
        mName = (EditText) view.findViewById(R.id.edit_loginname);
        mPassword = (EditText) view.findViewById(R.id.edit_loginpasword);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mRegisterFragment = new RegisterFragment();
        mForgetFragment = new ForgetFragment();
        mRequestQueue = Volley.newRequestQueue(getActivity());
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                /*FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.lyt_center, mRegisterFragment);
                fragmentTransaction.commit();*/
                ((FragmentActivity) getActivity()).register();
                getActivity().finish();
                break;
            case R.id.btn2:
               /* FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.lyt_center, mForgetFragment);
                fragmentTransaction1.commit();*/
                ((FragmentActivity) getActivity()).forget();
                getActivity().finish();
                break;
            case R.id.btn3:
                Log.e("----", "resule=" + mResult);
                String name = mName.getText().toString();
                String password = mPassword.getText().toString();
                HttpUtils.connectionPOST(HttpInfo.BASE_URL + HttpInfo.LOGIN_URL, name, password, this, mRequestQueue);
                break;
        }
    }

    static int mStatus;
    int mResult ;
    String mExplain;
    @Override
    public void getRegister(String message) {
        Log.e("----", "message=" + message);
        JSONObject jsonObject = null;
        try {
            SharedPreferences shar = getActivity().getSharedPreferences("count", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shar.edit();
            jsonObject = new JSONObject(message);
            String message1 = jsonObject.getString("message");
            mStatus = jsonObject.getInt("status");
            edit.putInt("status",mStatus);
            int status = shar.getInt("status", 0);
            Log.e("====","status==="+status);
            if (mStatus==0){
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "用户名或密码错误！请重新输入", Toast.LENGTH_SHORT).show();
                mName.setText("");
                mPassword.setText("");
            }
            Log.e("----", "mStatus============" + mStatus);
            JSONObject data = jsonObject.getJSONObject("data");
            mResult = data.getInt("result");
            Log.e("----", "mResult=============" + mResult);
            String token = data.getString("token");
            mExplain = data.getString("explain");
            Log.e("----", "mExplain============" + mExplain);
            edit.putString("token", token);
            edit.putInt("result",mResult);
            edit.commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
