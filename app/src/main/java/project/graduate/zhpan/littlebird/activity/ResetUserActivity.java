package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;

public class ResetUserActivity extends BaseActivity{

    @BindView(R.id.et_email)
    EditText mEtEmail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_user;
    }

    @Override
    protected void init() {
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ResetUserActivity.class));
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
        if (userBeen.get(0).save()) {
            Toast.makeText(this, "重置成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "重置失败", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        submit();
    }
}
