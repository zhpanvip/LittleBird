package project.graduate.zhpan.littlebird.activity;


import project.graduate.zhpan.littlebird.R;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void init() {
        mTvTitle.setText("关于我们");
    }
}
