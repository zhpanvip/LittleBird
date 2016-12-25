package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import project.graduate.zhpan.littlebird.R;

public class WriteTopicActivity extends BaseActivity implements View.OnClickListener {

    private EditText mTvTopic;
    private ImageView mIvAddPic;
    private Button mBtnPublish;
    private LinearLayout mActivityWriteTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_topic);
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
        String topic = mTvTopic.getText().toString().trim();
        if (TextUtils.isEmpty(topic)) {
            Toast.makeText(this, "写点什么吧...", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
