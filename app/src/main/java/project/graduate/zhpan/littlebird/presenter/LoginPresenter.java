package project.graduate.zhpan.littlebird.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import project.graduate.zhpan.littlebird.activity.LoginActivity;
import project.graduate.zhpan.littlebird.activity.MainActivity;
import project.graduate.zhpan.littlebird.bean.LoginBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.constants.CheckConstants;
import project.graduate.zhpan.littlebird.constants.UrlConstant;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;

/**
 * Created by zhpan on 2016/12/11.
 */

public class LoginPresenter {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private Activity activity;

    public LoginPresenter(Activity activity) {
        this.activity = activity;
    }

    /**
     * 检查用户名密码是否输入正确
     */
    public int checkUserInfo(UserBean user) {
        if (TextUtils.isEmpty(user.getEmail())) {
            return CheckConstants.NUll_USERNAME;    //  用户名为空
        } else if (TextUtils.isEmpty(user.getPassword())) {   //  密码为空
            return CheckConstants.NULL_PASSWORD;
        } else {
            if (user.getPassword().length() < 6) {  //  密码小于6位
                return CheckConstants.PASSWORD_NOT_ENOUGH;
            } else {
                return CheckConstants.LEGAL;
            }
        }
    }

    /**
     *  登陆请求
     * @param userBean
     * @param loginActivity
     */
    public void login(final UserBean userBean, final LoginActivity loginActivity) {
        OkHttpUtils.post()
                .url(UrlConstant.loginUrl)
                .addParams("username", userBean.getEmail())
                .addParams("password", userBean.getPassword())
                .build()
                .execute(new Callback<LoginBean>() {
                    @Override
                    public LoginBean parseNetworkResponse(Response response, int id) throws Exception {
                        String json = response.body().string();
                        Gson gson = new Gson();
                        LoginBean loginData = gson.fromJson(json, LoginBean.class);
                        return loginData;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        loginActivity.onError();
                    }

                    @Override
                    public void onResponse(LoginBean response, int id) {
                        int result = response.getResult();

                        if (result == 1) {
                            SharedPreferencesUtils.saveLoginStatus(activity,true);
                            String userId = response.getData().getId();
                            Toast.makeText(loginActivity, "uid=" + userId, Toast.LENGTH_SHORT).show();
                            String password = userBean.getPassword();
                            Intent intent = new Intent(loginActivity, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("uid", userId);
                            bundle.putString("password", password);
                            intent.putExtras(bundle);
                            loginActivity.finish();
                            loginActivity.startActivity(intent);
                        } else {
                            loginActivity.onPasswordError();
                        }
                    }
                });
    }

    public void localLogin(final UserBean userBean, final LoginActivity loginActivity){
        List<UserBean> userBeens = DataSupport.where("email=? and password=?",userBean.getEmail(), userBean.getPassword()).find(UserBean.class);
        if(userBeens.size()>0){
            SharedPreferencesUtils.saveLoginStatus(activity,true);
            String email = userBeens.get(0).getEmail();
            String password = userBean.getPassword();
            Intent intent = new Intent(loginActivity, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("password", password);
            intent.putExtras(bundle);
            loginActivity.finish();
            loginActivity.startActivity(intent);
        }else {
            loginActivity.onPasswordError();
        }

    }

    /**
     * 获取手机IMEI号
     */
    public String getIMEI() {
        String imei = "";
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // 判断是否需要解释获取权限原因
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_PHONE_STATE)) {
                // 需要向用户解释
                // 此处可以弹窗或用其他方式向用户解释需要该权限的原因
            } else {
                // 无需解释，直接请求权限
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS 是自定义的常量，在回调方法中可以获取到

            }
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
        }
        return imei;
    }

    /**
     * 运行时权限请求权限成功后
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void requestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                //如果请求被取消，那么 result 数组将为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
                    // 已经获取对应权限
                } else {
                    // 未获取到授权，取消需要该权限的方法
                }
                return;
            }
            // 检查其他权限......
        }
    }
}
