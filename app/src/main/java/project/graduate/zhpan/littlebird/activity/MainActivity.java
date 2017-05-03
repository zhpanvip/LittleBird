package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.fragment.ColleagueFragment;
import project.graduate.zhpan.littlebird.fragment.HomePageFragment;
import project.graduate.zhpan.littlebird.fragment.MeFragment;
import project.graduate.zhpan.littlebird.fragment.TopicFragment;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_edit_task)
    LinearLayout llEditText;
    @BindView(R.id.ll_main_write_topic)
    LinearLayout llWriteTask;
    @BindView(R.id.ll_main_payroll)
    LinearLayout llQuerySalary;
    @BindView(R.id.ll_second_line)
    LinearLayout llLine2;
    @BindView(R.id.ll_add_user)
    LinearLayout llAddUser;
    @BindView(R.id.ll_rest_user)
    LinearLayout llResetUser;
    @BindView(R.id.ll_delete_user)
    LinearLayout llDelUser;
    @BindView(R.id.tv_month)
    TextView mTvMonth;
    @BindView(R.id.tv_day)
    TextView mTvDay;
    @BindView(R.id.tv_week)
    TextView mTvWeek;
    @BindView(R.id.tv_lunar_month)
    TextView mLunarMonth;
    @BindView(R.id.tv_lunar_day)
    TextView mLunarDay;
    @BindView(R.id.iv_main_close)
    ImageView mIvClose;
    @BindView(R.id.rl_main)
    RelativeLayout mRlMain;
    @BindView(R.id.cb_add)
    CheckBox mCheckBox;
    @BindView(R.id.rg_tab)
    RadioGroup mRadioGroup; //  底部导航栏的四个按钮的RadioGroup

    private long mExitTime;
    private FragmentManager mSupportFragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        initData();
        setData();
        setListener();
    }


    private void setListener() {
        //  导航栏中间CheckBox改变的监听事件
        mCheckBox.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            if (isPopWindowShow()) {
                mRlMain.setVisibility(View.VISIBLE);
            } else {
                mRlMain.setVisibility(View.GONE);
            }

            if (UserInfoTools.isAdmin(MainActivity.this)) {
                llLine2.setVisibility(View.VISIBLE);
            } else {
                llLine2.setVisibility(View.GONE);
            }
        });

        //  导航栏RadioButton改变的监听事件
        mRadioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {

            FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
            switch (checkedId) {
                case R.id.rb_tab1:
                    showFragment(transaction, new HomePageFragment());
                    break;
                case R.id.rb_tab2:
                    showFragment(transaction, new ColleagueFragment());
                    break;
                case R.id.rb_tab4:
                    showFragment(transaction, new TopicFragment());
                    break;
                case R.id.rb_tab5:
                    showFragment(transaction, new MeFragment());
                    break;
            }
        });
    }

    public void showFragment(FragmentTransaction transaction, Fragment fragment) {
        transaction.replace(R.id.rl_fragment, fragment);
        transaction.commit();
    }

    private void setData() {
        mTvMonth.setText(DateUtils.getMonth());
        mTvDay.setText(DateUtils.getDayOfMonth());
        mTvWeek.setText(DateUtils.getDayOfWeek());
        mLunarMonth.setText(DateUtils.getLunarMonth());
        mLunarDay.setText(DateUtils.getLunarDayOfMonth());
    }

    private void initData() {

        EventBus.getDefault().register(this);
        mToolbar.setVisibility(View.GONE);

        mSupportFragmentManager = getSupportFragmentManager();

        //  显示默认Fragment
        FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
        showFragment(transaction, new HomePageFragment());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isPopWindowShow()) {
                hidePopWindow();
                return true;
            }
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe
    public void getExitEvent(SettingActivity.Exit exit) {
        finish();
    }

    @OnClick({R.id.iv_main_close, R.id.rl_main, R.id.ll_edit_task, R.id.ll_main_payroll,
            R.id.ll_main_write_topic, R.id.ll_add_user, R.id.ll_delete_user, R.id.ll_rest_user})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main_close:
                hidePopWindow();
                break;
            case R.id.rl_main:
                break;
            case R.id.ll_edit_task:
                EditTaskActivity.start(this, "添加任务");
                hidePopWindow();
                break;
            case R.id.ll_main_payroll:
                startActivity(new Intent(this, QuerySalaryActivity.class));
                hidePopWindow();
                break;
            case R.id.ll_main_write_topic:
                startActivity(new Intent(this, WriteTopicActivity.class));
                hidePopWindow();
                break;
            case R.id.ll_add_user:
                RegisterActivity.start(this);
                hidePopWindow();
                break;
            case R.id.ll_delete_user:
                DeleteUserActivity.start(this);
                hidePopWindow();
                break;
            case R.id.ll_rest_user:
                ResetUserActivity.start(this);
                hidePopWindow();
                break;
        }
    }

    //  隐藏弹窗
    public void hidePopWindow() {
        mCheckBox.setChecked(false);
    }
    //  弹窗是否显示
    public boolean isPopWindowShow() {
        return mCheckBox.isChecked();
    }

    public static void start(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }
}
