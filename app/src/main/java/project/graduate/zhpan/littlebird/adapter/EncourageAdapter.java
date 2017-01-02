package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.EncourageBean;
import project.graduate.zhpan.littlebird.bean.IntegralBean;

/**
 * Created by zhpan on 2016/12/18.
 */

public class EncourageAdapter extends LittleBirdAdapter {
    Context mContext;

    public EncourageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
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
        EncourageBean integralBean = (EncourageBean) mList.get(position);
        holder.mIvDate.setText(integralBean.getDate());
        if(integralBean.getIntegral()>0){
            holder.mTvCount.setText("+"+integralBean.getIntegral()+"积分");
            holder.mTvCount.setTextColor(Color.parseColor("#FF0000"));
        }else {
            holder.mTvCount.setText(integralBean.getIntegral()+"积分");
            holder.mTvCount.setTextColor(Color.parseColor("#42bd41"));
        }
        holder.mTvHowGet.setText(integralBean.getHowGet());
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
