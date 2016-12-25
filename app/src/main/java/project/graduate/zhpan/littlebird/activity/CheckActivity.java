package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.CheckAdapter;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;

public class CheckActivity extends BaseActivity {
    private ListView mListView;
    private ColleagueAdapter mAdapter;
    private long userId=0;
    private String userName="";
    private List<ColleagueBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        initView();
        setData();
        setListener();
    }
    private void setListener(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(CheckActivity.this,CheckTaskActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        mListView= (ListView) findViewById(R.id.lv_check);
    }

    private void setData(){
        mTvTitle.setText("审批");
        mList=new ArrayList<>();
        mAdapter=new ColleagueAdapter(this);
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);

    }
}
