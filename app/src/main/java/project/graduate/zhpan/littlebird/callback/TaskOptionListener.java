package project.graduate.zhpan.littlebird.callback;

/**
 * Created by zhpan on 2016/12/31.
 */

public interface TaskOptionListener {
    void onDeleteSuccess(int postion);
    void onTaskEdit(int position);
    void onTaskCommit(int position);
    void onTaskStart(int position);

}