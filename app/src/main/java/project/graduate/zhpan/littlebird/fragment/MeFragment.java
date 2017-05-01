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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.airong.core.utils.ImageLoaderUtil;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.EncourageActivity;
import project.graduate.zhpan.littlebird.activity.IntegralActivity;
import project.graduate.zhpan.littlebird.activity.SettingActivity;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.adapter.MeAdapter;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.JsonFileReader;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;

/**
 * Created by zhpan on 2016/10/15.
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_back) ImageView mIvBack;
    @BindView(R.id.toolbar_title) TextView mTvTitle;
    @BindView(R.id.iv_me_head_picture) ImageView imageView;
    @BindView(R.id.tv_name) TextView mTvName;
    @BindView(R.id.iv_me_rank) ImageView mIvGrade;
    @BindView(R.id.tv_integral) TextView mTvInt;
    @BindView(R.id.gv_me) GridView mGridView;

    private ColleagueBean.EmployeeListBean mProfile;
    private String[] mText = {"资料", "等级", "积分", "奖励", "规划", "设置"};
    private int[] images = {R.drawable.me_data, R.drawable.me_grade, R.drawable.me_integration, R.drawable.me_equity, R.drawable.me_plan, R.drawable.me_set};
    private UserBean userBean;
    private SpannableStringBuilder spannable;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init() {
        initData();
        setData();
        setListener();
    }

    private void setData() {
        mTvName.setText(userBean.getRealName());

        spannable = new SpannableStringBuilder("当前积分" + userBean.getIntegral() + "分");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff5656"));
        spannable.setSpan(colorSpan, 4, 4 + (userBean.getIntegral() + "").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvInt.setText(spannable);

        int rank = userBean.getRank();
        switch (rank) {
            case 1:
                mIvGrade.setImageResource(R.drawable.me_grade_one);
                break;
            case 2:
                mIvGrade.setImageResource(R.drawable.me_grade_two);
                break;
            case 3:
                mIvGrade.setImageResource(R.drawable.me_grade_three);
                break;
            case 4:
                mIvGrade.setImageResource(R.drawable.me_grade_four);
                break;
            case 5:
                mIvGrade.setImageResource(R.drawable.me_grade_five);
                break;
        }

    }

    private void setListener() {
        mGridView.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long l) -> {
            switch (position) {
                case 0:
                    UserInfoActivity.start(getActivity(), "个人简介", userBean);
                    break;
                case 1:
                    showMyDialog();
                    break;
                case 2:
                    startActivity(new Intent(getContext(), IntegralActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getContext(), EncourageActivity.class));
                    break;
                case 4:
                    showMyDialog();
                    break;
                case 5:
                    startActivity(new Intent(getContext(), SettingActivity.class));
                    break;
            }
        });
    }

    private void showMyDialog() {
        showOneButtonDialog("敬请期待", "确定", (v) ->
                dismissDialog());
    }

    private void initData() {
        userBean = DataSupport.where("email=?", UserInfoTools.getEmail(getContext())).find(UserBean.class).get(0);
        mTvTitle.setText("LittleBird");
        mIvBack.setVisibility(View.GONE);
        MeAdapter adapter = new MeAdapter(getContext(), mText, images);
        mGridView.setAdapter(adapter);
        String profileJson = JsonFileReader.getJson(getContext(), "profile.json");
        Gson gson = new Gson();
        mProfile = gson.fromJson(profileJson, ColleagueBean.EmployeeListBean.class);

        ImageLoaderUtil.loadCircleImg(imageView,userBean.getHeadPic());

    }
}
