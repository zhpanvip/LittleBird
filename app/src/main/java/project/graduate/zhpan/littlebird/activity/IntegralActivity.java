package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.IntegralAdapter;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class IntegralActivity extends BaseActivity {

    @BindView(R.id.lv_integral)
    ListView mLvIntegral;
    private IntegralAdapter mAdapter;
    private List<IntegralBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mTvTitle.setText("积分明细");
        mAdapter = new IntegralAdapter(this);
        mList = DataSupport.where("email=?", UserInfoTools.getEmail(this)).find(IntegralBean.class);
        mAdapter.setList(mList);
        mLvIntegral.setAdapter(mAdapter);
    }


}
