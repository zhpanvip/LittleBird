package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.bean.LittleBirldBean;

/**
 * Created by zhpan on 2016/12/18.
 */

public class IntegralAdapter extends LittleBirdAdapter {
    Context mContext;

    public IntegralAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<IntegralBean> getmList() {
        return mList;
    }

    public void setmList(List<IntegralBean> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.item_integral, null);
            holder=new ViewHolder(convertView);
           convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mIvDate;
        public TextView mTvCount;
        public TextView mTvHowGet;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvDate = (TextView) rootView.findViewById(R.id.iv_date);
            this.mTvCount = (TextView) rootView.findViewById(R.id.tv_count);
            this.mTvHowGet = (TextView) rootView.findViewById(R.id.tv_how_get);
        }

    }
}
