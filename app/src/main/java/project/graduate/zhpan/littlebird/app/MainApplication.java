package project.graduate.zhpan.littlebird.app;

import android.app.Application;

import org.litepal.LitePal;

import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;

/**
 * Created by zhpan on 2016/12/31.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

        boolean fistRun = SharedPreferencesUtils.isFistRun(this);
        if(fistRun){    //  初次运行 初始化数据库数据
            InitialData.initUser(); //  初始化用户
            InitialData.initIntegral(); //  初始化积分
            InitialData.initEncourage();    //  初始化奖励
            InitialData.initTopic();    //  初始化话题
        }
    }
}
