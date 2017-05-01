package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import java.util.List;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.GradeTextWatcher;

public class GradeActivity extends BaseActivity implements View.OnClickListener {

    private View mViewComplete;
    private TextView mTvTaskName;
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
    private TaskBean taskBean;
    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_grade;
    }

    @Override
    protected void init() {
        initView();
        setData();
        setListener();
    }

    private void setListener() {
        mEtGrade.addTextChangedListener(new GradeTextWatcher(mEtGrade));
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                taskBean = (TaskBean) bundle.getSerializable("taskBean");
            }
        }
        List<UserBean> userBeen = DataSupport.where("email=?", taskBean.getUserEmail()).find(UserBean.class);
        mTvTitle.setText(userBeen.get(0).getRealName() + "的任务");

        mTvTaskName.setText("任务名称：" + taskBean.getTaskName());
        mTvCheckPerson.setText("审核人：" + taskBean.getCheckPerson());
        mTvCompleteState.setText("已提交");
        mTvQualityState.setText("已提交");
        long userTime = taskBean.getCommitTime() - taskBean.getRealStartTime();
        mTvTimeCount.setText(DateUtils.timeFormat(userTime / 1000));
    }

    private void initView() {
        mViewComplete = findViewById(R.id.view_complete);
        mTvTaskName = (TextView) findViewById(R.id.tv_task_name);
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
        List<TaskBean> taskBeen = DataSupport
                .where("taskName=?", taskBean.getTaskName())
                .find(TaskBean.class);

        taskBeen.get(0).setGrade(Float.parseFloat(grade));
        taskBeen.get(0).setCheck(true);
        taskBeen.get(0).save();
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new CheckSuccess());
        finish();

    }
    public class CheckSuccess{}
}
