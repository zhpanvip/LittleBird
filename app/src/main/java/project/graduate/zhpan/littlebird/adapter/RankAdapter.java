package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.RankBean;

/**
 * Created by zhpan on 2016/12/18.
 */

public class RankAdapter extends LittleBirdAdapter {
    Context mContext;

    public RankAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return 20;
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
            convertView = View.inflate(mContext, R.layout.item_rank, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTvRank;
        public ImageView mIvRank;
        public CircleImageView mIvHeadPic;
        public TextView mTvName;
        public TextView mTvIntegral;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvRank = (TextView) rootView.findViewById(R.id.tv_rank);
            this.mIvRank = (ImageView) rootView.findViewById(R.id.iv_rank);
            this.mIvHeadPic = (CircleImageView) rootView.findViewById(R.id.iv_head_pic);
            this.mTvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.mTvIntegral = (TextView) rootView.findViewById(R.id.tv_integral);
        }

    }
}
