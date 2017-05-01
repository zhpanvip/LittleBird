package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtE_mall;
    private EditText mEtRealName;
    private EditText mEtPsw;
    private EditText mEtConfirmPsw;
    private Button mBtnConfirm;
    private LinearLayout mActivityRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        initView();
        initData();
        setData();
    }

    private void setData() {
        mTvTitle.setText("添加用户");
    }

    private void initData() {

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    private void initView() {
        mEtE_mall = (EditText) findViewById(R.id.et_email);
        mEtRealName= (EditText) findViewById(R.id.et_real_name);
        mEtPsw = (EditText) findViewById(R.id.et_psw);
        mEtConfirmPsw = (EditText) findViewById(R.id.et_confirm_psw);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mActivityRegister = (LinearLayout) findViewById(R.id.activity_register);

        mBtnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String email = mEtE_mall.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!checkEmail(email)){
            Toast.makeText(this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
            return;
        }

        String realName = mEtRealName.getText().toString().trim();
        if (TextUtils.isEmpty(realName)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = mEtPsw.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        String confirmPsw = mEtConfirmPsw.getText().toString().trim();
        if (TextUtils.isEmpty(confirmPsw)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(confirmPsw)){
            Toast.makeText(this, "密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6||confirmPsw.length()<6){
            Toast.makeText(this, "密码长度不能小于6位", Toast.LENGTH_SHORT).show();
            return;
        }

        List<UserBean> userBeen = DataSupport.where("email=?", email).find(UserBean.class);
        if(userBeen.size()>0){
            Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show();
            return;
        }

        UserBean userBean=new UserBean();
        userBean.setEmail(email);
        userBean.setRealName(realName);
        userBean.setPassword(password);
        String detailedTime = DateUtils.getDetailedTime(System.currentTimeMillis());
        userBean.setUserId(Long.parseLong(detailedTime));
        if(userBean.save()){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
}
