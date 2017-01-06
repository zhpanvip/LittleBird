package project.graduate.zhpan.littlebird.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zhpan on 2017/1/4.
 */

public class RecyclerViewForScrollView extends RecyclerView{
    public RecyclerViewForScrollView(Context context) {
        super(context);
    }

    public RecyclerViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
