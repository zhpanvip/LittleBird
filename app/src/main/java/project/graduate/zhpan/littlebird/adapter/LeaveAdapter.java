package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;

/**
 * Created by zhpan on 2016/12/24.
 */

public class LeaveAdapter extends LittleBirdAdapter {
    private Context mContext;
    private int type;

    public LeaveAdapter(Context mContext,int type) {
        this.mContext = mContext;
        this.type=type;
    }

    @Override
    public int getCount() {
        return 10;
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
            convertView = View.inflate(mContext, R.layout.item_leave, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvOption;
        public TextView mTvReason;
        public ImageView mIvJump;
        public TextView mTvState;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvOption = (TextView) rootView.findViewById(R.id.tv_option);
            this.mTvReason = (TextView) rootView.findViewById(R.id.tv_reason);
            this.mIvJump = (ImageView) rootView.findViewById(R.id.iv_jump);
            this.mTvState = (TextView) rootView.findViewById(R.id.tv_state);
        }

    }
}
