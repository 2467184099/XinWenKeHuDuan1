package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity;

/**
 * Created by Administrator on 2016/11/22.
 */

public class FollowInfo {
    String title;
    String uid;
    String stamp;
    String ctx;

    public FollowInfo(String title, String uid, String stamp, String ctx) {
        this.title = title;
        this.uid = uid;
        this.stamp = stamp;
        this.ctx = ctx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx;
    }

    @Override
    public String toString() {
        return "FollowInfo{" +
                "title='" + title + '\'' +
                ", uid='" + uid + '\'' +
                ", stamp='" + stamp + '\'' +
                ", ctx='" + ctx + '\'' +
                '}';
    }
}
