package project.graduate.zhpan.littlebird.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;
import com.airong.core.utils.ImageLoaderUtil;
import butterknife.BindView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.UserBean;

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.iv_head_pic)
    ImageView imageView;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_grade)
    ImageView mIvGrade;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.tv_tel_no)
    TextView mTvTelNo;
    @BindView(R.id.tv_e_mail)
    TextView mTvEMail;
    @BindView(R.id.tv_duties)
    TextView mTvDuties;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_where)
    TextView mTvWhere;
    @BindView(R.id.tv_part)
    TextView mTvPart;

    private SpannableStringBuilder spannable;

    private UserBean userBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void init() {
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

        //  加载头像
        ImageLoaderUtil.loadCircleImg(imageView,userBean.getHeadPic());

        mTvName.setText(userBean.getRealName());
        mTvTelNo.setText(userBean.getTel());
        mTvEMail.setText(userBean.getEmail());
        mTvDuties.setText(userBean.getJob());
        mTvTime.setText(userBean.getEntryTime());
        mTvWhere.setText(userBean.getProjectGroup());
        mTvPart.setText(userBean.getDepartment());

        spannable = new SpannableStringBuilder("当前积分" + userBean.getIntegral() + "分");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff5656"));
        spannable.setSpan(colorSpan, 4, 4 + (userBean.getIntegral() + "").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvIntegral.setText(spannable);


        int rank = userBean.getRank();
        switch (rank) {
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("userBean", userBean);
        bundle.putString("title", title);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

}
