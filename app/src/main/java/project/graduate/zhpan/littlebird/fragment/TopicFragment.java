package project.graduate.zhpan.littlebird.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.activity.WriteTopicActivity;
import project.graduate.zhpan.littlebird.adapter.TopicAdapter;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.bean.CommentBean;
import project.graduate.zhpan.littlebird.bean.LikeBean;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.KeyboardWatcher;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;
import project.graduate.zhpan.littlebird.view.ListViewForScrollView;

/**
 * Created by zhpan on 2016/10/15.
 */

public class TopicFragment extends BaseFragment implements View.OnClickListener, TopicAdapter.CommentCallBack, KeyboardWatcher.OnKeyboardToggleListener {
    private ImageView imageView;
    private TextView mTvName;
    private TextView mTvSign;
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
    private List<UserBean> userBeen;
    private List<TopicBean> topicBeen;
    private int commentPosition;    //  评论的topic的position

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_topic, null);
        initView();
        initData();
        setData();
        setListener();
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initData() {
        userBeen = DataSupport.where("email=?", UserInfoTools.getEmail(getContext())).find(UserBean.class);
        topicBeen = DataSupport.findAll(TopicBean.class, true);
        for (int i = 0; i < topicBeen.size(); i++) {
            int view = topicBeen.get(i).getView();
            topicBeen.get(i).setView(++view);
            topicBeen.get(i).save();
        }
        EventBus.getDefault().register(this);
    }

    private void setListener() {
        mLinearLayout.setOnClickListener(this);
        mTvComment.setOnClickListener(this);
    }

    private void setData() {
        //  设置用信息
        Glide.with(this).load(userBeen.get(0).getHeadPic())
                .transform(new GlideCircleTransform(getContext()))
                .placeholder(R.drawable.ic_home_avatar)
                .into(imageView);
        mTvName.setText(userBeen.get(0).getRealName());
        mTvSign.setText(userBeen.get(0).getPersonalSign());

        //  适配器
        adapter = new TopicAdapter(getActivity(), this);
        adapter.setList(topicBeen);
        mListView.setAdapter(adapter);

        keyboardWatcher = new KeyboardWatcher(getActivity());
        keyboardWatcher.setListener(this);

        //  设置ToolBar信息
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
        //  给ListView添加footer
        footerView = View.inflate(getActivity(), R.layout.footer_view, null);
        mListView.addFooterView(footerView, null, false);
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
        imageView = (ImageView) mView.findViewById(R.id.head_img);
        mTvName = (TextView) mView.findViewById(R.id.tv_name);
        mTvSign = (TextView) mView.findViewById(R.id.tv_sign);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main:
                UserInfoActivity.start(getActivity(), "个人简介", new UserBean());
                break;
            case R.id.tv_publish:   //发表评论
                publishClickListener(mEtComment.getText().toString());
                break;
        }
    }

    @Override
    public void onCommentBtnClickListener(int position) {
        mEtComment.requestFocus();
        showKeyboard();
        commentPosition = position;
    }

    @Override
    public void onPraiseClickListener(int position) {
        List<TopicBean> list = adapter.getmList();
        TopicBean topicBean = list.get(position);
        List<LikeBean> like = topicBean.getLike();
        if (!isParse(like)) {
            LikeBean likeBean = new LikeBean();
            likeBean.setTopicBean(topicBean);
            likeBean.setEmail(UserInfoTools.getEmail(getContext()));
            likeBean.setName(UserInfoTools.getRealName(getContext()));

            if (likeBean.save()) {
                topicBeen = DataSupport.findAll(TopicBean.class, true);
                adapter.setList(topicBeen);
                adapter.notifyDataSetChanged();
                mEtComment.setText("");
                /**
                 *  隐藏软键盘
                 */
                InputMethodManager imm = (InputMethodManager) mEtComment.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);

                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(mEtComment.getApplicationWindowToken(), 0);
                }
            }
        }

    }

    /**
     * 发表评论
     * @param content 评论内容
     */
    public void publishClickListener(String content) {
        List<TopicBean> list = adapter.getmList();
        TopicBean topicBean = topicBeen.get(commentPosition);
        CommentBean commentBean = new CommentBean();
        commentBean.setContent(content);
        commentBean.setName(UserInfoTools.getRealName(getContext()));
        commentBean.setTopicBean(topicBean);
        commentBean.setEmail(UserInfoTools.getEmail(getContext()));
        if (commentBean.save()) {
            topicBeen = DataSupport.findAll(TopicBean.class, true);
            adapter.setList(topicBeen);
            adapter.notifyDataSetChanged();
        }
    }

    //  是否攒过该条topic
    public boolean isParse(List<LikeBean> like) {
        for (int i = 0; i < like.size(); i++) {
            if (like.get(i).getEmail().equals(UserInfoTools.getEmail(getContext()))) {
                return true;
            }
        }
        return false;
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

    @Subscribe
    public void onTopicPublishSuccess(WriteTopicActivity.TopicSendSuccess topicSendSuccess) {
        topicBeen = DataSupport.findAll(TopicBean.class, true);
        for (int i = 0; i < topicBeen.size(); i++) {
            int view = topicBeen.get(i).getView();
            topicBeen.get(i).setView(++view);
            topicBeen.get(i).save();
        }
        adapter.setList(topicBeen);
        adapter.notifyDataSetChanged();
    }
}
