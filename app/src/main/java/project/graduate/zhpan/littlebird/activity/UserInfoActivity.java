package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.bean.UserBean;

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
    private ImageView mIvGrade;
    private LinearLayout mActivityUserInfo;

    private  UserBean userBean;

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
            Bundle bundle = intent.getBundleExtra("bundle");
            String title = bundle.getString("title");
            userBean = (UserBean) bundle.getSerializable("userBean");
            mTvTitle.setText(title);
        }

        mTvName.setText(userBean.getRealName());
        mTvTelNo.setText(userBean.getTel());
        mTvEMail.setText(userBean.getEmail());
        mTvDuties.setText(userBean.getJob());
        mTvTime.setText(userBean.getEntryTime());
        mTvWhere.setText(userBean.getDepartment());
        mTvIntegral.setText("当前积分"+ userBean.getIntegral()+"分");
        int rank = userBean.getRank();
        switch (rank){
            case 1:
                mIvGrade.setImageResource(R.drawable.me_grade_one);
                break;
            case 2:
                mIvGrade.setImageResource(R.drawable.me_grade_two);
                break;
            case 3:
                mIvGrade.setImageResource(R.drawable.me_grade_three);
                break;
            case 4:
                mIvGrade.setImageResource(R.drawable.me_grade_four);
                break;
            case 5:
                mIvGrade.setImageResource(R.drawable.me_grade_five);
                break;
        }
    }

    public static void start(Context context, String title, UserBean userBean) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("userBean",userBean);
        bundle.putString("title",title);
        intent.putExtra("bundle",bundle);
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
        mIvGrade= (ImageView) findViewById(R.id.iv_grade);
        mActivityUserInfo = (LinearLayout) findViewById(R.id.activity_user_info);
    }
}
