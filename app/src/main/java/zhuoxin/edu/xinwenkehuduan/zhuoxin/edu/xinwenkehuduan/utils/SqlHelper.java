package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.db.DBInfo;

/**
 * Created by Administrator on 2016/11/10.
 */
/*
* 新闻收藏数据库
* */
public class SqlHelper extends SQLiteOpenHelper {
    //Context 上下文
    //String name 数据库名字
    //SQLiteDatabase.CursorFactory factory 游标工厂
    //int version 数据库版本
    public SqlHelper(Context context) {
        super(context, DBInfo.DB_NAME, null, DBInfo.DB_VERSION);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String sql = "create table %1$s( %2$s text,%3$s text,%4$s text,%5$s text,%6$s Integer,%7$s text,%8$s Integer)";
        String format = String.format(sql, DBInfo.TABLE_NAME, DBInfo._SUMMARY, DBInfo._ICON, DBInfo._STAMP, DBInfo._TITLE, DBInfo._NID, DBInfo._LINK, DBInfo._TYPE);
        db.execSQL(format);



    }

    //版本更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
