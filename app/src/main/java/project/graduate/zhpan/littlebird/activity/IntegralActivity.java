package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.IntegralAdapter;
import project.graduate.zhpan.littlebird.bean.IntegralBean;

public class IntegralActivity extends BaseActivity {

    private ListView mLvIntegral;
    private IntegralAdapter mAdapter;
    private List<IntegralBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        initView();
        setData();
    }

    private void setData() {
        mTvTitle.setText("积分明细");
        mAdapter=new IntegralAdapter(this);
        mAdapter.setmList(mList);
        mLvIntegral.setAdapter(mAdapter);
    }

    private void initView() {
        mLvIntegral = (ListView) findViewById(R.id.lv_integral);
    }
}
