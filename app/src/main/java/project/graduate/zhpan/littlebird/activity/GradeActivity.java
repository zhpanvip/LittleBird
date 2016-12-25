package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.utils.GradeTextWatcher;

public class GradeActivity extends BaseActivity implements View.OnClickListener {

    private View mViewComplete;
    private TextView mTvCompleteState;
    private View mViewQuality;
    private TextView mTvQualityState;
    private TextView mTvCuntTime;
    private TextView mTvTimeCount;
    private TextView mTvCheckPerson;
    private EditText mEtGrade;
    private EditText mEtJudge;
    private Button mBtnSubmit;
    private LinearLayout mActivityGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        initView();
        setData();
        setListener();
    }

    private void setListener(){
        mEtGrade.addTextChangedListener(new GradeTextWatcher(mEtGrade));
    }
    private void setData() {
        mTvTitle.setText("写代码");
    }

    private void initView() {
        mViewComplete = (View) findViewById(R.id.view_complete);
        mTvCompleteState = (TextView) findViewById(R.id.tv_complete_state);
        mViewQuality = (View) findViewById(R.id.view_quality);
        mTvQualityState = (TextView) findViewById(R.id.tv_quality_state);
        mTvCuntTime = (TextView) findViewById(R.id.tv_cunt_time);
        mTvTimeCount = (TextView) findViewById(R.id.tv_time_count);
        mTvCheckPerson = (TextView) findViewById(R.id.tv_check_person);
        mEtGrade = (EditText) findViewById(R.id.et_grade);
        mEtJudge = (EditText) findViewById(R.id.et_judge);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mActivityGrade = (LinearLayout) findViewById(R.id.activity_grade);

        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submit();
                break;
        }
    }


    private void submit() {
        // validate
        String grade = mEtGrade.getText().toString().trim();
        if (TextUtils.isEmpty(grade)) {
            Toast.makeText(this, "请输入任务评分", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something


    }
}
