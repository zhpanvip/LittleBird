package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.SalaryAdapter;
import project.graduate.zhpan.littlebird.bean.SalaryBean;

public class QuerySalaryActivity extends BaseActivity {
   private ExpandableListView mListView;
    private SalaryAdapter mAdapter;
    private List<SalaryBean> mGroupdList;
    private List<List<SalaryBean>> mChildList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_salary);
        initView();
        setData();
    }

    private void initView() {
        mListView= (ExpandableListView) findViewById(R.id.elv_salary);

    }

    private void setData() {
        mTvTitle.setText("查工资");
        mAdapter=new SalaryAdapter(this);
        mChildList=new ArrayList<>();
        mGroupdList=new ArrayList<>();
        mAdapter.setmChileList(mChildList);
        mAdapter.setmGroupList(mGroupdList);
        mListView.setAdapter(mAdapter);

    }
}
