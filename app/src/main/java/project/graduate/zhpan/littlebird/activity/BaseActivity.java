package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import project.graduate.zhpan.littlebird.R;

public class BaseActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;//把父类activity和子类activity的view都add到这里
    public ImageView mIvBack;
    public TextView mTvTitle;
    public ImageView mIvRight;
    public TextView mTvRight;
    public Toolbar mToolbar;
    public LinearLayout mLinearLayout;
    public TextView mTvItem1;
    public TextView mTvItem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.tool_bar_layout);

        findView();
        setListener();

    }

    private void setListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findView() {
        mIvBack= (ImageView) findViewById(R.id.iv_back);
        mTvTitle= (TextView) findViewById(R.id.toolbar_title);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mIvRight= (ImageView) findViewById(R.id.iv_right);
        mTvRight= (TextView) findViewById(R.id.tv_right);
        mLinearLayout= (LinearLayout) findViewById(R.id.ll_select);
        mTvItem1= (TextView) findViewById(R.id.tv_item1);
        mTvItem2= (TextView) findViewById(R.id.tv_item2);
    }

    /**
     * 初始化contentview
     */
    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);

    }

    @Override
    public void setContentView(View view) {

        parentLinearLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {

        parentLinearLayout.addView(view, params);

    }


}
