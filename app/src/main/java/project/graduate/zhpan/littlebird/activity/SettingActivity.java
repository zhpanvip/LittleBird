package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.greenrobot.eventbus.EventBus;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.databinding.ActivitySettingBinding;
import project.graduate.zhpan.littlebird.presenter.SettingPresenter;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class SettingActivity extends BaseActivity  implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private Button mBtnExit;
    private RelativeLayout mRlModifyPsw;
    private RelativeLayout mRlAboutUs;
    private ToggleButton mTbRememberPsw;
    private ToggleButton mTbRemind;
    private ToggleButton mTbSignRemind;
    private TextView mTvAccount;
    private TextView mTvVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        mBtnExit= (Button) findViewById(R.id.btn_exit);
        mRlModifyPsw= (RelativeLayout) findViewById(R.id.rl_modify_psw);
        mRlAboutUs= (RelativeLayout) findViewById(R.id.rl_about_us);
        mTbRememberPsw= (ToggleButton) findViewById(R.id.toggle_button_remember_psw);
        mTbRemind= (ToggleButton) findViewById(R.id.toggle_button_remind);
        mTbSignRemind= (ToggleButton) findViewById(R.id.toggle_button_sign_remind);
        mTvAccount= (TextView) findViewById(R.id.tv_account);
        mTvVersion= (TextView) findViewById(R.id.tv_version);
    }

    private void setListener() {
        mBtnExit.setOnClickListener(this);
        mRlModifyPsw.setOnClickListener(this);
        mRlAboutUs.setOnClickListener(this);
        mTbRememberPsw.setOnCheckedChangeListener(this);
        mTbRemind.setOnCheckedChangeListener(this);
        mTbSignRemind.setOnCheckedChangeListener(this);
    }

    private void initData() {
        mTvTitle.setText("设置");

        //  设置当前登陆账号
        mTvAccount.setText(UserInfoTools.getUserName(this));
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
        if(appVersion!=null){
            mTvVersion.setText(appVersion);
        }else {
            Toast.makeText(this, "获取版本号异常", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_exit: //  退出按钮点击事件
                //  登陆状态更改为false
                SharedPreferencesUtils.saveLoginStatus(this,false);
                LoginActivity.start(SettingActivity.this,true);
                EventBus.getDefault().post(new Exit());
                finish();
                break;
            case R.id.rl_modify_psw:
                startActivity(new Intent(this,ModifyPswActivity.class));
                break;
            case R.id.rl_about_us:
                startActivity(new Intent(this,AboutUsActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()){
            case R.id.toggle_button_remember_psw:
                if(isChecked){
                    //  保存自动登录选中状态
                    SharedPreferencesUtils.saveIsAutoLogin(SettingActivity.this,true);
                    SharedPreferencesUtils.saveIsRememberPsw(SettingActivity.this,true);
                }else {
                    //  保存自动登录选中状态
                    SharedPreferencesUtils.saveIsAutoLogin(SettingActivity.this,false);
                }
                break;
            case R.id.toggle_button_remind: //  任务提醒
                SharedPreferencesUtils.saveIsTaskRemind(this,isChecked);
                break;
            case R.id.toggle_button_sign_remind:    //  考勤提醒
                SharedPreferencesUtils.saveIsSignRemind(this,isChecked);

                break;
        }
    }

    public class Exit{
    }
}
