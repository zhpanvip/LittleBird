package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import project.graduate.zhpan.littlebird.R;

public class ModifyPswActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtOldPsw;
    private EditText mEtNewPsw;
    private EditText mEtConfirmPsw;
    private Button mBtnComfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        initView();
        setData();
    }

    private void setData() {
        mTvTitle.setText("修改密码");
    }

    private void initView() {
        mEtOldPsw = (EditText) findViewById(R.id.et_old_psw);
        mEtNewPsw = (EditText) findViewById(R.id.et_new_psw);
        mEtConfirmPsw = (EditText) findViewById(R.id.et_confirm_psw);
        mBtnComfirm = (Button) findViewById(R.id.btn_confirm);
        mBtnComfirm.setOnClickListener(this);
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
        String oldPsw = mEtOldPsw.getText().toString().trim();
        if (TextUtils.isEmpty(oldPsw)) {
            Toast.makeText(this, "请输入原始密码", Toast.LENGTH_SHORT).show();
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

        // TODO validate success, do something


    }
}
