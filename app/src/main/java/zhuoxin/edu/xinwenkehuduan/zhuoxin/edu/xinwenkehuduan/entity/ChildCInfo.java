package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity;

/**
 * Created by Administrator on 2016/11/18.
 */

public class ChildCInfo {
    String uid;
    String content;
    String stamp;
    int cid;
    String portrait;

    public ChildCInfo() {
    }

    public ChildCInfo(String uid, String content, String stamp, int cid, String portrait) {
        this.uid = uid;
        this.content = content;
        this.stamp = stamp;
        this.cid = cid;
        this.portrait = portrait;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "ChildCInfo{" +
                "uid='" + uid + '\'' +
                ", content='" + content + '\'' +
                ", stamp='" + stamp + '\'' +
                ", cid=" + cid +
                ", portrait='" + portrait + '\'' +
                '}';
    }
}
