package project.graduate.zhpan.littlebird.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.EncourageActivity;
import project.graduate.zhpan.littlebird.activity.IntegralActivity;
import project.graduate.zhpan.littlebird.activity.SettingActivity;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.adapter.MeAdapter;
import project.graduate.zhpan.littlebird.utils.SideBar;

/**
 * Created by zhpan on 2016/10/15.
 */

public class MeFragment extends BaseFragment {
    private GridView mGridView;
    private String[] mText = {"资料", "等级", "积分", "奖励", "规划", "设置"};
    private int[] images = {R.drawable.me_data, R.drawable.me_grade, R.drawable.me_integration, R.drawable.me_equity, R.drawable.me_plan, R.drawable.me_set};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, null);
        initView();
        initData();
        setListener();
        return mView;
    }

    private void setListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        UserInfoActivity.start(getActivity(),"个人简介");
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
        mTvTitle.setText("LittleBird");
        mIvBack.setVisibility(View.GONE);
        MeAdapter adapter = new MeAdapter(getContext(), mText, images);
        mGridView.setAdapter(adapter);
    }

    private void initView() {
        mTvTitle= (TextView) mView.findViewById(R.id.toolbar_title);
        mIvBack= (ImageView) mView.findViewById(R.id.iv_back);
        mGridView = (GridView) mView.findViewById(R.id.gv_me);
    }
}
