package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */

public class GroupInfo {
    String message;
     int status;
    ArrayList<ChildInfo> data;

    public GroupInfo(String message, int status, ArrayList<ChildInfo> data) {
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

    public ArrayList<ChildInfo> getData() {
        return data;
    }

    public void setData(ArrayList<ChildInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
