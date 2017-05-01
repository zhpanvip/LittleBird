package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;

public class DeleteUserActivity extends BaseActivity{

    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_delete_user;
    }

    @Override
    protected void init() {
        initView();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, DeleteUserActivity.class));
    }

    private void initView() {
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
    }

    private void submit() {
        String email = mEtEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入要删除的用户邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        List<UserBean> userBeen = DataSupport.where("email=?", email).find(UserBean.class);
        if (userBeen.size() > 0) {
            DataSupport.deleteAll(UserBean.class, "email=?", email);
            List<UserBean> userBeen2 = DataSupport.where("email=?", email).find(UserBean.class);
            if (userBeen2.size() == 0) {
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "该用户不存在", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                submit();
                break;
        }
    }
}
