package project.graduate.zhpan.littlebird.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.app.InitialData;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.activity_splash)
    RelativeLayout mRelativeLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        initData();
        setAnimation();
        finishActivity();
    }

    private void initData() {
        mToolbar.setVisibility(View.GONE);
        //  初始化数据库
        new Thread() {
            @Override
            public void run() {
                super.run();
                boolean fistRun = SharedPreferencesUtils.isFistRun(SplashActivity.this);
                if (fistRun) {    //  初次运行 初始化数据库数据
                    InitialData.initUser(); //  初始化用户
                    InitialData.initIntegral(); //  初始化积分
                    InitialData.initEncourage();    //  初始化奖励
                    InitialData.initTopic();    //  初始化话题
                    InitialData.initNotice();   //  初始化公告
                }
            }
        }.start();
    }


    //  设置状态栏为透明
    private void setStatusBarTransparent() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    //
    //  设置魅族手机状态栏字体颜色为深色
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    //  设置Splash动画 透明渐变
    private void setAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(3000);
        mRelativeLayout.setAnimation(animation);
    }

    //  splash界面休眠3秒后销毁
    private void finishActivity() {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((@NonNull Long aLong)-> {
                        LoginActivity.start(SplashActivity.this,false);
                        overridePendingTransition(0, android.R.anim.fade_out);
                        finish();
                });
    }

    /**
     * 设置splash界面中按返回键不作反应
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
