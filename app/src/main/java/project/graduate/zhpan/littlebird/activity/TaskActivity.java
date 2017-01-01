package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.adapter.TaskAdapter;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.callback.TaskOptionListener;
import project.graduate.zhpan.littlebird.constants.Constatns;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;
import project.graduate.zhpan.littlebird.utils.UserTools;

public class TaskActivity extends BaseActivity implements View.OnClickListener,TaskOptionListener{

    private ListView mLvTask;
    private TextView mTvHistory;
    private TaskAdapter mTaskAdapter;
    private List<TaskBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setListener(){
        mTvHistory.setOnClickListener(this);
        mIvRight.setOnClickListener(this);

    }
    private void initData() {
        mTvTitle.setText("我的任务");
        mIvRight.setVisibility(View.VISIBLE);

        mTaskAdapter=new TaskAdapter(this);
        mList=new ArrayList<>();
        mList = DataSupport.where("createDate=? and userEmail=?", DateUtils.getDate(), UserInfoTools.getEmail(this)).find(TaskBean.class, true);

        mTaskAdapter.setList(mList);
        mLvTask.setAdapter(mTaskAdapter);
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mLvTask= (ListView) findViewById(R.id.lv_task);
        mTvHistory= (TextView) findViewById(R.id.tv_history_task);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_history_task:
                Intent  intent=new Intent(this,HistoryTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_right:
                EditTaskActivity.start(this,"添加任务");
                break;
        }
    }

    /**
     * 删除任务成功回调方法
     * @param position
     */
    @Override
    public void onDeleteSuccess(int position) {
        TaskBean taskBean = (TaskBean) mTaskAdapter.getmList().get(position);
        DataSupport.deleteAll(TaskBean.class, "taskName=  ?" ,( taskBean.getTaskName()));
        mList= DataSupport.findAll(TaskBean.class);
        mTaskAdapter.setList(mList);
        mTaskAdapter.notifyDataSetChanged();
    }

    /**
     * 编辑任务点击事件
     * @param position
     */
    @Override
    public void onTaskEdit(int position) {
        EditTaskActivity.start(this,"编辑任务");
    }

    /**
     * 提交任务点击事件
     * @param position
     */
    @Override
    public void onTaskCommit(int position) {
        TaskBean taskBean = (TaskBean) mTaskAdapter.getmList().get(position);
        taskBean.setTaskState(Constatns.HAVE_COMMIT);
        double useTime=System.currentTimeMillis()-taskBean.getRealStartTime();
        double totalTime=taskBean.getEndTime()-taskBean.getStartTime();
        int completePercent= (int) ((useTime/totalTime)*100);
        taskBean.setCompletePercent( completePercent);
        taskBean.setRealStartTime(System.currentTimeMillis());
        taskBean.setUserEmail(UserInfoTools.getEmail(this));
        taskBean.save();

        mList= DataSupport.where("createDate=? and userEmail=?", DateUtils.getDate(),UserInfoTools.getEmail(this)).find(TaskBean.class);
        mTaskAdapter.setList(mList);
        mTaskAdapter.notifyDataSetChanged();
        Toast.makeText(this, "任务已提交", Toast.LENGTH_SHORT).show();
    }

    /**
     * 开始任务点击事件
     * @param position
     */
    @Override
    public void onTaskStart(int position) {
        TaskBean taskBean = (TaskBean) mTaskAdapter.getmList().get(position);
        taskBean.setTaskState(Constatns.WAIT_COMMIT);
        taskBean.setRealStartTime(System.currentTimeMillis());
        taskBean.save();
        mList= DataSupport.findAll(TaskBean.class);
        mTaskAdapter.setList(mList);
        mTaskAdapter.notifyDataSetChanged();
    }
    @Subscribe
    public void onTaskCreateSuccess(EditTaskActivity.TaskCreateSuccess taskCreateSuccess){
        mList= DataSupport.where("createDate=? and userEmail=?", DateUtils.getDate(),UserInfoTools.getEmail(this)).find(TaskBean.class);
        mTaskAdapter.setList(mList);
        mTaskAdapter.notifyDataSetChanged();
    }
}
