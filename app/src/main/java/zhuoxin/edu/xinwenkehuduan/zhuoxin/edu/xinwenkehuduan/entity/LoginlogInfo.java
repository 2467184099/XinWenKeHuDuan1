package zhuoxin.edu.xinwenkehuduan.zhuoxin.edu.xinwenkehuduan.entity;

/**
 * Created by Administrator on 2016/11/21.
 */

public class LoginlogInfo {
    String time;
    String address;
    int device;

    public LoginlogInfo(String time, String address, int device) {
        this.time = time;
        this.address = address;
        this.device = device;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "LoginlogInfo{" +
                "time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", device=" + device +
                '}';
    }
}
