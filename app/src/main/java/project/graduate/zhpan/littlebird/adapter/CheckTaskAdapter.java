package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.EditTaskActivity;
import project.graduate.zhpan.littlebird.activity.GradeActivity;

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

            holder.swipeMenuLayout= (SwipeMenuLayout) convertView.findViewById(R.id.swipe_menu_layout);
            convertView.setTag(holder);
        }else {
            holder= (TaskViewHolder) convertView.getTag();
        }

        holder.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context, EditTaskActivity.class));
                Intent intent=new Intent(mContext, GradeActivity.class);
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
        private SwipeMenuLayout swipeMenuLayout;
    }

}
