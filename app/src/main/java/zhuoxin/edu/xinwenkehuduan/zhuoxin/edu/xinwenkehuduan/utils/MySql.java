package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.db.DB;

/**
 * Created by Administrator on 2016/11/23.
 */
/*
* 跟帖数据库
* */
public class MySql extends SQLiteOpenHelper {
    public MySql(Context context) {
        super(context, DB.DB_NAME, null, DB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table %1$s( %2$s text,%3$s text,%4$s text,%5$s text)";
        String format = String.format(sql, DB.TABLE_NAME, DB._TITLE, DB._UID, DB._STAMP, DB._CTX);
        db.execSQL(format);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
