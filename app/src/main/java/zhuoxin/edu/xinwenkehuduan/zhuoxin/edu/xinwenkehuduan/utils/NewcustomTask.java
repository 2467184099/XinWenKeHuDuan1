package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.inter.OnLoadNewcustomLlister;

/**
 * Created by Administrator on 2016/10/31.
 */
/*
* 异步
* */
public class NewcustomTask extends AsyncTask<String, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String PATA = params[0];
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(PATA);
            urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                byte[] bytys = new byte[2 * 1024];
                int lenght = 0;
                StringBuffer buffer = new StringBuffer();
                while (((lenght = inputStream.read(bytys)) != -1)) {
                    buffer.append(new String(bytys, 0, lenght));

                }
               // Log.e("====",""+ buffer.toString());
                return buffer.toString();


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (null != s && null != onLoadNewcustomLlister) {
            onLoadNewcustomLlister.OnLoadNewcustomLlister(s);
        }


    }

    OnLoadNewcustomLlister onLoadNewcustomLlister;

    public void setOnLoadNewcustomLlister(OnLoadNewcustomLlister onLoadNewcustomLlister) {
        this.onLoadNewcustomLlister = onLoadNewcustomLlister;
    }
}
