package project.graduate.zhpan.littlebird.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class WriteTopicActivity extends BaseActivity implements View.OnClickListener {

    private EditText mTvTopic;
    private ImageView mIvAddPic;
    private Button mBtnPublish;
    private LinearLayout mActivityWriteTopic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_write_topic;
    }

    @Override
    protected void init() {
        initView();
        setData();
    }

    private void setData() {
        mTvTitle.setText("发话题");
    }

    private void initView() {
        mTvTopic = (EditText) findViewById(R.id.tv_topic);
        mIvAddPic = (ImageView) findViewById(R.id.iv_add_pic);
        mBtnPublish = (Button) findViewById(R.id.btn_publish);
        mActivityWriteTopic = (LinearLayout) findViewById(R.id.activity_write_topic);

        mBtnPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_publish:
                submit();
                break;
        }
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
        if(topicBean.save()){
            Toast.makeText(this, "发表成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new TopicSendSuccess());
            finish();
        }else {
            Toast.makeText(this, "发表话题失败，请重试！", Toast.LENGTH_SHORT).show();
        }
    }

    public class TopicSendSuccess{}
}
