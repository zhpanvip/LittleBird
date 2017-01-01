package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.EditTaskActivity;
import project.graduate.zhpan.littlebird.activity.TaskActivity;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.constants.Constatns;
import project.graduate.zhpan.littlebird.utils.DateUtils;

/**
 * Created by zhpan on 2016/11/20.
 */

public class TaskAdapter extends LittleBirdAdapter {
    private Context context;
    private TaskActivity taskActivity;

    public TaskAdapter(Context context) {
        this.context = context;
        taskActivity=(TaskActivity)context;
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
            convertView=View.inflate(context,R.layout.item_task,null);
            holder=new TaskViewHolder();
            holder.taskName= (TextView) convertView.findViewById(R.id.tv_task_name);
            holder.pbComplete= (ProgressBar) convertView.findViewById(R.id.pb_complete);
            holder.pbQuality= (ProgressBar) convertView.findViewById(R.id.pb_quality);
            holder.completeState= (TextView) convertView.findViewById(R.id.tv_complete_state);
            holder.qualityState= (TextView) convertView.findViewById(R.id.tv_quality_state);
            holder.timeCount= (TextView) convertView.findViewById(R.id.tv_time_count);
            holder.checkPerson= (TextView) convertView.findViewById(R.id.tv_check_person);
            holder.btnEdit= (Button) convertView.findViewById(R.id.btn_edit);
            holder.btnCommit= (Button) convertView.findViewById(R.id.btn_commit);
            holder.btnDelete= (Button) convertView.findViewById(R.id.btn_delete);
            holder.swipeMenuLayout= (SwipeMenuLayout) convertView.findViewById(R.id.swipe_menu_layout);
            convertView.setTag(holder);
        }else {
            holder= (TaskViewHolder) convertView.getTag();
        }
        TaskBean taskBean = (TaskBean) mList.get(position);
        holder.taskName.setText("任务"+(position+1)+":"+taskBean.getTaskName());
        holder.pbComplete.setProgress(taskBean.getCompletePercent());


        final int taskState = taskBean.getTaskState();
        switch (taskState){
            case 0:
                holder.completeState.setText("待开始");
                holder.qualityState.setText("待开始");
                holder.btnCommit.setText("开始");
                holder.timeCount.setText("未开始");
                holder.swipeMenuLayout.setSwipeEnable(true);
                break;
            case 1:
                holder.completeState.setText("待提交");
                holder.qualityState.setText("待提交");
                holder.btnCommit.setText("提交");
                if(System.currentTimeMillis()<taskBean.getEndTime()){
                    long totalTime = System.currentTimeMillis()-taskBean.getEndTime();
                    holder.timeCount.setText(DateUtils.timeFormat(totalTime/1000));
                }else {
                    holder.timeCount.setText("00:00:00");
                }
                holder.swipeMenuLayout.setSwipeEnable(true);
                break;
            case 2:
                holder.completeState.setText("已提交");
                holder.qualityState.setText("已提交");
                holder.timeCount.setText("已提交");
                holder.swipeMenuLayout.setSwipeEnable(false);
                break;
        }

        holder.checkPerson.setText("审核人："+taskBean.getCheckPerson());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context, EditTaskActivity.class));
                holder.swipeMenuLayout.smoothClose();
                taskActivity.onTaskEdit(position);
            }
        });

        holder.btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.swipeMenuLayout.smoothClose();
                if(taskState== Constatns.WAIT_START){
                    taskActivity.onTaskStart(position);
                }else if(taskState==Constatns.WAIT_COMMIT){
                    taskActivity.onTaskCommit(position);
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.swipeMenuLayout.smoothClose();
                taskActivity.onDeleteSuccess(position);
            }
        });
        return convertView;
    }

    static class TaskViewHolder {
        private TextView taskName;
        private ProgressBar pbComplete;
        private ProgressBar pbQuality;
        private TextView completeState;
        private TextView qualityState;
        private TextView timeCount;
        private TextView checkPerson;
        private Button btnEdit;
        private Button btnCommit;
        private Button btnDelete;
        private SwipeMenuLayout swipeMenuLayout;
    }


}
