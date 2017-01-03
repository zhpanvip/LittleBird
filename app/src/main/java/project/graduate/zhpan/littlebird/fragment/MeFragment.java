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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.EncourageActivity;
import project.graduate.zhpan.littlebird.activity.IntegralActivity;
import project.graduate.zhpan.littlebird.activity.SettingActivity;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.adapter.MeAdapter;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.bean.ProfileBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.JsonFileReader;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;

/**
 * Created by zhpan on 2016/10/15.
 */

public class MeFragment extends BaseFragment {
    private ImageView imageView;
    private TextView mTvName;
    private TextView mTvInt;
    private ImageView mIvGrade;
    private GridView mGridView;
    private ColleagueBean.EmployeeListBean mProfile;
    private String[] mText = {"资料", "等级", "积分", "奖励", "规划", "设置"};
    private int[] images = {R.drawable.me_data, R.drawable.me_grade, R.drawable.me_integration, R.drawable.me_equity, R.drawable.me_plan, R.drawable.me_set};
    private UserBean userBean;
    private SpannableStringBuilder spannable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, null);
        initView();
        initData();
        setData();
        setListener();
        return mView;
    }

    private void setData() {
        mTvName.setText(userBean.getRealName());

        spannable = new SpannableStringBuilder("当前积分" + userBean.getIntegral() + "分");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff5656"));
        spannable.setSpan(colorSpan, 4, 4 + (userBean.getIntegral() + "").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvInt.setText(spannable);

        int rank = userBean.getRank();
        switch (rank){
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
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        UserInfoActivity.start(getActivity(),"个人简介",userBean);
                        break;
                    case 1:

                        break;
                    case 2:
                        startActivity(new Intent(getContext(), IntegralActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), EncourageActivity.class));
                        break;
                    case 4:

                        break;
                    case 5:
                        startActivity(new Intent(getContext(), SettingActivity.class));
                        break;
                }
            }
        });
    }

    private void showDialog(String content){

    }
    private void initData() {
        userBean= DataSupport.where("email=?", UserInfoTools.getEmail(getContext())).find(UserBean.class).get(0);
        mTvTitle.setText("LittleBird");
        mIvBack.setVisibility(View.GONE);
        MeAdapter adapter = new MeAdapter(getContext(), mText, images);
        mGridView.setAdapter(adapter);
        String profileJson = JsonFileReader.getJson(getContext(), "profile.json");
        Gson gson=new Gson();
        mProfile = gson.fromJson(profileJson, ColleagueBean.EmployeeListBean.class);

        Glide.with(this).load(userBean.getHeadPic())
                .transform(new GlideCircleTransform(getContext()))
                .placeholder(R.drawable.ic_home_avatar)
                .into(imageView);
    }

    private void initView() {
        mTvTitle= (TextView) mView.findViewById(R.id.toolbar_title);
        mIvBack= (ImageView) mView.findViewById(R.id.iv_back);
        mGridView = (GridView) mView.findViewById(R.id.gv_me);
        mTvName= (TextView) mView.findViewById(R.id.tv_name);
        mTvInt= (TextView) mView.findViewById(R.id.tv_integral);
        mIvGrade= (ImageView) mView.findViewById(R.id.iv_me_rank);
        imageView= (ImageView) mView.findViewById(R.id.iv_me_head_picture);
    }
}
