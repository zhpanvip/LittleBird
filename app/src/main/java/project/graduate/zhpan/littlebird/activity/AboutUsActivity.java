package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;

import project.graduate.zhpan.littlebird.R;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mTvTitle.setText("关于我们");
    }
}
