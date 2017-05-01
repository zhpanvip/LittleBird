package project.graduate.zhpan.littlebird.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.RankAdapter;
import project.graduate.zhpan.littlebird.bean.RankBean;
import project.graduate.zhpan.littlebird.bean.UserBean;

/**
 * Created by zhpan on 2016/12/18.
 */

public class RankFragment extends BaseFragment {
    public static final String TYPE_DAY="averageDay";
    public static final String TYPE_WEEK="averageWEEK";
    public static final String TYPE_QUARTER="averageQuarter";
    public static final String TYPE_MONTH="averageMonth";
    public static final String TYPE_YEAR="averageYear";
    private TextView tv_rank;
    private ListView lv_rank;
    private RankAdapter mAdapter;
    private List<UserBean> mList;
    private TextView mTvNoData;
    private String rankType;

    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void init() {
        initView(mView);
        setData();
    }

    private void setData() {
        mList=new ArrayList<>();
        //mList= DataSupport.order(rankType+"desc").find(UserBean.class);
        mList= DataSupport.where(rankType+">0").order(rankType+"desc").find(UserBean.class);
        mAdapter=new RankAdapter(getContext(),rankType);
        mAdapter.setList(mList);
        lv_rank.setAdapter(mAdapter);
        if(mList.size()<=0){
            mTvNoData.setVisibility(View.VISIBLE);
        }
    }

    private void initView(View mView) {
        tv_rank = (TextView) mView.findViewById(R.id.tv_rank);
        lv_rank = (ListView) mView.findViewById(R.id.lv_rank);
        mTvNoData= (TextView) mView.findViewById(R.id.tv_no_data);
    }


}
