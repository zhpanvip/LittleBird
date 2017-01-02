package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.callback.LoginCallBack;
import project.graduate.zhpan.littlebird.constants.CheckConstants;
import project.graduate.zhpan.littlebird.databinding.ActivityLoginActivityBinding;
import project.graduate.zhpan.littlebird.presenter.LoginPresenter;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginCallBack, CompoundButton.OnCheckedChangeListener {
    private String IMEI;
    private LoginPresenter loginPresenter;
    private ActivityLoginActivityBinding mBinding;
    private UserBean userBean;
    private boolean isExit; //  是否是退出登陆后进入登陆界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_activity);
        initData();
        setData();
        setListener();
    }

    private void setData() {
        if(SharedPreferencesUtils.isFistRun(this)){
            mBinding.etUsername.setText("zhpan@littlebird.com");
            mBinding.etUsername.setSelection(mBinding.etUsername.getText().toString().length());
            mBinding.etPassword.setText("123456");
            mBinding.etPassword.setSelection(mBinding.etPassword.getText().toString().length());
        }
        SharedPreferencesUtils.setFirstRun(this);
    }

    private void setListener() {
        mBinding.btnLogin.setOnClickListener(this);
        mBinding.cbRememberPassword.setOnCheckedChangeListener(this);
        mBinding.cbAutoLogin.setOnCheckedChangeListener(this);
    }

    private void initData() {
        userBean = new UserBean();
        Intent intent = getIntent();
        if (intent != null) {
            isExit = intent.getBooleanExtra("isExit", false);
        }
        loginPresenter = new LoginPresenter(this);
        //  获取手机IMEI
        IMEI = loginPresenter.getIMEI();
        userBean.setImei(IMEI);
        //  获取是否记住密码
        boolean rememberPassword = SharedPreferencesUtils.isRememberPassword(this);
        //  获取是否自动登陆
        boolean autoLogin = UserInfoTools.isAutoLogin(this);
        if (rememberPassword) {
            mBinding.cbRememberPassword.setChecked(true);
            mBinding.etUsername.setText(UserInfoTools.getEmail(this));
            mBinding.etPassword.setText(UserInfoTools.getPassWord(this));
        } else {
            mBinding.etUsername.setText(UserInfoTools.getEmail(this));
            mBinding.etUsername.setSelection(UserInfoTools.getEmail(this).length());
            mBinding.cbRememberPassword.setChecked(false);
        }
        if (autoLogin) {
            mBinding.cbAutoLogin.setChecked(true);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    SystemClock.sleep(1000);
                    if (!isExit) {
                        login();
                    }

                }
            }.start();
        } else {
            mBinding.cbAutoLogin.setChecked(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        loginPresenter.requestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 登陆
     */
    private void login() {
        userBean.setEmail(mBinding.etUsername.getText().toString());
        userBean.setPassword(mBinding.etPassword.getText().toString());
        int isLegal = loginPresenter.checkUserInfo(userBean);
        switch (isLegal) {
            case CheckConstants.NUll_USERNAME:
                Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                break;
            case CheckConstants.NULL_PASSWORD:
                Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                break;
            case CheckConstants.PASSWORD_NOT_ENOUGH:
                Toast.makeText(LoginActivity.this, "密码长度不能小于6位", Toast.LENGTH_SHORT).show();
                break;
            case CheckConstants.LEGAL:
                //loginPresenter.login(userBean, this);
                loginPresenter.localLogin(userBean,this);
                saveConfigInfo();
                break;
        }
    }

    //  保存登陆状态的配置信息
    private void saveConfigInfo() {
        if (mBinding.cbRememberPassword.isChecked()) {
            //  保存记住密码选中状态
            SharedPreferencesUtils.saveIsRememberPsw(this, true);
            //  保存用户名和密码
            userBean.setPassword(mBinding.etPassword.getText().toString());
            userBean.setEmail(mBinding.etUsername.getText().toString());
            SharedPreferencesUtils.saveUserInfo(this, userBean);
        } else {
            //  保存记住密码checkbox选中状态
            SharedPreferencesUtils.saveIsRememberPsw(this, false);
            SharedPreferencesUtils.saveIsAutoLogin(this, false);
            userBean.setPassword("");
            SharedPreferencesUtils.saveUserInfo(this, userBean);
        }
        if (mBinding.cbAutoLogin.isChecked()) {
            //  保存自动登录选中状态
            SharedPreferencesUtils.saveIsAutoLogin(this, true);
            SharedPreferencesUtils.saveIsRememberPsw(this, true);
        } else {
            SharedPreferencesUtils.saveIsAutoLogin(this, false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:    //  登陆按钮点击事件
                login();
                break;
        }
    }

    /**
     * checkbox选中状态改变的监听时间
     *
     * @param compoundButton
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.cb_remember_password:
                if (isChecked) {
                    mBinding.cbRememberPassword.setChecked(true);
                } else {
                    mBinding.cbAutoLogin.setChecked(false);
                }
                break;
            case R.id.cb_auto_login:
                if (isChecked) {
                    mBinding.cbRememberPassword.setChecked(true);
                } else {
                    mBinding.cbAutoLogin.setChecked(false);
                }
                break;
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "登陆失败,请检查网络是否畅通", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordError() {
        Toast.makeText(this, "登陆失败，用户名或密码不正确。", Toast.LENGTH_SHORT).show();
    }

    public static void start(Context context, boolean isExit) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("isExit", isExit);
        context.startActivity(intent);
    }
}
