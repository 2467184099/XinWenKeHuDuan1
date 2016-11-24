package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.db.DBInfo;
import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity.ChildInfo;

/**
 * Created by Administrator on 2016/11/10.
 */
/*
* 新闻收藏数据库
* */
public class SqlUtils {
    Context mContext;
    SqlHelper mSqlHelper;
    Cursor mCursor;

    public SqlUtils(Context mContext) {
        this.mContext = mContext;
        mSqlHelper = new SqlHelper(mContext);
    }

    // summary, icon, stamp, title, nid, link, type
    //插入数据
    public void insert(String summary, String icon, String stamp, String title, int nid, String link, int type) {
        SQLiteDatabase sqLiteDatabase = mSqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBInfo._SUMMARY, summary);
        values.put(DBInfo._ICON, icon);
        values.put(DBInfo._STAMP, stamp);
        values.put(DBInfo._TITLE, title);
        values.put(DBInfo._NID, nid);
        values.put(DBInfo._LINK, link);
        values.put(DBInfo._TYPE, type);
        sqLiteDatabase.insert(DBInfo.TABLE_NAME, null, values);
    }



    //查询全部数据
    public ArrayList<ChildInfo> query() {
        SQLiteDatabase database = mSqlHelper.getReadableDatabase();
        ArrayList<ChildInfo> data = new ArrayList<>();
        mCursor = database.query(DBInfo.TABLE_NAME, null, null, null, null, null, null);
        while (mCursor.moveToNext()) {
            String summary = mCursor.getString(mCursor.getColumnIndex("summary"));
            String icon = mCursor.getString(mCursor.getColumnIndex("icon"));
            String stamp = mCursor.getString(mCursor.getColumnIndex("stamp"));
            String title = mCursor.getString(mCursor.getColumnIndex("title"));
            int nid = mCursor.getInt(mCursor.getColumnIndex("nid"));
            String link = mCursor.getString(mCursor.getColumnIndex("link"));
            int type = mCursor.getInt(mCursor.getColumnIndex("type"));
            data.add(new ChildInfo(summary, icon, stamp, title, nid, link, type));
        }
        mCursor.close();
        return data;
    }



    //删除数据
    public void delete(int nid) {
        SQLiteDatabase database = mSqlHelper.getWritableDatabase();
        database.delete(DBInfo.TABLE_NAME, "nid" + "=?", new String[]{nid + ""});
    }
}
