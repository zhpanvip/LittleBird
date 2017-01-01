package project.graduate.zhpan.littlebird.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2016/10/16.
 */

public class UserBean extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String email;
    private String password;
    @Column(unique = true)
    private long userId;
    private String realName;
    private String Tel;
    private int integral;
    private int rank;
    private String job;
    private String entryTime;
    private String projectGroup;
    private String department;
    private String imei;
    private String personalSign;
    private boolean isAdmin;
    private double averageDay;
    private double averageWeek;
    private double averageMonth;
    private double averageQuarter;
    private double averageYear;

    public double getAverageWeek() {
        return averageWeek;
    }

    public void setAverageWeek(double averageWeek) {
        this.averageWeek = averageWeek;
    }

    public double getAverageMonth() {
        return averageMonth;
    }

    public void setAverageMonth(double averageMonth) {
        this.averageMonth = averageMonth;
    }

    public double getAverageQuarter() {
        return averageQuarter;
    }

    public void setAverageQuarter(double averageQuarter) {
        this.averageQuarter = averageQuarter;
    }

    public double getAverageYear() {
        return averageYear;
    }

    public void setAverageYear(double averageYear) {
        this.averageYear = averageYear;
    }

    public double getAverageDay() {
        return averageDay;
    }

    public void setAverageDay(double averageDay) {
        this.averageDay = averageDay;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPersonalSign() {
        return personalSign;
    }

    public void setPersonalSign(String personalSign) {
        this.personalSign = personalSign;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getImei() {
        return imei;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
