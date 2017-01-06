package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.bean.CommentBean;
import project.graduate.zhpan.littlebird.bean.LikeBean;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.fragment.TopicListFragment;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;

/**
 * Created by zhpan on 2017/1/4.
 */

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.TopicViewHolder> {
    private LayoutInflater inflater;
    private List<TopicBean> mList;
    private Context context;
    Fragment fragment;
    public List<TopicBean> getList() {
        return mList;
    }

    public void setList(List<TopicBean> mList) {
        this.mList = mList;
    }

    public TopicListAdapter(Context context,Fragment fragment){
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.fragment=fragment;

    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView=inflater.inflate(R.layout.item_topic,parent,false);
        TopicViewHolder holder=new TopicViewHolder(convertView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, final int position) {
        TopicBean topicBean =  mList.get(position);
        final List<UserBean> userBeen = DataSupport.where("email=?", topicBean.getEmail()).find(UserBean.class);
        final UserBean userBean = userBeen.get(0);

        List<LikeBean> likes = topicBean.getLike();

        if (likes.size() == 0) {
            holder.view.setVisibility(View.GONE);
        } else {
            holder.view.setVisibility(View.VISIBLE);
        }
        List<CommentBean> comments = topicBean.getComment();
        if (likes != null && likes.size() > 0) {
            String likePerson = "";
            for (int i = 0; i < likes.size(); i++) {
                likePerson = likePerson + (likes.get(i).getName()) + "、";
            }
            holder.tvLikes.setText(likePerson.substring(0, likePerson.length() - 1) + "觉得很赞");
            holder.tvLikes.setVisibility(View.VISIBLE);
        } else {
            holder.tvLikes.setVisibility(View.GONE);
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 5, 0);
        holder.llComment.removeAllViews();
        SpannableStringBuilder spannableStringBuilder;
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2863AD"));
        if (comments.size() > 0) {
            holder.llComment.setVisibility(View.VISIBLE);
            for (int i = 0; i < comments.size(); i++) {
                TextView textView = new TextView(context);
                textView.setLayoutParams(params);
                spannableStringBuilder = new SpannableStringBuilder(comments.get(i).getName() + "：" + comments.get(i).getContent());
                spannableStringBuilder.setSpan(colorSpan, 0, comments.get(i).getName().length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                textView.setText(spannableStringBuilder);
                holder.llComment.addView(textView);
            }
        } else {
            holder.llComment.setVisibility(View.GONE);
        }


        holder.tvContent.setText(topicBean.getContent());
        holder.tvView.setText("浏览" + topicBean.getView() + "次");
        holder.tvName.setText(userBean.getRealName());
        holder.tvTime.setText(DateUtils.formatTopicDate(topicBean.getDate()));
        if(((TopicListFragment)fragment).isParse(likes)){
            holder.ivPraise.setImageResource(R.drawable.av7);
        }else {
            holder.ivPraise.setImageResource(R.drawable.av6);
        }
        //  设置用信息
        Glide.with(context).load(userBeen.get(0).getHeadPic())
                .transform(new GlideCircleTransform(context))
                .placeholder(R.drawable.ic_home_avatar)
                .into(holder.ivHead);

        holder.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoActivity.start(context, userBean.getRealName(), userBean);
            }
        });

        holder.ivComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TopicAdapter.CommentCallBack) fragment).onCommentBtnClickListener(position);
            }
        });

        holder.ivPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivPraise.setImageResource(R.drawable.av7);
                //使用AnimationUtils类的loadAnimation来加载xml格式的动画文件
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_scale);
                holder.ivPraise.clearAnimation();
                holder.ivPraise.startAnimation(animation);
                ((TopicAdapter.CommentCallBack) fragment).onPraiseClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class TopicViewHolder extends RecyclerView.ViewHolder {
         ImageView ivHead;
         TextView tvName;
         TextView tvTime;
         TextView tvContent;
         TextView tvView;
         ImageView ivPraise;
         ImageView ivComment;
         TextView tvLikes;
         LinearLayout llComment;
         View view;

        public TopicViewHolder(View convertView) {
            super(convertView);
            ivHead = (ImageView) convertView.findViewById(R.id.iv_head_pic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            tvView = (TextView) convertView.findViewById(R.id.tv_view);
            ivPraise = (ImageView) convertView.findViewById(R.id.iv_praise);
            ivComment = (ImageView) convertView.findViewById(R.id.iv_comment);
            tvLikes = (TextView) convertView.findViewById(R.id.tv_likes);
            llComment = (LinearLayout) convertView.findViewById(R.id.ll_comment);
            view = convertView.findViewById(R.id.view_base_line);
        }
    }
}
