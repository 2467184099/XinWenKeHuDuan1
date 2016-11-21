package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18.
 */

public class CgroupInfo {
    String message;
    int status;
    ArrayList<ChildCInfo> data;

    public CgroupInfo(String message, int status, ArrayList<ChildCInfo> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<ChildCInfo> getData() {
        return data;
    }

    public void setData(ArrayList<ChildCInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CgroupInfo{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
