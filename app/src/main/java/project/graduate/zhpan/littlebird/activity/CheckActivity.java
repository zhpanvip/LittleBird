package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class CheckActivity extends BaseActivity{
    @BindView(R.id.lv_check)
    ListView mListView;
    @BindView(R.id.tv_no_data)
    TextView mTvNoData;
    private ColleagueAdapter mAdapter;
    private long userId = 0;
    private String userName = "";
    private List<UserBean> mList;
    private UserBean userBean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_check;
    }

    @Override
    protected void init() {
        initView();
        initData();
        setData();
        setListener();
    }

    private void initData() {
        userBean = DataSupport.where("email=?", UserInfoTools.getEmail(this)).find(UserBean.class).get(0);
    }

    private void setListener() {
        mListView.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long l)-> {
                ColleagueAdapter adapter = (ColleagueAdapter) adapterView.getAdapter();
                List list = adapter.getmList();
                UserBean user = (UserBean) list.get(position);
                Intent intent = new Intent(CheckActivity.this, CheckTaskActivity.class);
                intent.putExtra("email", user.getEmail());
                intent.putExtra("userName", user.getRealName());
                startActivity(intent);
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_check);
        mTvNoData = (TextView) findViewById(R.id.tv_no_data);
    }

    private void setData() {
        mTvTitle.setText("审批");
        if (userBean.isAdmin()) {
            mTvRight.setText("审批请假");
            mTvRight.setVisibility(View.VISIBLE);
            mList = new ArrayList<>();
            mList = DataSupport.where("email !=?", UserInfoTools.getEmail(this)).find(UserBean.class);
            mAdapter = new ColleagueAdapter(this);
            mAdapter.setList(mList);
            mListView.setAdapter(mAdapter);
        } else {
            mTvNoData.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.tv_right)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                startActivity(new Intent(this, CheckLeaveActivity.class));
                break;
        }
    }
}
