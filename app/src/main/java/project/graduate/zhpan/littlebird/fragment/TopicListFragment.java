package project.graduate.zhpan.littlebird.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.activity.WriteTopicActivity;
import project.graduate.zhpan.littlebird.adapter.TopicAdapter;
import project.graduate.zhpan.littlebird.adapter.TopicListAdapter;
import project.graduate.zhpan.littlebird.bean.CommentBean;
import project.graduate.zhpan.littlebird.bean.LikeBean;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.KeyboardWatcher;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;
import project.graduate.zhpan.littlebird.view.RecyclerViewForScrollView;

/**
 * Created by zhpan on 2017/1/5.
 */

public class TopicListFragment extends BaseFragment implements View.OnClickListener, TopicAdapter.CommentCallBack, KeyboardWatcher.OnKeyboardToggleListener {
    private TopicListAdapter adapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mLlComment;
    private EditText mEtComment;
    private TextView mTvComment;
    private InputMethodManager inputManager;
    private KeyboardWatcher keyboardWatcher;
    private List<UserBean> userBeen;
    private List<TopicBean> topicBeen;
    private int commentPosition;    //  评论的topic的position

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic_list;
    }

    @Override
    protected void init() {
        initView();
        initData();
        setData();
        setListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //  用来获取MallFragment中解析接送得到的对象
    public static TopicListFragment getInstance() {
        TopicListFragment fragment = new TopicListFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    private void setListener() {
        mTvComment.setOnClickListener(this);
    }

    private void setData() {
        //  适配器
        /*adapter = new TopicAdapter(getActivity(), this);
        adapter.setList(topicBeen);
        mRecyclerView.setAdapter(adapter);*/
        adapter=new TopicListAdapter(getContext(),this);
        adapter.setList(topicBeen);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);


        keyboardWatcher = new KeyboardWatcher(getActivity());
        keyboardWatcher.setListener(this);
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

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.lv_topic);
        mLlComment = (LinearLayout) mView.findViewById(R.id.ll_comment);
        mTvComment = (TextView) mView.findViewById(R.id.tv_publish);
        mEtComment = (EditText) mView.findViewById(R.id.et_comment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
        List<TopicBean> list = adapter.getList();
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
            }
        }

    }

    /**
     * 发表评论
     * @param content 评论内容
     */
    public void publishClickListener(String content) {
        List<TopicBean> list = adapter.getList();
        TopicBean topicBean = topicBeen.get(commentPosition);
        CommentBean commentBean = new CommentBean();
        commentBean.setContent(content);
        commentBean.setName(UserInfoTools.getRealName(getContext()));
        commentBean.setTopicBean(topicBean);
        commentBean.setEmail(UserInfoTools.getEmail(getContext()));
        if (commentBean.save()) {
            mEtComment.setText("");
            /**
             *  隐藏软键盘
             */
            InputMethodManager imm = (InputMethodManager) mEtComment.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);

            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mEtComment.getApplicationWindowToken(), 0);
            }
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
