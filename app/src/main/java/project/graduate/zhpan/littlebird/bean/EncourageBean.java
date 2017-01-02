package project.graduate.zhpan.littlebird.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2017/1/2.
 */

public class EncourageBean extends DataSupport {
    private String date;
    private int integral;
    private String howGet;
    private String email;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getHowGet() {
        return howGet;
    }

    public void setHowGet(String howGet) {
        this.howGet = howGet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
