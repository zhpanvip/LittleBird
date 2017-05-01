package project.graduate.zhpan.littlebird.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airong.core.utils.ImageLoaderUtil;
import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.CheckActivity;
import project.graduate.zhpan.littlebird.activity.NoticeActivity;
import project.graduate.zhpan.littlebird.activity.ProjectActivity;
import project.graduate.zhpan.littlebird.activity.RankActivity;
import project.graduate.zhpan.littlebird.activity.SignActivity;
import project.graduate.zhpan.littlebird.activity.TaskActivity;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;

/**
 * Created by zhpan on 2016/10/15.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_back) ImageView mIvBack;
    @BindView(R.id.toolbar_title) TextView mTvTitle;
    @BindView(R.id.tv_integral) TextView mTvInt;
    @BindView(R.id.tv_integral_yesterday) TextView mTvYesterday;
    @BindView(R.id.ll_homepage_sign) LinearLayout mLlSign;
    @BindView(R.id.ll_homepage_task) LinearLayout mLlTask;
    @BindView(R.id.ll_homepage_check) LinearLayout mLlCheck;
    @BindView(R.id.ll_homepage_ranking) LinearLayout mLlRanking;
    @BindView(R.id.ll_homepage_project) LinearLayout mLlProject;
    @BindView(R.id.ll_homepage_notice) LinearLayout mLlNotice;
    @BindView(R.id.iv_homepage_head_picture) ImageView imageView;

    private UserBean userBean;
    private SpannableStringBuilder spannable;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void init() {
        initData();
        setData();
        setListener();
    }

    private void initData() {
        userBean = DataSupport.where("email=?", UserInfoTools.getEmail(getContext())).find(UserBean.class).get(0);
    }


    private void setListener() {
        mLlSign.setOnClickListener(this);
        mLlTask.setOnClickListener(this);
        mLlCheck.setOnClickListener(this);
        mLlRanking.setOnClickListener(this);
        mLlProject.setOnClickListener(this);
        mLlNotice.setOnClickListener(this);
    }

    private void setData() {
        mTvTitle.setText("LittleBird");
        mIvBack.setVisibility(View.GONE);
        //ColleagueAdapter adapter=new ColleagueAdapter(getContext());
        spannable = new SpannableStringBuilder("当前积分" + userBean.getIntegral() + "分");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff5656"));
        spannable.setSpan(colorSpan, 4, 4 + (userBean.getIntegral() + "").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvInt.setText(spannable);

        //  获取昨日积分实体类
        long timeStamp = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
        List<IntegralBean> integralBeen = DataSupport.where("date=? and email=?", DateUtils.stampToDate(timeStamp), UserInfoTools.getEmail(getContext())).find(IntegralBean.class);
        int integral = integralBeen.size() > 0 ? integralBeen.get(0).getIntegral() : 0;
        spannable = new SpannableStringBuilder("昨日积分" + integral + "分");

        spannable.setSpan(colorSpan, 4, 4 + (userBean.getYesterdayInt() + "").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvYesterday.setText(spannable);
        //  加载头像
        ImageLoaderUtil.loadCircleImg(imageView,userBean.getHeadPic());
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_homepage_sign:
                intent = new Intent(getContext(), SignActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_task:
                intent = new Intent(getContext(), TaskActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_check:
                intent = new Intent(getContext(), CheckActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_ranking:
                intent = new Intent(getContext(), RankActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_project:
                intent = new Intent(getContext(), ProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_notice:
                intent = new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
