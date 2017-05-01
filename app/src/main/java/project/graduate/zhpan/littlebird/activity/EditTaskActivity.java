package project.graduate.zhpan.littlebird.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class EditTaskActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private ImageView mIvDown;
    private TextView mTvTaskType;
    private TextView mTvCheckPerson;
    private TextView mTvTaskName;
    private EditText mEtTask;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TextView mTvTaskDescribe;
    private EditText mEtTaskDescribe;
    private LinearLayout mActivityEditTask;
    private TimePickerDialog mDialogAll;
    private TimePickerDialog mDialogYearMonth;
    private TimePickerDialog mDialogYearMonthDay;
    private TimePickerDialog mDialogMonthDayHourMinute;
    private TimePickerDialog mDialogHourMinute;
    private int flag = 0; //  标志哪一个时间选择其
    private String title="";
    private long startTimeStamp;
    private long endTimeStamp;
    private TaskBean taskBean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_task;
    }

    @Override
    protected void init() {
        initView();
        setData();
        setListener();
    }


    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
            mTvTitle.setText(title);
        }
        mTvRight.setVisibility(View.VISIBLE);
        // 初始化任务实体类
        taskBean = new TaskBean();
        taskBean.setStartTime(System.currentTimeMillis());
        taskBean.setEndTime(System.currentTimeMillis());
        taskBean.setCheckPerson("李伟");

        String currentTime = DateUtils.timeStampToExactlyDate(System.currentTimeMillis());
        startTimeStamp=System.currentTimeMillis();
        endTimeStamp=System.currentTimeMillis();
        if (title.equals("添加任务")) {
            mTvStartTime.setText(currentTime);
            mTvEndTime.setText(currentTime);
        } else if (title.equals("编辑任务")) {
            mTvStartTime.setText(currentTime);
            mTvEndTime.setText(currentTime);
        }


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

    private void setListener() {
        mTvStartTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
    }

    private void initView() {
        mIvDown = (ImageView) findViewById(R.id.iv_down);
        mTvTaskType = (TextView) findViewById(R.id.tv_task_type);
        mTvCheckPerson = (TextView) findViewById(R.id.tv_check_person);
        mTvTaskName = (TextView) findViewById(R.id.tv_task_name);
        mEtTask = (EditText) findViewById(R.id.et_task);
        mTvStartTime = (TextView) findViewById(R.id.tv_start_time);
        mTvEndTime = (TextView) findViewById(R.id.tv_end_time);
        mTvTaskDescribe = (TextView) findViewById(R.id.tv_task_describe);
        mEtTaskDescribe = (EditText) findViewById(R.id.et_task_describe);
        mActivityEditTask = (LinearLayout) findViewById(R.id.activity_edit_task);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:

                mDialogAll.show(getSupportFragmentManager(), "all");
                flag = 1;
                break;
            case R.id.tv_end_time:
                mDialogAll.show(getSupportFragmentManager(), "all");
                flag = 2;
                break;
            case R.id.tv_right:
                    submit();
                break;
        }
    }

    private void submit() {
        // validate
        String task = mEtTask.getText().toString().trim();
        if (TextUtils.isEmpty(task)) {
            Toast.makeText(this, "请填写任务名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String describe = mEtTaskDescribe.getText().toString().trim();
        if (TextUtils.isEmpty(describe)) {
            Toast.makeText(this, "请填写任务说明", Toast.LENGTH_SHORT).show();
            return;
        }
        if(startTimeStamp>=endTimeStamp){
            Toast.makeText(this, "结束时间应该大于开始时间", Toast.LENGTH_SHORT).show();
            return;
        }

        saveToDB();
    }
    //  添加任务
    private void saveToDB() {
        taskBean.setTaskName(mEtTask.getText().toString());
        taskBean.setTaskDescribe(mEtTaskDescribe.getText().toString());
        taskBean.setCreateDate(DateUtils.getDate());
        taskBean.setUserEmail(UserInfoTools.getEmail(this));
        if(taskBean.save()){    //  保存到数据库
            Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new TaskCreateSuccess() );
            finish();
        }else {
            Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
        }

    }

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, EditTaskActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


    /**
     * 时间选择器选择时间后的回调方法
     *
     * @param timePickerView
     * @param millseconds
     */
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = DateUtils.timeStampToExactlyDate(millseconds);
        if (flag == 1) {
            startTimeStamp=millseconds;
            taskBean.setStartTime(startTimeStamp);
            mTvStartTime.setText(text);
        } else if (flag == 2) {
            endTimeStamp=millseconds;
            taskBean.setEndTime(endTimeStamp);
            mTvEndTime.setText(text);
        }

    }

    public class TaskCreateSuccess{}
}
