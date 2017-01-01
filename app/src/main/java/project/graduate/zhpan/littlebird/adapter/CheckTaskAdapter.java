package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.EditTaskActivity;
import project.graduate.zhpan.littlebird.activity.GradeActivity;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;

/**
 * Created by zhpan on 2016/11/20.
 */

public class CheckTaskAdapter extends LittleBirdAdapter {
    private Context mContext;

    public CheckTaskAdapter(Context context) {
        this.mContext = context;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final TaskViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(mContext,R.layout.item_check_task,null);
            holder=new TaskViewHolder();
            holder.taskName= (TextView) convertView.findViewById(R.id.tv_task_name);
            holder.viewComplete=convertView.findViewById(R.id.view_complete);
            holder.viewQuality=convertView.findViewById(R.id.view_quality);
            holder.completeState= (TextView) convertView.findViewById(R.id.tv_complete_state);
            holder.qualityState= (TextView) convertView.findViewById(R.id.tv_quality_state);
            holder.timeCount= (TextView) convertView.findViewById(R.id.tv_time_count);
            holder.checkPerson= (TextView) convertView.findViewById(R.id.tv_check_person);
            holder.btnCheck= (Button) convertView.findViewById(R.id.btn_check);
            holder.tvCountTime= (TextView) convertView.findViewById(R.id.tv_cunt_time);
            holder.swipeMenuLayout= (SwipeMenuLayout) convertView.findViewById(R.id.swipe_menu_layout);
            convertView.setTag(holder);
        }else {
            holder= (TaskViewHolder) convertView.getTag();
        }
        TaskBean taskBean = (TaskBean) mList.get(position);
        if(taskBean.isCommit()&&!taskBean.isCheck()){
            holder.swipeMenuLayout.setSwipeEnable(true);
        }
        holder.taskName.setText("任务"+(position+1)+"："+taskBean.getTaskName());
        if(taskBean.isCheck()){
            holder.checkPerson.setText("审核状态：已审核");
        }else {
            holder.checkPerson.setText("审核状态：未审核");
        }
        if(!taskBean.isStart()){
            holder.timeCount.setText("未开始");
            holder.completeState.setText("未开始");
            holder.qualityState.setText("未开始");
        }else {
            if(taskBean.isCommit()){
                long totalTime = taskBean.getCommitTime()-taskBean.getRealStartTime();
                holder.timeCount.setText(DateUtils.timeFormat(totalTime/100));
                holder.tvCountTime.setText("任务用时：");
                holder.completeState.setText("已提交");
                holder.qualityState.setText("已提交");
            }else {
                holder.completeState.setText("待提交");
                holder.qualityState.setText("待提交");
                holder.tvCountTime.setText("倒计时：");
                long totalTime =taskBean.getEndTime()- System.currentTimeMillis();
                if(totalTime>0){
                    holder.timeCount.setText(DateUtils.timeFormat(totalTime/1000));
                }else {
                    holder.timeCount.setText("00：00：00");
                }
            }
        }


        holder.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context, EditTaskActivity.class));
                Intent intent=new Intent(mContext, GradeActivity.class);
                Bundle bundle=new Bundle();
                TaskBean taskBean1= (TaskBean) mList.get(position);
                bundle.putSerializable("taskBean",taskBean1);
                intent.putExtra("bundle",bundle);
                mContext.startActivity(intent);
                holder.swipeMenuLayout.smoothClose();
            }
        });

        return convertView;
    }

    static class TaskViewHolder {
        private TextView taskName;
        private View viewComplete;
        private View viewQuality;
        private TextView completeState;
        private TextView qualityState;
        private TextView timeCount;
        private TextView checkPerson;
        private Button btnCheck;
        private TextView tvCountTime;
        private SwipeMenuLayout swipeMenuLayout;
    }

}
