package project.graduate.zhpan.littlebird.activity;

import android.widget.ListView;
import org.litepal.crud.DataSupport;
import java.util.List;
import butterknife.BindView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.EncourageAdapter;
import project.graduate.zhpan.littlebird.bean.EncourageBean;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class EncourageActivity extends BaseActivity {
    @BindView(R.id.lv_encourage)
    ListView mLvEncourage;
    private EncourageAdapter mAdapter;
    private List<EncourageBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_encourage;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mTvTitle.setText("奖励");
        mAdapter = new EncourageAdapter(this);
        mList = DataSupport.where("email=?", UserInfoTools.getEmail(this)).find(EncourageBean.class);
        mAdapter.setList(mList);
        mLvEncourage.setAdapter(mAdapter);
    }
}
