package project.graduate.zhpan.littlebird.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.RankAdapter;
import project.graduate.zhpan.littlebird.bean.RankBean;

/**
 * Created by zhpan on 2016/12/18.
 */

public class RankFragment extends BaseFragment {
    private TextView tv_rank;
    private ListView lv_rank;
    private RankAdapter mAdapter;
    List<RankBean> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_rank, null);
        initView(mView);
        setData();
        return mView;
    }

    private void setData() {
        mList=new ArrayList<>();
        mAdapter=new RankAdapter(getContext());
        lv_rank.setAdapter(mAdapter);
    }

    private void initView(View mView) {
        tv_rank = (TextView) mView.findViewById(R.id.tv_rank);
        lv_rank = (ListView) mView.findViewById(R.id.lv_rank);
    }
}
