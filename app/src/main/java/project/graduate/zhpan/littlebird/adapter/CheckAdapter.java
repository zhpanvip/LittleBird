package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;

/**
 * Created by zhpan on 2016/12/25.
 */

public class CheckAdapter extends LittleBirdAdapter {
    Context mContext;

    public CheckAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 5;
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
            convertView = View.inflate(mContext, R.layout.item_colleague_view, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public CircleImageView mIvItemHead;
        public TextView mTvItemName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mIvItemHead = (CircleImageView) rootView.findViewById(R.id.iv_item_head);
            this.mTvItemName = (TextView) rootView.findViewById(R.id.tv_item_name);
        }

    }
}
