package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;

/**
 * Created by zhpan on 2016/12/24.
 */

public class ProjectAdapter extends LittleBirdAdapter {
    private Context mContext;

    public ProjectAdapter(Context mContext) {
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
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_project, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvProject;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvProject = (TextView) rootView.findViewById(R.id.tv_project);
        }

    }
}
