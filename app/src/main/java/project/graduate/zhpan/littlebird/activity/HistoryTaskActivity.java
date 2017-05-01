package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import project.graduate.zhpan.littlebird.R;

public class HistoryTaskActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_task;
    }

    @Override
    protected void init() {
        setData();
    }

    private void setData() {
        mTvTitle.setText("历史任务");
    }
}
