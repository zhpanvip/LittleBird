package project.graduate.zhpan.littlebird.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.BaseFragmentAdapter;
import project.graduate.zhpan.littlebird.fragment.RankFragment;

public class RankActivity extends BaseActivity {

    @BindView(R.id.tv_day) TextView mTvDay;
    @BindView(R.id.view_day) View mViewDay;
    @BindView(R.id.ll_day) RelativeLayout mLlDay;
    @BindView(R.id.tv_week) TextView mTvWeek;
    @BindView(R.id.view_week) View mViewWeek;
    @BindView(R.id.ll_week) RelativeLayout mLlWeek;
    @BindView(R.id.tv_month) TextView mTvMonth;
    @BindView(R.id.view_month) View mViewMonth;
    @BindView(R.id.ll_month) RelativeLayout mLlMonth;
    @BindView(R.id.tv_quarter) TextView mTvQuarter;
    @BindView(R.id.view_quarter) View mViewQuarter;
    @BindView(R.id.ll_quarter) RelativeLayout mLlQuarter;
    @BindView(R.id.tv_year) TextView mTvYear;
    @BindView(R.id.view_year) View mViewYear;
    @BindView(R.id.ll_year) RelativeLayout mLlYear;
    @BindView(R.id.vp_rank) ViewPager mVpRank;

    private int prePosition = 0;
    private List<TextView> mTvList;
    private List<View> mViewList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void init() {
        setListener();
        setData();
    }

    private void setListener() {
        mVpRank.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewList.get(position).setBackgroundColor(Color.parseColor("#299FCC"));
                mTvList.get(position).setTextColor(Color.parseColor("#299FCC"));
                mViewList.get(prePosition).setBackgroundColor(Color.parseColor("#999999"));
                mTvList.get(prePosition).setTextColor(Color.parseColor("#333333"));
                prePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setData() {
        mTvTitle.setText("排名");

        RankFragment mDayFragment = new RankFragment();
        mDayFragment.setRankType(RankFragment.TYPE_DAY);
        RankFragment mWeekFragment = new RankFragment();
        mWeekFragment.setRankType(RankFragment.TYPE_WEEK);
        RankFragment mMonthFragment = new RankFragment();
        mMonthFragment.setRankType(RankFragment.TYPE_MONTH);
        RankFragment mQuarterFragment = new RankFragment();
        mQuarterFragment.setRankType(RankFragment.TYPE_QUARTER);
        RankFragment mYearFragment = new RankFragment();
        mYearFragment.setRankType(RankFragment.TYPE_YEAR);

        List<RankFragment> mList = new ArrayList<>();
        mTvList = new ArrayList<>();
        mViewList = new ArrayList<>();
        Collections.addAll(mList, mDayFragment, mWeekFragment, mMonthFragment, mQuarterFragment, mYearFragment);
        Collections.addAll(mTvList, mTvDay, mTvWeek, mTvMonth, mTvQuarter, mTvYear);
        Collections.addAll(mViewList, mViewDay, mViewWeek, mViewMonth, mViewQuarter, mViewYear);

        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragmentAdapter mFragmentAdapter = new BaseFragmentAdapter(fragmentManager, RankActivity.this);
        mFragmentAdapter.setmFragmentList(mList);
        mVpRank.setAdapter(mFragmentAdapter);

    }
}
