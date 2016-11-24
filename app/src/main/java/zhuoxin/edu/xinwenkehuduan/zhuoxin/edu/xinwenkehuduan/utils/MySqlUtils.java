package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.db.DB;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.FollowInfo;

/**
 * Created by Administrator on 2016/11/23.
 */
/*
* 跟帖数据库
* */
public class MySqlUtils {
    Context mContext;
    MySql mSql;

    public MySqlUtils(Context mContext) {
        this.mContext = mContext;
        mSql = new MySql(mContext);
    }

    public void insert(String title, String uid, String stamp, String ctx) {
        SQLiteDatabase database = mSql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB._TITLE, title);
        values.put(DB._UID, uid);
        values.put(DB._STAMP, stamp);
        values.put(DB._CTX, ctx);
        database.insert(DB.TABLE_NAME, null, values);
    }

    Cursor mQuery;

    public ArrayList<FollowInfo> query() {
        SQLiteDatabase database = mSql.getReadableDatabase();
        ArrayList<FollowInfo> list = new ArrayList<>();
        mQuery = database.query(DB.TABLE_NAME, null, null, null, null, null, null);
        while (mQuery.moveToNext()) {
            String title = mQuery.getString(mQuery.getColumnIndex("title"));
            String uid = mQuery.getString(mQuery.getColumnIndex("uid"));
            String stamp = mQuery.getString(mQuery.getColumnIndex("stamp"));
            String ctx = mQuery.getString(mQuery.getColumnIndex("ctx"));
            list.add(new FollowInfo(title, uid, stamp, ctx));
        }
        mQuery.close();
        return list;
    }
}
