package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.IntegralAdapter;
import project.graduate.zhpan.littlebird.bean.IntegralBean;

public class EncourageActivity extends BaseActivity {
    private ListView mLvEncourage;
    private IntegralAdapter mAdapter;
    private List<IntegralBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encourage);
        initView();
        setData();
    }
    private void setData() {
        mTvTitle.setText("奖励");
        mAdapter=new IntegralAdapter(this);
        mAdapter.setmList(mList);
        mLvEncourage.setAdapter(mAdapter);
    }

    private void initView() {
        mLvEncourage = (ListView) findViewById(R.id.lv_encourage);
    }

}
