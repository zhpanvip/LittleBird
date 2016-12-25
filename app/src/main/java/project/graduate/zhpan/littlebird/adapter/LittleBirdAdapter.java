package project.graduate.zhpan.littlebird.adapter;

import android.widget.BaseAdapter;

import java.util.List;

import project.graduate.zhpan.littlebird.bean.LittleBirldBean;

/**
 * Created by zhpan on 2016/11/20.
 */

public abstract class LittleBirdAdapter<TypeBean> extends BaseAdapter {
    public List<TypeBean> mList;

    public List<TypeBean> getmList() {
        return mList;
    }

    public void setList(List<TypeBean> mList) {
        this.mList = mList;
    }
}
