package project.graduate.zhpan.littlebird.callback;

/**
 * Created by zhpan on 2016/12/11.
 */

public interface LoginCallBack {
    /**
     * 登陆失败
     */
    void onError();

    /**
     * 密码错误
     */
    void onPasswordError();
}
