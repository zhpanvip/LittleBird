package project.graduate.zhpan.littlebird.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;
import project.graduate.zhpan.littlebird.adapter.TopicAdapter;
import project.graduate.zhpan.littlebird.utils.KeyboardWatcher;
import project.graduate.zhpan.littlebird.view.ListViewForScrollView;

import static android.R.attr.keyHeight;

/**
 * Created by zhpan on 2016/10/15.
 */

public class TopicFragment extends BaseFragment implements View.OnClickListener, TopicAdapter.CommentCallBack, KeyboardWatcher.OnKeyboardToggleListener {
    private TopicAdapter adapter;
    private ListViewForScrollView mListView;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private LinearLayout mLinearLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LinearLayout mLlComment;
    private EditText mEtComment;
    private TextView mTvComment;
    private InputMethodManager inputManager;
    private KeyboardWatcher keyboardWatcher;
    private View footerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_topic, null);
        initView();
        setData();
        setListener();
        return mView;

    }

    private void setListener() {
        mLinearLayout.setOnClickListener(this);
    }

    private void setData() {
        keyboardWatcher = new KeyboardWatcher(getActivity());
        keyboardWatcher.setListener(this);
        adapter = new TopicAdapter(getActivity(), this);
        mListView.setAdapter(adapter);
        mCollapsingToolbarLayout.setTitle("LittleBird");
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
        mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.LEFT | Gravity.BOTTOM);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -(2 * mLinearLayout.getHeight()) / 3) {
                    mCollapsingToolbarLayout.setTitle("LittleBird");
                } else {
                    mCollapsingToolbarLayout.setTitle("");
                }
            }
        });

        footerView=View.inflate(getActivity(),R.layout.footer_view,null);
        mListView.addFooterView(footerView,null,false);

    }

    private void initView() {
        mListView = (ListViewForScrollView) mView.findViewById(R.id.lv_topic);
        mAppBarLayout = (AppBarLayout) mView.findViewById(R.id.apl_main);
        mToolbar = (Toolbar) mView.findViewById(R.id.tb_main);
        mLlComment = (LinearLayout) mView.findViewById(R.id.ll_comment);
        mTvComment = (TextView) mView.findViewById(R.id.tv_publish);
        mEtComment = (EditText) mView.findViewById(R.id.et_comment);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.ll_main);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) mView.findViewById(R.id.ctl_main);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main:
                UserInfoActivity.start(getActivity(), "个人简介");
                break;
        }
    }

    @Override
    public void onCommentBtnClickListener(int position) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        mEtComment.requestFocus();
        showKeyboard();
    }

    //  弹出软键盘
    protected void showKeyboard() {
        inputManager = (InputMethodManager) mEtComment.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(mEtComment, 0);
       /* Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                inputManager.showSoftInput(mEtComment, 0);
            }
        }, 500);*/
    }

    @Override
    public void onKeyboardShown(int keyboardSize) {
        mLlComment.setVisibility(View.VISIBLE);
    }

    @Override
    public void onKeyboardClosed() {
        mLlComment.setVisibility(View.GONE);
    }
}
