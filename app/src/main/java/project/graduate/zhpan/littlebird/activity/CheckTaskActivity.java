package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.CheckTaskAdapter;
import project.graduate.zhpan.littlebird.adapter.TaskAdapter;
import project.graduate.zhpan.littlebird.bean.TaskBean;

public class CheckTaskActivity extends BaseActivity implements View.OnClickListener{

    private ListView mLvTask;
    private TextView mTvHistory;
    private CheckTaskAdapter mTaskAdapter;
    private List<TaskBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_task);
        initView();
        initData();
        setListener();
    }
    private void setListener(){
        mTvHistory.setOnClickListener(this);
    }
    private void initData() {
        mTvTitle.setText("张飞的任务");
        mTaskAdapter=new CheckTaskAdapter(this);
        mList=new ArrayList<>();
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
}
