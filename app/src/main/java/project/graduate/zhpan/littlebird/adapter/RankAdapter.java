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
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.fragment.RankFragment;

/**
 * Created by zhpan on 2016/12/18.
 */

public class RankAdapter extends LittleBirdAdapter {
    private Context mContext;
    private String rankType;


    public RankAdapter(Context mContext,String rankType) {
        this.mContext = mContext;
        this.rankType=rankType;
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
        ViewHolder holder;
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.item_rank, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(position==0){
            holder.mIvRank.setImageResource(R.drawable.icon_rank_1);
            holder.mIvRank.setVisibility(View.VISIBLE);
            holder.mTvRank.setVisibility(View.INVISIBLE);
        }else if(position==1) {
            holder.mIvRank.setImageResource(R.drawable.icon_rank_2);
            holder.mIvRank.setVisibility(View.VISIBLE);
            holder.mTvRank.setVisibility(View.INVISIBLE);
        }else if(position==2) {
            holder.mIvRank.setImageResource(R.drawable.icon_rank_3);
            holder.mIvRank.setVisibility(View.VISIBLE);
            holder.mTvRank.setVisibility(View.INVISIBLE);
        }else {
            holder.mIvRank.setVisibility(View.INVISIBLE);
            holder.mTvRank.setVisibility(View.VISIBLE);
            holder.mTvRank.setText(position+1+"");
        }

        UserBean user = (UserBean) mList.get(position);
        holder.mTvName.setText(user.getRealName());
        if(rankType== RankFragment.TYPE_DAY){
            holder.mTvIntegral.setText(user.getAverageDay()+"");
        }else if(rankType== RankFragment.TYPE_WEEK){
            holder.mTvIntegral.setText(user.getAverageWeek()+"");
        }else if(rankType== RankFragment.TYPE_MONTH){
            holder.mTvIntegral.setText(user.getAverageMonth()+"");
        }else if(rankType== RankFragment.TYPE_QUARTER){
            holder.mTvIntegral.setText(user.getAverageQuarter()+"");
        }else if(rankType== RankFragment.TYPE_YEAR){
            holder.mTvIntegral.setText(user.getAverageYear()+"");
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
