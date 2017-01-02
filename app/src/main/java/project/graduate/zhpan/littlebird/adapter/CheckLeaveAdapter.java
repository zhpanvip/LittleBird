package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.CheckLeaveActivity;
import project.graduate.zhpan.littlebird.bean.LeaveBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;

/**
 * Created by zhpan on 2017/1/2.
 */

public class CheckLeaveAdapter extends LittleBirdAdapter {
    private Context mContext;
    private CheckLeaveActivity checkLeaveActivity;

    public CheckLeaveAdapter(Context mContext) {
        this.mContext = mContext;
        checkLeaveActivity=(CheckLeaveActivity) mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_check_leave, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        LeaveBean leaveBean = (LeaveBean) mList.get(position);
        final int checkResult = leaveBean.getCheckResult();

        switch (checkResult){
            case 0:
                holder.swipeMenuLayout.setSwipeEnable(true);
                holder.mTvState.setText("审批状态："+"待审批");

                break;
            case 1:
                holder.swipeMenuLayout.setSwipeEnable(false);
                holder.mTvState.setText("审批状态："+"已通过");
                break;
            case 2:
                holder.swipeMenuLayout.setSwipeEnable(false);
                holder.mTvState.setText("审批状态："+"已拒绝");
                break;
        }
        holder.mTvPerson.setText("申请人："+leaveBean.getLeavePerson());
        holder.mTvStartTime.setText(DateUtils.timeStampToExactlyDate(leaveBean.getStartTime()));
        holder.mTvEndTime.setText(DateUtils.timeStampToExactlyDate(leaveBean.getEndTime()));
        holder.mTvReason.setText(leaveBean.getReason());

        holder.mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeMenuLayout.smoothClose();
                checkLeaveActivity.onPassListener(position);
            }
        });

        holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeMenuLayout.smoothClose();
                checkLeaveActivity.onRefusedListener(position);
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvStartTime;
        public RelativeLayout mRlStartTime;
        public TextView mTvEndTime;
        public RelativeLayout mRlEndTime;
        public TextView mTvLeaveReason;
        public TextView mTvReason;
        public Button mBtnCommit;
        public Button mBtnDelete;
        public TextView mTvPerson;
        public TextView mTvState;
        public SwipeMenuLayout swipeMenuLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvStartTime = (TextView) rootView.findViewById(R.id.tv_start_time);
            this.mRlStartTime = (RelativeLayout) rootView.findViewById(R.id.rl_start_time);
            this.mTvEndTime = (TextView) rootView.findViewById(R.id.tv_end_time);
            this.mRlEndTime = (RelativeLayout) rootView.findViewById(R.id.rl_end_time);
            this.mTvLeaveReason = (TextView) rootView.findViewById(R.id.tv_leave_reason);
            this.mTvReason = (TextView) rootView.findViewById(R.id.tv_reason);
            this.mBtnCommit = (Button) rootView.findViewById(R.id.btn_commit);
            this.mBtnDelete = (Button) rootView.findViewById(R.id.btn_delete);
            this.mTvPerson= (TextView) rootView.findViewById(R.id.tv_apply_person);
            this.mTvState= (TextView) rootView.findViewById(R.id.tv_check_state);
            this.swipeMenuLayout= (SwipeMenuLayout) rootView.findViewById(R.id.swipe_menu_layout);
        }

    }
}
