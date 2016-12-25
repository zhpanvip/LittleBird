package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;

public class UserInfoActivity extends BaseActivity {

    private CircleImageView mIvHeadPic;
    private TextView mTvName;
    private TextView mTvGrade;
    private LinearLayout mLlGrade;
    private TextView mTvIntegral;
    private TextView mTvTelNo;
    private TextView mTvEMail;
    private TextView mTvDuties;
    private TextView mTvTime;
    private TextView mTvWhere;
    private TextView mTvPart;
    private LinearLayout mActivityUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        setData();

    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            mTvTitle.setText(title);
        }
    }

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    private void initView() {
        mIvHeadPic = (CircleImageView) findViewById(R.id.iv_head_pic);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvGrade = (TextView) findViewById(R.id.tv_grade);
        mLlGrade = (LinearLayout) findViewById(R.id.ll_grade);
        mTvIntegral = (TextView) findViewById(R.id.tv_integral);
        mTvTelNo = (TextView) findViewById(R.id.tv_tel_no);
        mTvEMail = (TextView) findViewById(R.id.tv_e_mail);
        mTvDuties = (TextView) findViewById(R.id.tv_duties);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvWhere = (TextView) findViewById(R.id.tv_where);
        mTvPart = (TextView) findViewById(R.id.tv_part);
        mActivityUserInfo = (LinearLayout) findViewById(R.id.activity_user_info);
    }
}
