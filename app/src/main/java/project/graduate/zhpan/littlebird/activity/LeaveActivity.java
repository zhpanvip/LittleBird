package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.LeaveAdapter;
import project.graduate.zhpan.littlebird.bean.LeaveBean;

public class LeaveActivity extends BaseActivity {
    private ListView mListView;
    private LeaveAdapter mAdapter;
    private List<LeaveBean> mList;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        initView();
        setData();
    }

    private void setData() {
        Intent intent = getIntent();
        if(intent!=null){
           type=intent.getIntExtra("type",0);
        }
        if(type==LeaveListActivity.OUT_FOR_WORK){
            mTvTitle.setText("申请公出");
        }else if(type==LeaveListActivity.LEAVE){
            mTvTitle.setText("申请请假");
        }
    }

    private void initView() {


    }
}
