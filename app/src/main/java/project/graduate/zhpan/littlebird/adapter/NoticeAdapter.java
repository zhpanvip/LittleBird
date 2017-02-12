package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.NoticeBean;

/**
 * Created by zhpan on 2016/12/24.
 */

public class NoticeAdapter extends LittleBirdAdapter {
    Context mContext;

    public NoticeAdapter(Context mContext) {
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
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_notice, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        NoticeBean noticeBean = (NoticeBean) mList.get(position);
        holder.mTvContent.setText(noticeBean.getTitle());
        holder.mTvTime.setText(noticeBean.getDate());

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvContent;
        public TextView mTvTime;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
            this.mTvTime = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
