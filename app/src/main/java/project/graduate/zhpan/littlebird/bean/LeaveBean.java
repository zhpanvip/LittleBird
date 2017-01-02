package project.graduate.zhpan.littlebird.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2016/12/24.
 */

public class LeaveBean extends DataSupport {
    private long startTime;
    private long endTime;
    private String reason;
    private boolean isChecked;
    private int checkResult; // 0 未审批 1 审批通过 2 审批拒绝
    private int leaveType;  //  请假类型 1公出 2请假
    private String email;
    private String leavePerson;

    public String getLeavePerson() {
        return leavePerson;
    }

    public void setLeavePerson(String leavePerson) {
        this.leavePerson = leavePerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(int leaveType) {
        this.leaveType = leaveType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(int checkResult) {
        this.checkResult = checkResult;
    }
}
