package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadRegisterListener;

/**
 * Created by Administrator on 2016/11/3.
 */

public class HttpUtils {
    //get方法
    public static void connectionGET(String uri, final OnLoadRegisterListener onLoadRegisterListener, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener.getRegister(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "" + error.getMessage());
            }
        });
        requestQueue.add(request);

    }
   //post方法
    public static void connectionPOST(String uri, final String name, final String password, final OnLoadRegisterListener onLoadRegisterListener, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener.getRegister(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("-----", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("ver", "1");
                map.put("uid", name);
                map.put("pwd", password);
                map.put("device", "0");
                return map;
            }
        };
        requestQueue.add(request);
    }
}
