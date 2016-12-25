package project.graduate.zhpan.littlebird.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.QuestionAdapter;
import project.graduate.zhpan.littlebird.bean.QuestionBean;

/**
 * Created by zhpan on 2016/12/24.
 */

public class QuestionFragment extends BaseFragment {
    private View mView;
    private ListView mListView;
    private QuestionAdapter mAdapter;
    private List<QuestionBean> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_question,null);
        initView();
        setData();
        return mView;
    }

    private void setData() {

    }

    private void initView() {
        mListView= (ListView) mView.findViewById(R.id.lv_question);
        mAdapter=new QuestionAdapter(getContext());
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);
    }
}
