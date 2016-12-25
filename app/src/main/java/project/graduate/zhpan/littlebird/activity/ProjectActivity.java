package project.graduate.zhpan.littlebird.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.ProjectAdapter;
import project.graduate.zhpan.littlebird.bean.ProjectBean;

public class ProjectActivity extends BaseActivity {
    private ListView mListView;
    private List<ProjectBean> mList;
    private ProjectAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        initView();
        setData();
    }

    private void initView() {
        mListView= (ListView) findViewById(R.id.lv_project);
    }

    private void setData() {
        mTvTitle.setText("项目管理");
        mList=new ArrayList<>();
        mAdapter=new ProjectAdapter(this);
        mListView.setAdapter(mAdapter);
    }
}
