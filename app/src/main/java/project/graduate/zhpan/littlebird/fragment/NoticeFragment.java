package project.graduate.zhpan.littlebird.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.NoticeAdapter;
import project.graduate.zhpan.littlebird.bean.NoticeBean;
import project.graduate.zhpan.littlebird.view.ListViewForScrollView;

/**
 * Created by zhpan on 2016/12/22.
 */

public class NoticeFragment extends BaseFragment {
    private View mView;
    private ListView mListView;
    private List<NoticeBean> mList;
    private NoticeAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_notice,null);
        initView();
        setData();
        return mView;
    }

    private void setData() {
        mList=new ArrayList<>();
        mList = DataSupport.findAll(NoticeBean.class, false);
        mAdapter=new NoticeAdapter(getContext());
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);
    }

    private void initView() {
        mListView= (ListView) mView.findViewById(R.id.lv_notice);

    }
}
