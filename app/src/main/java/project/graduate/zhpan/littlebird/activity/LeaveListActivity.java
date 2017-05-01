package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.LeaveAdapter;
import project.graduate.zhpan.littlebird.bean.LeaveBean;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class LeaveListActivity extends BaseActivity implements View.OnClickListener{
    public static final int OUT_FOR_WORK = 1;  //  公出
    public static final int LEAVE = 2;   //  请假
    private int type;
    private ListView mListView;
    private LeaveAdapter mAdapter;
    private List<LeaveBean> mList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_leave_list;
    }

    @Override
    protected void init() {
        initView();
        initData();
        setData();
        setListener();
    }

    private void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setListener(){
        mTvRight.setOnClickListener(this);
    }
    private void initView() {
        mListView= (ListView) findViewById(R.id.lv_leave);
    }

    private void setData() {
        Intent intent = getIntent();
        if(intent!=null){
            type = intent.getIntExtra("type", 0);
            if(type==LEAVE){
                mTvTitle.setText("请假列表");
            }else if(type==OUT_FOR_WORK){
                mTvTitle.setText("公出列表");
            }
        }
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText("申请");
        mAdapter=new LeaveAdapter(this,type);
        mList=new ArrayList<>();

        mList=DataSupport.where("leaveType=? and email=?",type+"", UserInfoTools.getEmail(this)).find(LeaveBean.class);
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);
    }

    public  static void start(Context context,int type) {
        Intent starter = new Intent(context, LeaveListActivity.class);
        starter.putExtra("type",type);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_right:
                Intent intent=new Intent(this,LeaveActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
        }
    }
    @Subscribe
    public void onLeaveSuccess( LeaveActivity.LeaveSuccess leaveSuccess){
        mList=DataSupport.where("leaveType=? and email=?",type+"", UserInfoTools.getEmail(this)).find(LeaveBean.class);
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }
}
