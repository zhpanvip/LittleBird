package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.CheckTaskAdapter;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;

public class CheckTaskActivity extends BaseActivity{

    @BindView(R.id.tv_history_task)
    TextView mTvHistory;
    @BindView(R.id.lv_task)
    ListView mLvTask;
    private CheckTaskAdapter mTaskAdapter;
    private List<TaskBean> mList;
    private String userEmail;
    private String userName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_task;
    }

    @Override
    protected void init() {
        initData();
    }


    private void initData() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra("email");
            userName = intent.getStringExtra("userName");
        }
        mTvTitle.setText(userName + "的任务");
        mTaskAdapter = new CheckTaskAdapter(this);
        mList = new ArrayList<>();
        mList = DataSupport
                .where("userEmail=? and createDate=?", userEmail, DateUtils.getDate())
                .find(TaskBean.class);
        mTaskAdapter.setList(mList);
        mLvTask.setAdapter(mTaskAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onCheckSuccess(GradeActivity.CheckSuccess checkSuccess) {
        mList = DataSupport.where("userEmail=? and createDate=?", userEmail, DateUtils.getDate()).find(TaskBean.class);
        mTaskAdapter.setList(mList);
        mLvTask.setAdapter(mTaskAdapter);
    }

    @OnClick(R.id.tv_history_task)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_history_task:
                Intent intent = new Intent(this, HistoryTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
