package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.LeaveAdapter;
import project.graduate.zhpan.littlebird.bean.LeaveBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class LeaveActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {
    private LeaveAdapter mAdapter;
    private List<LeaveBean> mList;
    private int type;
    private TextView mTvCheckPerson;
    private TextView mTvStartTime;
    private RelativeLayout mRlStartTime;
    private TextView mTvEndTime;
    private RelativeLayout mRlEndTime;
    private TextView mTvLeaveReason;
    private EditText mEtTaskDescribe;
    private LinearLayout mActivityLeave2;
    private TimePickerDialog mDialogAll;
    private TimePickerDialog mDialogYearMonth;
    private TimePickerDialog mDialogYearMonthDay;
    private TimePickerDialog mDialogMonthDayHourMinute;
    private TimePickerDialog mDialogHourMinute;
    private int flag = 0; //  标志哪一个时间选择其
    private long startTimeStamp;
    private long endTimeStamp;
    private LeaveBean leaveBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        initView();
        initData();
        setData();
    }

    private void initData() {
        /**
         * 初始化时间选择器弹窗
         */
        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("请选择时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(DateUtils.date2TimeStamp("1990/01/01 00:00:00"))
                .setMaxMillseconds(DateUtils.date2TimeStamp("2030/12/31 00:00:00"))
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimaryDark))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.red))
                .setWheelItemTextSize(14)
                .build();

        mDialogYearMonth = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setCallBack(this)
                .build();
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
        mDialogMonthDayHourMinute = new TimePickerDialog.Builder()
                .setType(Type.MONTH_DAY_HOUR_MIN)
                .setCallBack(this)
                .build();
        mDialogHourMinute = new TimePickerDialog.Builder()
                .setType(Type.HOURS_MINS)
                .setCallBack(this)
                .build();
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
        }
        if (type == LeaveListActivity.OUT_FOR_WORK) {
            mTvTitle.setText("申请公出");
        } else if (type == LeaveListActivity.LEAVE) {
            mTvTitle.setText("申请请假");
        }
        mTvRight.setText("保存");
        mTvRight.setVisibility(View.VISIBLE);
    }

    private void initView() {
        mTvCheckPerson = (TextView) findViewById(R.id.tv_check_person);
        mTvStartTime = (TextView) findViewById(R.id.tv_start_time);
        mRlStartTime = (RelativeLayout) findViewById(R.id.rl_start_time);
        mTvEndTime = (TextView) findViewById(R.id.tv_end_time);
        mRlEndTime = (RelativeLayout) findViewById(R.id.rl_end_time);
        mTvLeaveReason = (TextView) findViewById(R.id.tv_leave_reason);
        mEtTaskDescribe = (EditText) findViewById(R.id.et_task_describe);
        mActivityLeave2 = (LinearLayout) findViewById(R.id.activity_leave2);
        mTvRight.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
        mTvStartTime.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String describe = mEtTaskDescribe.getText().toString().trim();
        if (TextUtils.isEmpty(describe)) {
            Toast.makeText(this, "请填写公出理由", Toast.LENGTH_SHORT).show();
            return;
        }
        leaveBean = new LeaveBean();
        leaveBean.setStartTime(startTimeStamp);
        leaveBean.setEndTime(endTimeStamp);
        leaveBean.setReason(describe);
        leaveBean.setLeaveType(type);
        leaveBean.setEmail(UserInfoTools.getEmail(this));
        List<UserBean> userBeen = DataSupport.where("email=?", UserInfoTools.getEmail(this)).find(UserBean.class);
        leaveBean.setLeavePerson(userBeen.get(0).getRealName());
        leaveBean.save();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new LeaveSuccess());
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_right:
                submit();
                break;
            case R.id.tv_start_time:
                mDialogAll.show(getSupportFragmentManager(), "all");
                flag = 1;
                break;

            case R.id.tv_end_time:
                mDialogAll.show(getSupportFragmentManager(), "all");
                flag = 2;
                break;
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = DateUtils.timeStampToExactlyDate(millseconds);
        if (flag == 1) {
            startTimeStamp=millseconds;
            mTvStartTime.setText(text);
        } else if (flag == 2) {
            endTimeStamp=millseconds;
            mTvEndTime.setText(text);
        }
    }

    public class LeaveSuccess{}
}
