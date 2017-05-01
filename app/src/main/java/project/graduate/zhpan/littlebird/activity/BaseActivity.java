package project.graduate.zhpan.littlebird.activity;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airong.core.BaseRxActivity;
import com.airong.core.view.CustomDialog;

import project.graduate.zhpan.littlebird.R;

public abstract class BaseActivity extends BaseRxActivity {
    private LinearLayout parentLinearLayout;//把父类activity和子类activity的view都add到这里
    public ImageView mIvBack;
    public TextView mTvTitle;
    public ImageView mIvRight;
    public TextView mTvRight;
    public Toolbar mToolbar;
    public LinearLayout mLinearLayout;
    public TextView mTvItem1;
    public TextView mTvItem2;

    //  custom dialog
    private CustomDialog dialog;
    //  the view of dialog
    private View dialogView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.tool_bar_layout);
        setContentView(getLayoutId());
        findView();
        setListener();
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

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


    /**
     * @param content         内容
     * @param confirm         确定键文字
     * @param cancel          取消键文字
     * @param confirmListener 确定键监听
     * @param cancelListener  取消键监听
     */
    @Override
    public void showTwoButtonDialog(String content, String confirm, String cancel,
                                    View.OnClickListener confirmListener,
                                    View.OnClickListener cancelListener) {
        dialog = new CustomDialog.Builder(this)
                .setTheme(com.airong.core.R.style.IdeaDialog)
                .setContent(content)
                .addConfirmClickListener(confirm, confirmListener)
                .addCancelClickListener(cancel, cancelListener)
                .build();
        dialog.show();
    }

    /**
     * @param content         内容
     * @param confirm         确定键文字
     * @param cancel          取消键文字
     * @param confirmColor    确定键颜色
     * @param cancelColor     取消键颜色
     * @param confirmListener 确定键监听
     * @param cancelListener  取消键监听
     */
    @Override
    public void showTwoButtonDialog(String content, String confirm, String cancel,
                                    @ColorInt int confirmColor, @ColorInt int cancelColor,
                                    View.OnClickListener confirmListener,
                                    View.OnClickListener cancelListener) {
        dialog = new CustomDialog.Builder(this)
                .setTheme(com.airong.core.R.style.IdeaDialog)
                .setContent(content)
                .setConfirmColor(confirmColor)
                .setCancelColor(cancelColor)
                .addConfirmClickListener(confirm, confirmListener)
                .addCancelClickListener(cancel, cancelListener)
                .build();
        dialog.show();
    }

    /**
     * @param content         内容
     * @param confirm         按钮文字
     * @param confirmListener 按钮监听
     */
    @Override
    public void showOneButtonDialog(String content, String confirm, View.OnClickListener confirmListener) {
        dialog = new CustomDialog.Builder(this)
                .setTheme(com.airong.core.R.style.IdeaDialog)
                .setContent(content)
                .addConfirmClickListener(confirm, confirmListener)
                .showOneButton()
                .build();
        dialog.show();
    }


    /**
     * create custom dialog
     * 可以定制任意的dialog样式
     * @param dialogLayoutRes    dialog布局资源文件
     * @param cancelTouchOutside 点击外部是否可以取消
     * @return
     */
    public View showCustomDialog(@LayoutRes Integer dialogLayoutRes, boolean cancelTouchOutside) {
        if (dialogLayoutRes == null) {
            dialogLayoutRes = com.airong.core.R.layout.custom_dialog;
        }
        dialogView = LayoutInflater.from(this).inflate(dialogLayoutRes, null);
        //  计算dialog宽高
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        dialogView.measure(measureSpec, measureSpec);
        int height = dialogView.getMeasuredHeight();
        int width = dialogView.getMeasuredWidth();

        dialog = new CustomDialog.Builder(this)
                .setTheme(com.airong.core.R.style.IdeaDialog)
                .setHeightPx(height)
                .setWidthPx(width)
                .cancelTouchOutside(cancelTouchOutside)
                .setDialogLayout(dialogView).build();
        dialog.show();
        return dialogView;
    }

    /**
     * 隐藏dialog
     */
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
