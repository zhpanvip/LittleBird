package project.graduate.zhpan.littlebird.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.utils.SideBar;

/**
 * Created by zhpan on 2016/10/15.
 */

public class ColleagueFragment extends BaseFragment {
    private ListView mListView;
    private SideBar mSideBar;
    private ColleagueAdapter adapter;
    private List<ColleagueBean> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_colleague, null);
        initView();
        setData();
        setListener();
        return mView;

    }
    private void setListener(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserInfoActivity.start(getActivity(),"张飞");
            }
        });
    }
    private void setData() {
        mTvTitle.setText("LittleBird");
        mIvBack.setVisibility(View.GONE);
        adapter = new ColleagueAdapter(getContext());
        mList = new ArrayList<>();
        adapter.setList(mList);
        mListView.setAdapter(adapter);
        /*float f= (float) 0.5;
        mSideBar.setAlpha(f);*/
    }

    private void initView() {
        mTvTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mIvBack = (ImageView) mView.findViewById(R.id.iv_back);
        mListView = (ListView) mView.findViewById(R.id.lv_colleague_name);
        mSideBar = (SideBar) mView.findViewById(R.id.sidrbar);
    }
}
