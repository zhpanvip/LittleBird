package project.graduate.zhpan.littlebird.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.BaseFragmentAdapter;
import project.graduate.zhpan.littlebird.fragment.RankFragment;

public class RankActivity extends BaseActivity implements View.OnClickListener {

    private RankFragment mDayFragment;
    private RankFragment mMonthFragment;
    private RankFragment mWeekFragment;
    private RankFragment mQuarterFragment;
    private RankFragment mYearFragment;

    private List<RankFragment> mList;
    private BaseFragmentAdapter mFragmentAdapter;

    private int prePosition = 0;
    private List<TextView> mTvList;
    private List<View> mViewList;
    private TextView mTvDay;
    private View mViewDay;
    private RelativeLayout mLlDay;
    private TextView mTvWeek;
    private View mViewWeek;
    private RelativeLayout mLlWeek;
    private TextView mTvMonth;
    private View mViewMonth;
    private RelativeLayout mLlMonth;
    private TextView mTvQuarter;
    private View mViewQuarter;
    private RelativeLayout mLlQuarter;
    private TextView mTvYear;
    private View mViewYear;
    private RelativeLayout mLlYear;
    private ViewPager mVpRank;
    private LinearLayout mActivityRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initView();
        setData();
    }

    private void initView() {

        mTvDay = (TextView) findViewById(R.id.tv_day);
        mTvDay.setOnClickListener(this);
        mViewDay = (View) findViewById(R.id.view_day);
        mViewDay.setOnClickListener(this);
        mLlDay = (RelativeLayout) findViewById(R.id.ll_day);
        mLlDay.setOnClickListener(this);
        mTvWeek = (TextView) findViewById(R.id.tv_week);
        mTvWeek.setOnClickListener(this);
        mViewWeek = (View) findViewById(R.id.view_week);
        mViewWeek.setOnClickListener(this);
        mLlWeek = (RelativeLayout) findViewById(R.id.ll_week);
        mLlWeek.setOnClickListener(this);
        mTvMonth = (TextView) findViewById(R.id.tv_month);
        mTvMonth.setOnClickListener(this);
        mViewMonth = (View) findViewById(R.id.view_month);
        mViewMonth.setOnClickListener(this);
        mLlMonth = (RelativeLayout) findViewById(R.id.ll_month);
        mLlMonth.setOnClickListener(this);
        mTvQuarter = (TextView) findViewById(R.id.tv_quarter);
        mTvQuarter.setOnClickListener(this);
        mViewQuarter = (View) findViewById(R.id.view_quarter);
        mViewQuarter.setOnClickListener(this);
        mLlQuarter = (RelativeLayout) findViewById(R.id.ll_quarter);
        mLlQuarter.setOnClickListener(this);
        mTvYear = (TextView) findViewById(R.id.tv_year);
        mTvYear.setOnClickListener(this);
        mViewYear = (View) findViewById(R.id.view_year);
        mViewYear.setOnClickListener(this);
        mLlYear = (RelativeLayout) findViewById(R.id.ll_year);
        mLlYear.setOnClickListener(this);
        mVpRank = (ViewPager) findViewById(R.id.vp_rank);
        mVpRank.setOnClickListener(this);
        mActivityRank = (LinearLayout) findViewById(R.id.activity_rank);
        mActivityRank.setOnClickListener(this);

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

        mDayFragment = new RankFragment();
        mDayFragment.setRankType(RankFragment.TYPE_DAY);
        mWeekFragment = new RankFragment();
        mWeekFragment.setRankType(RankFragment.TYPE_WEEK);
        mMonthFragment = new RankFragment();
        mMonthFragment.setRankType(RankFragment.TYPE_MONTH);
        mQuarterFragment = new RankFragment();
        mQuarterFragment.setRankType(RankFragment.TYPE_QUARTER);
        mYearFragment = new RankFragment();
        mYearFragment.setRankType(RankFragment.TYPE_YEAR);

        mList = new ArrayList<>();
        mTvList = new ArrayList<>();
        mViewList = new ArrayList<>();
        Collections.addAll(mList, mDayFragment, mWeekFragment, mMonthFragment, mQuarterFragment, mYearFragment);
        Collections.addAll(mTvList, mTvDay,mTvWeek,mTvMonth,mTvQuarter,mTvYear);
        Collections.addAll(mViewList,mViewDay,mViewWeek,mViewMonth,mViewQuarter,mViewYear);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentAdapter = new BaseFragmentAdapter(fragmentManager, RankActivity.this);
        mFragmentAdapter.setmFragmentList(mList);
        mVpRank.setAdapter(mFragmentAdapter);

    }

    @Override
    public void onClick(View view) {

    }
}
