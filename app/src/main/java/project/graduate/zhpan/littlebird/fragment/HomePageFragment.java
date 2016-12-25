package project.graduate.zhpan.littlebird.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.CheckActivity;
import project.graduate.zhpan.littlebird.activity.NoticeActivity;
import project.graduate.zhpan.littlebird.activity.ProjectActivity;
import project.graduate.zhpan.littlebird.activity.RankActivity;
import project.graduate.zhpan.littlebird.activity.SignActivity;
import project.graduate.zhpan.littlebird.activity.TaskActivity;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;

/**
 * Created by zhpan on 2016/10/15.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener{
    private GridView mGrideView;
    private LinearLayout mLlSign;
    private LinearLayout mLlTask;
    private LinearLayout mLlCheck;
    private LinearLayout mLlRank;
    private LinearLayout mLlProject;
    private LinearLayout mLlNotice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_home_page,null);

        initView();
        setData();
        setListener();
        return mView;

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
        ColleagueAdapter adapter=new ColleagueAdapter(getContext());
       //mGrideView.setAdapter(adapter);
    }

    private void initView() {
        mTvTitle= (TextView) mView.findViewById(R.id.toolbar_title);
        mIvBack= (ImageView) mView.findViewById(R.id.iv_back);
        //mGrideView= (GridView) mView.findViewById(R.id.gv_home_page);
        mLlSign= (LinearLayout) mView.findViewById(R.id.ll_homepage_sign);
        mLlTask= (LinearLayout) mView.findViewById(R.id.ll_homepage_task);
        mLlCheck= (LinearLayout) mView.findViewById(R.id.ll_homepage_check);
        mLlRank= (LinearLayout) mView.findViewById(R.id.ll_homepage_ranking);
        mLlProject= (LinearLayout) mView.findViewById(R.id.ll_homepage_project);
        mLlNotice= (LinearLayout) mView.findViewById(R.id.ll_homepage_notice);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.ll_homepage_sign:
                intent=new Intent(getContext(), SignActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_task:
                intent=new Intent(getContext(), TaskActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_check:
                intent=new Intent(getContext(), CheckActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_ranking:
                intent=new Intent(getContext(), RankActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_project:
                intent=new Intent(getContext(), ProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_homepage_notice:
                intent=new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
