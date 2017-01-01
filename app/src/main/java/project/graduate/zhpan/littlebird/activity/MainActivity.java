package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.fragment.ColleagueFragment;
import project.graduate.zhpan.littlebird.fragment.HomePageFragment;
import project.graduate.zhpan.littlebird.fragment.MeFragment;
import project.graduate.zhpan.littlebird.fragment.TopicFragment;
import project.graduate.zhpan.littlebird.utils.DateUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager mSupportFragmentManager;
    private LinearLayout mLinearLayoutHomePage, mLinearLayoutColleague, mLinearLayoutAdd,mLinearLayoutTopic, mLinearLayoutMe;
    private RelativeLayout mRlMain;
    private TextView mTextViewHomePage, mTextViewColleague, mTextViewAdd,mTextViewTopic, mTextViewMe;
    private ImageView mImageViewHomePage, mImageViewColleague, mImageViewAdd,mImageViewTopic, mImageViewMe,mIvClose;
    private List<ImageView> mImageViewList;
    private List<TextView> mTextViewList;
    private int[] image1={R.drawable.index_icon_home_current,R.drawable.index_icon_colleague_current,R.drawable.icon_close,R.drawable.index_icon_message_current,R.drawable.index_icon_about_current};
    private int[] image2={R.drawable.index_icon_home,R.drawable.index_icon_colleague,R.drawable.icon_open,R.drawable.index_icon_message,R.drawable.index_icon_about};
    private int preposition=0;
    private boolean isShow=false;   //是否显示更多布局
    private LinearLayout llEditText;
    private LinearLayout llWriteTask;
    private LinearLayout llQuerySalary;
    private LinearLayout llAdduser;
    private long mExitTime;
    private LinearLayout mLlMenu;
    private TextView mTvMonth;
    private TextView mTvDay;
    private TextView mTvWeek;
    private TextView mLunarMonth;
    private TextView mLunarDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setListener();
    }

    private void initData() {

        EventBus.getDefault().register(this);

        mTvMonth.setText(DateUtils.getMonth());
        mTvDay.setText(DateUtils.getDayOfMonth());
        mTvWeek.setText(DateUtils.getDayOfWeek());
        mLunarMonth.setText(DateUtils.getLunarMonth());
        mLunarDay.setText(DateUtils.getLunarDayOfMonth());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isShow){
                mImageViewList.get(2).setBackgroundResource(image2[2]);
                mRlMain.setVisibility(View.GONE);
                isShow=false;
                return true;
            }
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setListener() {
        mLinearLayoutHomePage.setOnClickListener(this);
        mLinearLayoutColleague.setOnClickListener(this);
        mLinearLayoutAdd.setOnClickListener(this);
        mLinearLayoutTopic.setOnClickListener(this);
        mLinearLayoutMe.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
        mRlMain.setOnClickListener(this);
        llEditText.setOnClickListener(this);
        llQuerySalary.setOnClickListener(this);
        llWriteTask.setOnClickListener(this);
        llAdduser.setOnClickListener(this);
    }

    private void initView() {
        mLlMenu= (LinearLayout) findViewById(R.id.ll_main_menu);
        mLinearLayoutHomePage = (LinearLayout) findViewById(R.id.ll_main_homepage);
        mLinearLayoutColleague = (LinearLayout) findViewById(R.id.ll_main_colleague);
        mLinearLayoutAdd = (LinearLayout) findViewById(R.id.ll_main_add);
        mLinearLayoutTopic= (LinearLayout) findViewById(R.id.ll_main_topic);
        mLinearLayoutMe = (LinearLayout) findViewById(R.id.ll_main_me);
        mRlMain = (RelativeLayout) findViewById(R.id.rl_main);
        mTextViewHomePage = (TextView) findViewById(R.id.tv_main_homepage);
        mTextViewColleague = (TextView) findViewById(R.id.tv_main_colleague);
        mTextViewAdd = (TextView) findViewById(R.id.tv_main_add);
        mTextViewTopic= (TextView) findViewById(R.id.tv_main_topic);
        mTextViewMe = (TextView) findViewById(R.id.tv_main_me);

        mImageViewHomePage = (ImageView) findViewById(R.id.iv_main_homepage);
        mImageViewColleague = (ImageView) findViewById(R.id.iv_main_colleague);
        mImageViewAdd = (ImageView) findViewById(R.id.iv_main_add);
        mImageViewTopic= (ImageView) findViewById(R.id.iv_main_topic);
        mImageViewMe = (ImageView) findViewById(R.id.iv_main_me);
        mIvClose= (ImageView) findViewById(R.id.iv_main_close);

        mImageViewList=new ArrayList<>();
        mTextViewList=new ArrayList<>();
        Collections.addAll(mImageViewList, mImageViewHomePage, mImageViewColleague, mImageViewAdd,mImageViewTopic, mImageViewMe);
        Collections.addAll(mTextViewList, mTextViewHomePage, mTextViewColleague, mTextViewAdd,mTextViewTopic, mTextViewMe);

        llEditText= (LinearLayout) findViewById(R.id.ll_edit_task);
        llQuerySalary= (LinearLayout) findViewById(R.id.ll_main_payroll);
        llWriteTask= (LinearLayout) findViewById(R.id.ll_main_write_topic);
        llAdduser= (LinearLayout) findViewById(R.id.ll_add_user);
        mTvMonth= (TextView) findViewById(R.id.tv_month);
        mTvDay= (TextView) findViewById(R.id.tv_day);
        mTvWeek= (TextView) findViewById(R.id.tv_week);
        mLunarMonth= (TextView) findViewById(R.id.tv_lunar_month);
        mLunarDay= (TextView) findViewById(R.id.tv_lunar_day);
        mSupportFragmentManager = getSupportFragmentManager();
        HomePageFragment mRecommendFragment = new HomePageFragment();
        FragmentTransaction recomTransaction = mSupportFragmentManager.beginTransaction();
        recomTransaction.replace(R.id.rl_fragment, mRecommendFragment);
        recomTransaction.commit();
    }
        @Subscribe
        public void getExitEvent(SettingActivity.Exit exit){
            finish();
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_main_homepage:
                if(preposition!=0){
                    mImageViewList.get(0).setBackgroundResource(image1[0]);
                    mTextViewList.get(0).setTextColor(Color.parseColor("#20BDFC"));
                    mImageViewList.get(preposition).setBackgroundResource(image2[preposition]);
                    mTextViewList.get(preposition).setTextColor(Color.parseColor("#666666"));
                    preposition=0;
                    HomePageFragment homePageFragment = new HomePageFragment();
                    FragmentTransaction homePageTransaction = mSupportFragmentManager.beginTransaction();
                    homePageTransaction.replace(R.id.rl_fragment, homePageFragment);
                    homePageTransaction.commit();
                }


                break;
            case R.id.ll_main_colleague:
                if(preposition!=1){
                    mImageViewList.get(1).setBackgroundResource(image1[1]);
                    mTextViewList.get(1).setTextColor(Color.parseColor("#20BDFC"));
                    mImageViewList.get(preposition).setBackgroundResource(image2[preposition]);
                    mTextViewList.get(preposition).setTextColor(Color.parseColor("#666666"));
                    preposition=1;
                    ColleagueFragment find_index_fragment = new ColleagueFragment();
                    FragmentTransaction findTransaction = mSupportFragmentManager.beginTransaction();
                    findTransaction.replace(R.id.rl_fragment,find_index_fragment);
                    findTransaction.commit();
                }

                break;
            case R.id.ll_main_add:
                if(isShow){
                    mImageViewList.get(2).setBackgroundResource(image2[2]);
                    mRlMain.setVisibility(View.GONE);
                    isShow=false;
                }else {
                    mImageViewList.get(2).setBackgroundResource(image1[2]);
                    mRlMain.setVisibility(View.VISIBLE);
                    isShow=true;
                }

                /*MallFragment mallFragment = new MallFragment();
                FragmentTransaction mallTransaction = mSupportFragmentManager.beginTransaction();
                mallTransaction.replace(R.id.frame,mallFragment);
                mallTransaction.commit();*/
                break;
            case R.id.iv_main_close:
                mRlMain.setVisibility(View.GONE);
                isShow=false;
                mImageViewList.get(2).setBackgroundResource(image2[2]);
                break;
            case R.id.rl_main:
                break;
            case R.id.ll_main_topic:
                if(preposition!=3){

                    mImageViewList.get(3).setBackgroundResource(image1[3]);
                    mTextViewList.get(3).setTextColor(Color.parseColor("#20BDFC"));
                    mImageViewList.get(preposition).setBackgroundResource(image2[preposition]);
                    mTextViewList.get(preposition).setTextColor(Color.parseColor("#666666"));
                    preposition=3;
                    TopicFragment topicFragment = new TopicFragment();
                    FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rl_fragment,topicFragment);
                    fragmentTransaction.commit();
                }

                break;
            case R.id.ll_main_me:
                if(preposition!=4){
                    mImageViewList.get(4).setBackgroundResource(image1[4]);
                    mTextViewList.get(4).setTextColor(Color.parseColor("#20BDFC"));
                    mImageViewList.get(preposition).setBackgroundResource(image2[preposition]);
                    mTextViewList.get(preposition).setTextColor(Color.parseColor("#666666"));
                    preposition=4;
                    MeFragment mineFragment = new MeFragment();
                    FragmentTransaction mineTransaction = mSupportFragmentManager.beginTransaction();
                    mineTransaction.replace(R.id.rl_fragment,mineFragment);
                    mineTransaction.commit();
                }


                break;
            case R.id.ll_edit_task:
                mImageViewList.get(2).setBackgroundResource(image2[2]);
                EditTaskActivity.start(this,"添加任务");
                mRlMain.setVisibility(View.GONE);
                isShow=false;
                break;
            case R.id.ll_main_payroll:
                mImageViewList.get(2).setBackgroundResource(image2[2]);
                startActivity(new Intent(this,QuerySalaryActivity.class));
                mRlMain.setVisibility(View.GONE);
                isShow=false;
                break;
            case R.id.ll_main_write_topic:
                mImageViewList.get(2).setBackgroundResource(image2[2]);
                startActivity(new Intent(this,WriteTopicActivity.class));
                mRlMain.setVisibility(View.GONE);
                isShow=false;
                break;
            case R.id.ll_add_user:
                mImageViewList.get(2).setBackgroundResource(image2[2]);
                RegisterActivity.start(this);
                mRlMain.setVisibility(View.GONE);
                isShow=false;
                break;
        }
    }

}
