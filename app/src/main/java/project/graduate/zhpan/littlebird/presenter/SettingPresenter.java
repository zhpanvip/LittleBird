package project.graduate.zhpan.littlebird.presenter;

import android.content.Context;

/**
 * Created by zhpan on 2016/12/13.
 */

public class SettingPresenter {

    //  获取版本号
    public static String getAppVersion(Context context) {
        try {
            String pkName = context.getPackageName();  //  获取包名
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return  versionName + "." + versionCode;
        } catch (Exception e) {
        }
        return null;
    }
}
