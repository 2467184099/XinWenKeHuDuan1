package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import zhuoxin.edu.xinwenkehuduan.R;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.HttpInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.main.FragmentActivity;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils.HttpUtils;

/**
 * Created by Administrator on 2016/11/2.
 */
/*
* 注册界面
* */
public class RegisterFragment extends Fragment implements View.OnClickListener, OnLoadRegisterListener {
    EditText mEmail;
    EditText mName;
    EditText mPassword;
    Button mBtn;
    CheckBox mChb;
    RequestQueue mRequestQueue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmail = (EditText) view.findViewById(R.id.edit_email);
        mName = (EditText) view.findViewById(R.id.edit_name);
        mPassword = (EditText) view.findViewById(R.id.edit_password);
        mChb = (CheckBox) view.findViewById(R.id.chb_register);
        mBtn = (Button) view.findViewById(R.id.btn_register);
        mBtn.setOnClickListener(this);
        //volley获取消息列队
        mRequestQueue = Volley.newRequestQueue(getActivity());


    }

    @Override
    public void onClick(View v) {
        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        boolean a = Pattern.matches("\\w{5,20}@\\w{2,10}.[a-zA-Z]{2,6}", email);
        boolean b = Pattern.matches("[a-zA-Z]\\w{5,15}", name);
        boolean c = Pattern.matches("\\w{6,20}", password);
        if (a) {
            if (b) {
                if (c) {
                    if (mChb.isChecked()) {
                        HttpUtils.connectionGET(HttpInfo.BASE_URL + HttpInfo.REGISTER_URL + "ver=1&uid=" + name + "&email=" + email + "&pwd=" + password, this, mRequestQueue);
                    } else {
                        Toast.makeText(getActivity(), "是否接受协议", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "密码输入有误，请重新输入！", Toast.LENGTH_LONG).show();

                }
            } else {
                Toast.makeText(getActivity(), "用户名输入有误，请重新输入", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), "邮箱格式错误！", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void getRegister(String message) {
        Log.e("------", message.toString());
        try {
            JSONObject jsonObject = new JSONObject(message);
            String message1 = jsonObject.getString("message");
            int status = jsonObject.getInt("status");

            JSONObject data = jsonObject.getJSONObject("data");
            int result = data.getInt("result");
            String token = data.getString("token");
            String explain = data.getString("explain");
            if (status==0){
                Toast.makeText(getContext(),explain,Toast.LENGTH_SHORT).show();
                if (result==0){
                    ((FragmentActivity) getActivity()).login();
                    getActivity().finish();
                }
            }else {
               Toast.makeText(getContext(),"邮箱格式错误",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
