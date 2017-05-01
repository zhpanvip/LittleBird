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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.LeaveAdapter;
import project.graduate.zhpan.littlebird.bean.LeaveBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class LeaveActivity extends BaseActivity implements OnDateSetListener {
    @BindView(R.id.tv_check_person)
    TextView mTvCheckPerson;
    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;
    @BindView(R.id.rl_start_time)
    RelativeLayout mRlStartTime;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.rl_end_time)
    RelativeLayout mRlEndTime;
    @BindView(R.id.tv_leave_reason)
    TextView mTvLeaveReason;
    @BindView(R.id.et_task_describe)
    EditText mEtTaskDescribe;
    @BindView(R.id.activity_leave2)
    LinearLayout mActivityLeave2;
    private LeaveAdapter mAdapter;
    private List<LeaveBean> mList;
    private int type;
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
    protected int getLayoutId() {
        return R.layout.activity_leave;
    }

    @Override
    protected void init() {
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
        List<UserBean> userBeen = DataSupport
                .where("email=?", UserInfoTools.getEmail(this))
                .find(UserBean.class);
        leaveBean.setLeavePerson(userBeen.get(0).getRealName());
        if (leaveBean.save()) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new LeaveSuccess());
            finish();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = DateUtils.timeStampToExactlyDate(millseconds);
        if (flag == 1) {
            startTimeStamp = millseconds;
            mTvStartTime.setText(text);
        } else if (flag == 2) {
            endTimeStamp = millseconds;
            mTvEndTime.setText(text);
        }
    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                submit();
                break;
            case R.id.tv_start_time:
                break;
            case R.id.tv_end_time:
                break;

        }
    }

    public class LeaveSuccess {
    }
}
