package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.CheckLeaveAdapter;
import project.graduate.zhpan.littlebird.bean.LeaveBean;
import project.graduate.zhpan.littlebird.callback.CheckLeaveListener;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class CheckLeaveActivity extends BaseActivity implements CheckLeaveListener{

    private ListView mIvLv;
    private TextView mTvNoData;
    private List<LeaveBean> mList;
    private CheckLeaveAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_leave);
        initView();
        initData();
        setData();
        setListener();
    }

    private void setListener() {

    }

    private void setData() {

    }

    private void initData() {
        mTvTitle.setText("审批请假/公出");
        mList=new ArrayList<>();
        mList=DataSupport.where("email !=?", UserInfoTools.getEmail(this)).find(LeaveBean.class);
        mAdapter=new CheckLeaveAdapter(this);
        mAdapter.setList(mList);
        mIvLv.setAdapter(mAdapter);
    }

    private void initView() {
        mIvLv = (ListView) findViewById(R.id.iv_lv);
        mTvNoData = (TextView) findViewById(R.id.tv_no_data);
    }

    @Override
    public void onPassListener(int position) {
        LeaveBean leaveBean = (LeaveBean) mAdapter.getmList().get(position);
        leaveBean.setCheckResult(1);
        leaveBean.save();
        refresh();
    }

    @Override
    public void onRefusedListener(int position) {
        LeaveBean leaveBean = (LeaveBean) mAdapter.getmList().get(position);
        leaveBean.setCheckResult(2);
        leaveBean.save();
        refresh();
    }

    private void refresh(){
        mList=DataSupport.where("email !=?", UserInfoTools.getEmail(this)).find(LeaveBean.class);
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }
}
