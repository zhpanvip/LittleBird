package project.graduate.zhpan.littlebird.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.BaseFragmentAdapter;
import project.graduate.zhpan.littlebird.bean.NoticeBean;
import project.graduate.zhpan.littlebird.fragment.BaseFragment;
import project.graduate.zhpan.littlebird.fragment.NoticeFragment;
import project.graduate.zhpan.littlebird.fragment.QuestionFragment;

public class NoticeActivity extends BaseActivity {

    private ViewPager mViewPager;
    private List<BaseFragment> mList;
    private QuestionFragment mQuestionFragment;
    private NoticeFragment mNoticeFragment;
    private BaseFragmentAdapter mFragmentAdapter;
    private int prePosition=0;
    private List<TextView> mTextViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initView();
        setData();
        setListener();
    }

    private void setListener(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvItem1 = mTextViewList.get(prePosition);
                mTvItem1.setBackgroundColor(Color.parseColor("#299FCC"));
                mTvItem1.setTextColor(Color.parseColor("#FFFFFF"));
                mTvItem2 = mTextViewList.get(position);
                mTvItem2.setBackgroundColor(Color.parseColor("#CDEEFD"));
                mTvItem2.setTextColor(Color.parseColor("#299FCC"));
                prePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.vp_notice);

    }

    private void setData() {
        mTvTitle.setVisibility(View.GONE);
        mLinearLayout.setVisibility(View.VISIBLE);
        mTvItem1.setText("通知列表");
        mTvItem2.setText("常见问题");

        mNoticeFragment=new NoticeFragment();
        mQuestionFragment=new QuestionFragment();
        mList=new ArrayList<>();
        mList.add(mNoticeFragment);
        mList.add(mQuestionFragment);
        mFragmentAdapter =new BaseFragmentAdapter(getSupportFragmentManager(),this);
        mFragmentAdapter.setmFragmentList(mList);
        mViewPager.setAdapter(mFragmentAdapter);
        mTextViewList=new ArrayList<>();
        mTextViewList.add(mTvItem1);
        mTextViewList.add(mTvItem2);

    }


}
