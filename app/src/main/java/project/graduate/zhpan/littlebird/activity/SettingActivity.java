package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.presenter.SettingPresenter;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.tv_account)
    TextView mTvAccount;
    @BindView(R.id.toggle_button_remember_psw)
    ToggleButton mTbRememberPsw;
    @BindView(R.id.toggle_button_remind)
    ToggleButton mTbRemind;
    @BindView(R.id.toggle_button_sign_remind)
    ToggleButton mTbSignRemind;
    @BindView(R.id.tv_version)
    TextView mTvVersion;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        initData();
    }

    private void initData() {
        mTvTitle.setText("设置");
        mTbRememberPsw.setOnCheckedChangeListener(this);

        //  设置当前登陆账号
        mTvAccount.setText(UserInfoTools.getEmail(this));
        //  获取是否自动登陆
        boolean isAutoLogin = SharedPreferencesUtils.isAutoLogin(this);
        mTbRememberPsw.setChecked(isAutoLogin);
        //  是否开启任务提醒
        boolean taskRemind = SharedPreferencesUtils.isTaskRemind(this);
        mTbRemind.setChecked(taskRemind);
        //  是否开启考勤提醒
        boolean signRemind = SharedPreferencesUtils.isSignRemind(this);
        mTbSignRemind.setChecked(signRemind);
        //  获取版本号
        String appVersion = SettingPresenter.getAppVersion(this);
        if (appVersion != null) {
            mTvVersion.setText(appVersion);
        } else {
            Toast.makeText(this, "获取版本号异常", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.toggle_button_remember_psw:
                if (isChecked) {
                    //  保存自动登录选中状态
                    SharedPreferencesUtils.saveIsAutoLogin(SettingActivity.this, true);
                    SharedPreferencesUtils.saveIsRememberPsw(SettingActivity.this, true);
                } else {
                    //  保存自动登录选中状态
                    SharedPreferencesUtils.saveIsAutoLogin(SettingActivity.this, false);
                }
                break;
            case R.id.toggle_button_remind: //  任务提醒
                SharedPreferencesUtils.saveIsTaskRemind(this, isChecked);
                break;
            case R.id.toggle_button_sign_remind:    //  考勤提醒
                SharedPreferencesUtils.saveIsSignRemind(this, isChecked);

                break;
        }
    }

    @OnClick({R.id.rl_modify_psw, R.id.iv_about_us, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_modify_psw:
                startActivity(new Intent(this, ModifyPswActivity.class));
                break;
            case R.id.iv_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.btn_exit:
                //  登陆状态更改为false
                SharedPreferencesUtils.saveLoginStatus(this, false);
                LoginActivity.start(SettingActivity.this, true);
                EventBus.getDefault().post(new Exit());
                finish();
                break;
        }
    }

    public class Exit {
    }
}
