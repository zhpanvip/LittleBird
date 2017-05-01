package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class WriteTopicActivity extends BaseActivity {

    @BindView(R.id.tv_topic)
    EditText mTvTopic;
    @BindView(R.id.iv_add_pic)
    ImageView mIvAddPic;
    @BindView(R.id.btn_publish)
    Button mBtnPublish;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_write_topic;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mTvTitle.setText("发话题");
    }

    private void submit() {
        String content = mTvTopic.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "写点什么吧...", Toast.LENGTH_SHORT).show();
            return;
        }
        TopicBean topicBean = new TopicBean();
        topicBean.setContent(mTvTopic.getText().toString());
        topicBean.setEmail(UserInfoTools.getEmail(this));
        topicBean.setDate(System.currentTimeMillis());
        if (topicBean.save()) {
            Toast.makeText(this, "发表成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new TopicSendSuccess());
            finish();
        } else {
            Toast.makeText(this, "发表话题失败，请重试！", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.btn_publish)
    public void onViewClicked() {
        submit();
    }

    public class TopicSendSuccess {
    }
}
