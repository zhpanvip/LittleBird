package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.QuestionBean;

/**
 * Created by zhpan on 2016/12/24.
 */

public class QuestionAdapter extends LittleBirdAdapter {
    private Context mContext;

    public QuestionAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return 10;
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
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.item_question, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_flag;
        public TextView tv_question;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_flag = (ImageView) rootView.findViewById(R.id.iv_flag);
            this.tv_question = (TextView) rootView.findViewById(R.id.tv_question);
        }

    }
}
