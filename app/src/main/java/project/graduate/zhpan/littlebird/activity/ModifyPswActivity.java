package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class ModifyPswActivity extends BaseActivity{

    @BindView(R.id.et_old_psw)
    EditText mEtOldPsw;
    @BindView(R.id.et_new_psw)
    EditText mEtNewPsw;
    @BindView(R.id.et_confirm_psw)
    EditText mEtConfirmPsw;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_psw;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mTvTitle.setText("修改密码");
    }

    @OnClick(R.id.btn_confirm)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                submit();
                break;
        }
    }

    private void submit() {
        String oldPsw = mEtOldPsw.getText().toString().trim();
        if (TextUtils.isEmpty(oldPsw)) {
            Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String newPsw = mEtNewPsw.getText().toString().trim();
        if (TextUtils.isEmpty(newPsw)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String confirmPsw = mEtConfirmPsw.getText().toString().trim();
        if (TextUtils.isEmpty(confirmPsw)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPsw.equals(confirmPsw)) {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        List<UserBean> userBeen = DataSupport.where("email=? and password=?", UserInfoTools.getEmail(this), oldPsw).find(UserBean.class);
        if (userBeen.size() > 0) {
            UserBean userBean = userBeen.get(0);
            userBean.setPassword(newPsw);
            if (userBean.save()) {
                Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                userBean.setPassword("");
                SharedPreferencesUtils.saveUserInfo(this, userBean);
                LoginActivity.start(this, true);
                finish();
            } else {
                Toast.makeText(this, "密码修改失败", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "原密码输入有误", Toast.LENGTH_SHORT).show();
        }


    }
}
