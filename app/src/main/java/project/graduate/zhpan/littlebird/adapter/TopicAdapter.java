package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;

/**
 * Created by zhpan on 2016/11/5.
 */

public class TopicAdapter extends LittleBirdAdapter {
    Context context;
    Fragment fragment;
    public TopicAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment=fragment;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final TopicViewHolder holder;
        if(convertView==null){
            holder=new TopicViewHolder();
            convertView=View.inflate(context,R.layout.item_topic,null);
            holder.ivHead= (CircleImageView) convertView.findViewById(R.id.iv_head_pic);
            holder.tvName= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvTime= (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvContent= (TextView) convertView.findViewById(R.id.tv_content);
            holder.tvView= (TextView) convertView.findViewById(R.id.tv_view);
            holder.ivPraise= (ImageView) convertView.findViewById(R.id.iv_praise);
            holder.ivComment= (ImageView) convertView.findViewById(R.id.iv_comment);
            convertView.setTag(holder);
        }else {
            holder= (TopicViewHolder) convertView.getTag();
        }

        holder.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoActivity.start(context,"赵云");
            }
        });

        holder.ivComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CommentCallBack)fragment).onCommentBtnClickListener(position);
            }
        });

        holder.ivPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivPraise.setImageResource(R.drawable.av7);
                //使用AnimationUtils类的loadAnimation来加载xml格式的动画文件
                Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_scale);
                holder.ivPraise.clearAnimation();
                holder.ivPraise.startAnimation(animation);
            }
        });
        return convertView;
    }

    static class TopicViewHolder{
        private CircleImageView ivHead;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvContent;
        private TextView tvView;
        private ImageView ivPraise;
        private ImageView ivComment;
    }
    public interface CommentCallBack{
       void onCommentBtnClickListener(int position);
    }
}
