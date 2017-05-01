package project.graduate.zhpan.littlebird.fragment;

import android.view.View;
import android.widget.ListView;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.NoticeAdapter;
import project.graduate.zhpan.littlebird.bean.NoticeBean;

/**
 * Created by zhpan on 2016/12/22.
 */

public class NoticeFragment extends BaseFragment {
    @BindView(R.id.lv_notice)
    ListView mListView;
    private List<NoticeBean> mList;
    private NoticeAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mList = new ArrayList<>();
        mList = DataSupport.findAll(NoticeBean.class, false);
        mAdapter = new NoticeAdapter(getContext());
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);
    }
}
