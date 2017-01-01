package project.graduate.zhpan.littlebird.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2016/12/31.
 */

public class SignBean extends DataSupport {
    private long id;
    @Column(unique = true, defaultValue = "unknown")
    private String signDate; // 签到日期
    private String signAddress;
    private String workTime;
    private long signTime;
    private long signOutTime;
    private int signState;  //  签到状态

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public long getSignTime() {
        return signTime;
    }

    public void setSignTime(long signTime) {
        this.signTime = signTime;
    }

    public long getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(long signOutTime) {
        this.signOutTime = signOutTime;
    }

    public int getSignState() {
        return signState;
    }

    public void setSignState(int signState) {
        this.signState = signState;
    }
}
