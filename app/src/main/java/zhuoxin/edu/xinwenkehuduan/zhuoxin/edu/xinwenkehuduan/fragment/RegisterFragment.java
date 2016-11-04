package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.content.Context;
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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/2.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener,OnLoadRegisterListener{
    EditText mEmail;
    EditText mName;
    EditText mPassword;
    Button mBtn;
    RequestQueue mRequestQueue;
    public static final String sPREFC_NAME = "register";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmail= (EditText) view.findViewById(R.id.edit_email);
        mName= (EditText) view.findViewById(R.id.edit_name);
        mPassword= (EditText) view.findViewById(R.id.edit_password);
        mBtn= (Button) view.findViewById(R.id.btn_register);
        mBtn.setOnClickListener(this);
        //volley获取消息列队
         mRequestQueue =  Volley.newRequestQueue(getActivity());


    }

    @Override
    public void onClick(View v) {
        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        HttpUtils.connectionGET(HttpInfo.BASE_URL+HttpInfo.REGISTER_URL+"ver=1&uid="+name+"&email="+email+"&pwd="+password,this,mRequestQueue);

    }

    @Override
    public void getRegister(String message)  {
        Log.e("------",message.toString());
        try {
            JSONObject jsonObject=new JSONObject(message);
            String message1 = jsonObject.getString("message");
            int status = jsonObject.getInt("status");
            JSONObject data = jsonObject.getJSONObject("data");
            int result = data.getInt("result");
            String token = data.getString("token");
            String explain = data.getString("explain");
            SharedPreferences shar=getActivity().getSharedPreferences(sPREFC_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shar.edit();
            edit.putInt("result",result);
            edit.putString("token",token);
            edit.putString("explain",explain);
            edit.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
