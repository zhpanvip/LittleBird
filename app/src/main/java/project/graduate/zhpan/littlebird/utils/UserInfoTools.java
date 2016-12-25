package project.graduate.zhpan.littlebird.utils;

import android.content.Context;

import project.graduate.zhpan.littlebird.bean.UserBean;

/**
 * Created by zhpan on 2016/12/13.
 */

public class UserInfoTools {

    /**
     * 获取用户名
     */
    public static String getUserName(Context context) {
        UserBean userInfo = SharedPreferencesUtils.getUserInfo(context);
        return userInfo.getUsername();
    }

    /**
     * 获取用户名
     */
    public static String getPassWord(Context context) {
        UserBean userInfo = SharedPreferencesUtils.getUserInfo(context);
        return userInfo.getPassword();
    }

    public static boolean isAutoLogin(Context context) {
        return SharedPreferencesUtils.isAutoLogin(context);
    }


    /**
     * 是否登陆
     *
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {

        return SharedPreferencesUtils.isLogin(context);
    }

    /**
     * 是否记住密码
     *
     * @param context
     * @return
     */
    public static boolean isRememberPsw(Context context) {
        return SharedPreferencesUtils.isRememberPassword(context);
    }

}
