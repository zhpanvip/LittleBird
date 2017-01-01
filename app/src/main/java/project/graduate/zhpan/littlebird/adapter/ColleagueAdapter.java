package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.bean.UserBean;

/**
 * Created by zhpan on 2016/10/15.
 */

public class ColleagueAdapter extends LittleBirdAdapter {

    Context mContext;

    public ColleagueAdapter(Context mContext) {
        this.mContext = mContext;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        HomeGridViewHolder holder;
        if(convertView==null){
            holder=new HomeGridViewHolder();
            convertView=View.inflate(mContext, R.layout.item_colleague_view,null);
            holder.mImageView= (ImageView) convertView.findViewById(R.id.iv_item_head);
            holder.mTextView= (TextView) convertView.findViewById(R.id.tv_item_name);
            convertView.setTag(holder);
        }else {
            holder= (HomeGridViewHolder) convertView.getTag();
        }
        UserBean user = (UserBean) mList.get(position);
        holder.mTextView.setText(user.getRealName());

        return convertView;
    }

    static class HomeGridViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}
