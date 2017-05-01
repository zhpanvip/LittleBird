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
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;

public class ResetUserActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtEmail;
    private Button mBtnConfirm;
    private LinearLayout mActivityResetUser;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_user;
    }

    @Override
    protected void init() {
        initView();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ResetUserActivity.class));
    }

    private void initView() {
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mActivityResetUser = (LinearLayout) findViewById(R.id.activity_reset_user);

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
        String email = mEtEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入要重置用户的邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        List<UserBean> userBeen = DataSupport.where("email=?", email).find(UserBean.class);
        userBeen.get(0).setPassword("123456");
        if(userBeen.get(0).save()){
            Toast.makeText(this, "重置成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "重置失败", Toast.LENGTH_SHORT).show();
        }

    }
}
