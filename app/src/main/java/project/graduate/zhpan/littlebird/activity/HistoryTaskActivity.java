package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import project.graduate.zhpan.littlebird.R;

public class HistoryTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task);
        setData();
    }

    private void setData() {
        mTvTitle.setText("历史任务");
    }
}
