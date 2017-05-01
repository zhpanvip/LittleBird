package project.graduate.zhpan.littlebird.activity;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.ProjectAdapter;
import project.graduate.zhpan.littlebird.bean.ProjectBean;

public class ProjectActivity extends BaseActivity {
    @BindView(R.id.lv_project)
    ListView mListView;
    private List<ProjectBean> mList;
    private ProjectAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project;
    }

    @Override
    protected void init() {
        setData();
    }


    private void setData() {
        mTvTitle.setText("项目管理");
        mList = new ArrayList<>();
        mAdapter = new ProjectAdapter(this);
        mListView.setAdapter(mAdapter);
    }
}
