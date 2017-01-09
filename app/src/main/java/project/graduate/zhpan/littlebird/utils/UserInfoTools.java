package project.graduate.zhpan.littlebird.utils;

import android.content.Context;

import org.litepal.crud.DataSupport;

import java.util.List;

import project.graduate.zhpan.littlebird.bean.UserBean;

/**
 * Created by zhpan on 2016/12/13.
 */

public class UserInfoTools {

    /**
     * 获取用户名
     */
    public static String getEmail(Context context) {
        UserBean userInfo = SharedPreferencesUtils.getUserInfo(context);
        return userInfo.getEmail();
    }

    /**
     * 获取用户名
     */
    public static String getPassWord(Context context) {
        UserBean userInfo = SharedPreferencesUtils.getUserInfo(context);
        return userInfo.getPassword();
    }

    public static String getRealName(Context context){
        List<UserBean> userBeen = DataSupport.where("email=?", getEmail(context)).find(UserBean.class);

        return userBeen.get(0).getRealName();
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

    public static boolean isAdmin(Context context){
        List<UserBean> userBeen = DataSupport.where("email=?", getEmail(context)).find(UserBean.class);
        if(userBeen.size()>0){
            boolean admin = userBeen.get(0).isAdmin();
            return admin;
        }
        return false;
    }

}
