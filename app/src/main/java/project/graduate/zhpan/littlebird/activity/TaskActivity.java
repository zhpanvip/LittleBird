package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.TaskAdapter;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.callback.TaskOptionListener;

public class TaskActivity extends BaseActivity implements View.OnClickListener,TaskOptionListener{

    private ListView mLvTask;
    private TextView mTvHistory;
    private TaskAdapter mTaskAdapter;
    private List<TaskBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initView();
        initData();
        setListener();
    }
    private void setListener(){
        mTvHistory.setOnClickListener(this);
    }
    private void initData() {
        mTvTitle.setText("我的任务");
        mTaskAdapter=new TaskAdapter(this);
        mList=new ArrayList<>();
        mList= DataSupport.findAll(TaskBean.class);
        mTaskAdapter.setList(mList);
        mLvTask.setAdapter(mTaskAdapter);
    }



    private void initView() {
        mLvTask= (ListView) findViewById(R.id.lv_task);
        mTvHistory= (TextView) findViewById(R.id.tv_history_task);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_history_task:
                Intent  intent=new Intent(this,HistoryTaskActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDeleteSuccess(int position) {
        TaskBean taskBean = (TaskBean) mTaskAdapter.getmList().get(position);
        DataSupport.deleteAll(TaskBean.class, "taskName=  ?" ,( taskBean.getTaskName()));
        mList= DataSupport.findAll(TaskBean.class);
        mTaskAdapter.setList(mList);
        mTaskAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEdit(int position) {

    }

    @Override
    public void onCommit(int position) {
        TaskBean taskBean = (TaskBean) mTaskAdapter.getmList().get(position);
        taskBean.setCommit(true);
        taskBean.save();
        mList= DataSupport.findAll(TaskBean.class);
        mTaskAdapter.setList(mList);
        mTaskAdapter.notifyDataSetChanged();

    }
}
