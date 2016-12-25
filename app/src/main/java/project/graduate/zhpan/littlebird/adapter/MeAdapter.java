package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import project.graduate.zhpan.littlebird.R;

/**
 * Created by zhpan on 2016/10/15.
 */

public class MeAdapter extends LittleBirdAdapter {

    Context mContext;
    String[] mText;
    int [] images;

    public MeAdapter(Context mContext, String[] mText, int[] images) {
        this.mContext = mContext;
        this.mText = mText;
        this.images = images;
    }



    @Override
    public int getCount() {
        return mText.length;
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
            convertView=View.inflate(mContext, R.layout.item_gride_view,null);
            holder.mImageView= (ImageView) convertView.findViewById(R.id.iv_item_picture);
            holder.mTextView= (TextView) convertView.findViewById(R.id.tv_item_text);
            convertView.setTag(holder);
        }else {
            holder = (HomeGridViewHolder) convertView.getTag();
        }

        holder.mImageView.setBackgroundResource(images[position]);
        holder.mTextView.setText(mText[position]);
        return convertView;
    }

    static class HomeGridViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}
