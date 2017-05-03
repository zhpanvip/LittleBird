package project.graduate.zhpan.littlebird.utils;

import android.content.Context;
import android.content.SharedPreferences;

import project.graduate.zhpan.littlebird.bean.UserBean;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhpan on 2016/10/11.
 * 缓存配置数据
 */

public class SharedPreferencesUtils {
    private static SharedPreferences sp;

    /**
     * 缓存用户信息
     */
    public static void saveUserInfo(Context context, UserBean userBean ) {
        sp = context.getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email",userBean.getEmail());
        editor.putString("password",userBean.getPassword());
        editor.apply();
    }

    /**
     * 获取用户信息
     */
    public static UserBean getUserInfo(Context context) {
        UserBean userBean=new UserBean();
        sp = context.getSharedPreferences("userInfo", MODE_PRIVATE);
        String username = sp.getString("email", "");
        String password=sp.getString("password","");
        userBean.setEmail(username);
        userBean.setPassword(password);
        return userBean;
    }

    /**
     * 保存用户是否登陆
     * @param context
     * @param isLogin
     */
    public static void saveLoginStatus(Context context,boolean isLogin){
        sp = context.getSharedPreferences("isLogin", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isLogin", isLogin);
        edit.commit();
    }
    /**
     * 是否登陆
     * @param context
     * @return
     */
    public static boolean isLogin(Context context){
        sp = context.getSharedPreferences("isLogin", MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
    /**
     * 保存是否记住密码
     *
     * @param context
     * @param isRemember
     */
    public static void saveIsRememberPsw(Context context, boolean isRemember) {
        sp = context.getSharedPreferences("isRemember", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isRemember", isRemember);
        edit.commit();
    }

    /**
     * 获取配置文件中是否记住密码
     *
     * @param context
     * @return
     */
    public static boolean isRememberPassword(Context context) {
        sp = context.getSharedPreferences("isRemember", MODE_PRIVATE);
        boolean isRemember = sp.getBoolean("isRemember", false);
        return isRemember;
    }

    /**
     * 获取是否自动登陆
     *
     * @param context
     * @return
     */
    public static boolean isAutoLogin(Context context) {
        sp = context.getSharedPreferences("isAutoLogin", MODE_PRIVATE);
        boolean isAutoLogin = sp.getBoolean("isAutoLogin", false);
        return isAutoLogin;
    }

    /**
     * 保存是否自动登陆
     * @param context
     * @param isAutoLogin
     */
    public static void saveIsAutoLogin(Context context, boolean isAutoLogin) {
        sp = context.getSharedPreferences("isAutoLogin", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isAutoLogin", isAutoLogin);
        edit.commit();
    }

    /**
     * 保存是否开启考勤提醒
     * @param context
     * @param isSignRemind
     */
    public static void saveIsSignRemind(Context context,boolean isSignRemind){
        sp = context.getSharedPreferences("isSignRemind", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isSignRemind", isSignRemind);
        edit.commit();
    }
    /**
     * 获取是否开启考勤提醒
     */
    public static boolean isSignRemind(Context context){
        sp = context.getSharedPreferences("isSignRemind", MODE_PRIVATE);
        boolean isSignRemind = sp.getBoolean("isSignRemind", false);
        return isSignRemind;
    }

    /**
     * 保存是否开启任务提醒
     * @param context
     * @param isTaskRemind
     */
    public static void saveIsTaskRemind(Context context,boolean isTaskRemind){
        sp = context.getSharedPreferences("isTaskRemind", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isTaskRemind", isTaskRemind);
        edit.commit();
    }
    /**
     * 获取是否开启任务提醒
     */
    public static boolean isTaskRemind(Context context){
        sp = context.getSharedPreferences("isTaskRemind", MODE_PRIVATE);
        boolean isTaskRemind = sp.getBoolean("isTaskRemind", false);
        return isTaskRemind;
    }
    public static void setFirstRun(Context context){
        sp = context.getSharedPreferences("isFirstRun", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isFirstRun", false);
        edit.commit();
    }

    public static boolean isFistRun(Context context){
        sp=context.getSharedPreferences("isFirstRun",MODE_PRIVATE);
        boolean isFirstRun=sp.getBoolean("isFirstRun",true);
        return isFirstRun;
    }

    //  存储公司经纬度
    public static void saveLocation(Context context,double lat,double lng){
        sp = context.getSharedPreferences("LittleBird", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat("lat", (float) lat);
        edit.putFloat("lng", (float) lng);
        edit.apply();
    }
    //  获取维度
    public static double getLat(Context context){
        sp = context.getSharedPreferences("LittleBird", MODE_PRIVATE);
        float lat = sp.getFloat("lat", 0);
        return lat;
    }
    //  获取经度
    public static double getLng(Context context){
        sp = context.getSharedPreferences("LittleBird", MODE_PRIVATE);
        float lng = sp.getFloat("lng", 0);
        return lng;
    }
}
