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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.CheckActivity;
import project.graduate.zhpan.littlebird.activity.NoticeActivity;
import project.graduate.zhpan.littlebird.activity.ProjectActivity;
import project.graduate.zhpan.littlebird.activity.RankActivity;
import project.graduate.zhpan.littlebird.activity.SignActivity;
import project.graduate.zhpan.littlebird.activity.TaskActivity;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;

/**
 * Created by zhpan on 2016/10/15.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imageView;
    private LinearLayout mLlSign;
    private LinearLayout mLlTask;
    private LinearLayout mLlCheck;
    private LinearLayout mLlRank;
    private LinearLayout mLlProject;
    private LinearLayout mLlNotice;
    private TextView mTvInt;
    private TextView mTvIntYesterday;
    private UserBean userBean;
    SpannableStringBuilder spannable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_page, null);
        initView();
        initData();
        setData();
        setListener();
        return mView;
    }

    private void initData() {
        userBean = DataSupport.where("email=?", UserInfoTools.getEmail(getContext())).find(UserBean.class).get(0);
    }


    private void setListener() {
        mLlSign.setOnClickListener(this);
        mLlTask.setOnClickListener(this);
        mLlCheck.setOnClickListener(this);
        mLlRank.setOnClickListener(this);
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
        long timeStamp=System.currentTimeMillis()-24*60*60*1000;
        List<IntegralBean> integralBeen = DataSupport.where("date=? and email=?", DateUtils.stampToDate(timeStamp), UserInfoTools.getEmail(getContext())).find(IntegralBean.class);
        int integral=integralBeen.size()>0 ? integralBeen.get(0).getIntegral():0;
        spannable = new SpannableStringBuilder("昨日积分" + integral + "分");

        spannable.setSpan(colorSpan, 4, 4 + (userBean.getYesterdayInt() + "").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvIntYesterday.setText(spannable);
        //  加载头像
        Glide.with(getContext()).load(userBean.getHeadPic())
                .into(imageView);
        Glide.with(this).load(userBean.getHeadPic())
                .transform(new GlideCircleTransform(getContext()))
                .placeholder(R.drawable.ic_home_avatar)
                .into(imageView);
    }

    private void initView() {
        mTvTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mIvBack = (ImageView) mView.findViewById(R.id.iv_back);
        //mGrideView= (GridView) mView.findViewById(R.id.gv_home_page);
        mLlSign = (LinearLayout) mView.findViewById(R.id.ll_homepage_sign);
        mLlTask = (LinearLayout) mView.findViewById(R.id.ll_homepage_task);
        mLlCheck = (LinearLayout) mView.findViewById(R.id.ll_homepage_check);
        mLlRank = (LinearLayout) mView.findViewById(R.id.ll_homepage_ranking);
        mLlProject = (LinearLayout) mView.findViewById(R.id.ll_homepage_project);
        mLlNotice = (LinearLayout) mView.findViewById(R.id.ll_homepage_notice);
        mTvInt = (TextView) mView.findViewById(R.id.tv_integral);
        mTvIntYesterday = (TextView) mView.findViewById(R.id.tv_integral_yesterday);
        imageView = (ImageView) mView.findViewById(R.id.iv_homepage_head_picture);
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
