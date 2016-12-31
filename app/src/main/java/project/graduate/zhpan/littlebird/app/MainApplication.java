package project.graduate.zhpan.littlebird.app;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by zhpan on 2016/12/31.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
