package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.callback.LoginCallBack;
import project.graduate.zhpan.littlebird.constants.CheckConstants;
import project.graduate.zhpan.littlebird.presenter.LoginPresenter;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginCallBack, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.cb_remember_password)
    CheckBox mCbRememberPassword;
    @BindView(R.id.cb_auto_login)
    CheckBox mCbAutoLogin;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private LoginPresenter loginPresenter;
    private UserBean userBean;
    private boolean isExit; //  是否是退出登陆后进入登陆界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        ButterKnife.bind(this);
        initData();
        setData();
        setListener();
    }

    private void setData() {
        if (SharedPreferencesUtils.isFistRun(this)) {
            mEtUsername.setText("admin@littlebird.com");
            mEtUsername.setSelection(mEtUsername.getText().toString().length());
            mEtPassword.setText("123456");
            mEtPassword.setSelection(mEtPassword.getText().toString().length());
        }
        SharedPreferencesUtils.setFirstRun(this);
    }

    private void setListener() {
        mBtnLogin.setOnClickListener(this);
        mCbRememberPassword.setOnCheckedChangeListener(this);
        mCbAutoLogin.setOnCheckedChangeListener(this);
    }

    private void initData() {
        userBean = new UserBean();
        Intent intent = getIntent();
        if (intent != null) {
            isExit = intent.getBooleanExtra("isExit", false);
        }
        loginPresenter = new LoginPresenter(this);
        //  获取是否记住密码
        boolean rememberPassword = SharedPreferencesUtils.isRememberPassword(this);
        //  获取是否自动登陆
        boolean autoLogin = UserInfoTools.isAutoLogin(this);
        mCbRememberPassword.setChecked(rememberPassword);
        mCbAutoLogin.setChecked(autoLogin);

        if (rememberPassword) {
            mEtUsername.setText(UserInfoTools.getEmail(this));
            mEtPassword.setText(UserInfoTools.getPassWord(this));
        } else {
            mEtUsername.setText(UserInfoTools.getEmail(this));
            mEtUsername.setSelection(UserInfoTools.getEmail(this).length());
        }
        if (autoLogin) {
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
        userBean.setEmail(mEtUsername.getText().toString());
        userBean.setPassword(mEtPassword.getText().toString());
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
                loginPresenter.localLogin(userBean, this);
                saveConfigInfo();
                break;
        }
    }

    //  保存登陆状态的配置信息
    private void saveConfigInfo() {
        if (mCbRememberPassword.isChecked()) {
            //  保存记住密码选中状态
            SharedPreferencesUtils.saveIsRememberPsw(this, true);
            //  保存用户名和密码
            userBean.setPassword(mEtPassword.getText().toString());
            userBean.setEmail(mEtUsername.getText().toString());
            SharedPreferencesUtils.saveUserInfo(this, userBean);
        } else {
            //  保存记住密码checkbox选中状态
            SharedPreferencesUtils.saveIsRememberPsw(this, false);
            SharedPreferencesUtils.saveIsAutoLogin(this, false);
            userBean.setPassword("");
            SharedPreferencesUtils.saveUserInfo(this, userBean);
        }
        if (mCbAutoLogin.isChecked()) {
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
                    mCbRememberPassword.setChecked(true);
                } else {
                    mCbAutoLogin.setChecked(false);
                }
                break;
            case R.id.cb_auto_login:
                if (isChecked) {
                    mCbRememberPassword.setChecked(true);
                } else {
                    mCbAutoLogin.setChecked(false);
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

    @Override
    public void onIMEIError() {
        Toast.makeText(this, "请在自己手机上登陆", Toast.LENGTH_SHORT).show();
    }

    public static void start(Context context, boolean isExit) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("isExit", isExit);
        context.startActivity(intent);
    }
}
