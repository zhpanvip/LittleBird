package project.graduate.zhpan.littlebird.callback;

/**
 * Created by zhpan on 2016/12/31.
 */

public interface TaskOptionListener {
    void onDeleteSuccess(int postion);
    void onEdit(int position);
    void onCommit(int position);

}
