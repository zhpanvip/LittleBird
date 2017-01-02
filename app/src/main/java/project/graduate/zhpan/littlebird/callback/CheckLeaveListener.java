package project.graduate.zhpan.littlebird.callback;

/**
 * Created by zhpan on 2017/1/2.
 */

public interface CheckLeaveListener  {
    void onPassListener(int position);

    void onRefusedListener(int position);
}
