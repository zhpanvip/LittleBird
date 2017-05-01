package com.airong.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airong.core.view.CustomProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseCoreFragment extends Fragment implements BaseImpl{
    public View mView;
    public LayoutInflater inflater;
    public ImageView mIvLeft;
    public ImageView mIvBack;
    public TextView mTvTitle;

    //  加载进度的dialog
    private CustomProgressDialog mProgressDialog;
    private Unbinder bind;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater=inflater;
        mProgressDialog = CustomProgressDialog.createDialog(getContext());
        mProgressDialog.setCanceledOnTouchOutside(false);
        if (mView == null) {
            mView = inflater.inflate(this.getLayoutId(), container, false);
            bind = ButterKnife.bind(this, mView);
            init();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }
    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    /**
     * 取消ProgressDialog
     */
    @Override
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
    protected abstract int getLayoutId();

    protected abstract void init();
}
