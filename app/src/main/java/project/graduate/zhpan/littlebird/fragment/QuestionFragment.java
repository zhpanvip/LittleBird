package project.graduate.zhpan.littlebird.fragment;

import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.QuestionAdapter;
import project.graduate.zhpan.littlebird.bean.QuestionBean;

/**
 * Created by zhpan on 2016/12/24.
 */

public class QuestionFragment extends BaseFragment {
    @BindView(R.id.lv_question)
    ListView mListView;
    private QuestionAdapter mAdapter;
    private List<QuestionBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_question;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mAdapter = new QuestionAdapter(getContext());
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);
    }
}
