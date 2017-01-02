package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.LeaveListActivity;
import project.graduate.zhpan.littlebird.bean.LeaveBean;

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
            convertView = View.inflate(mContext, R.layout.item_leave, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        LeaveBean leaveBean = (LeaveBean) mList.get(position);
        type=leaveBean.getLeaveType();
        if(type== LeaveListActivity.OUT_FOR_WORK){
            holder.mTvOption.setText("公出");
            holder.mTvOption.setBackgroundResource(R.drawable.bg_out_for_work);
        }else if(type==LeaveListActivity.LEAVE){
            holder.mTvOption.setText("请假");
            holder.mTvOption.setBackgroundResource(R.drawable.bg_leave);
        }

        holder.mTvReason.setText(leaveBean.getReason());

        int checkResult = leaveBean.getCheckResult();
        switch (checkResult){
            case 0:
                holder.mTvState.setText("待审批");

                break;
            case 1:
                holder.mTvState.setText("审批通过");
                break;
            case 2:
                holder.mTvState.setText("已拒绝");
                break;
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
