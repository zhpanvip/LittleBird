package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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

import de.hdodenhof.circleimageview.CircleImageView;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.bean.ColleagueBean;
import project.graduate.zhpan.littlebird.bean.CommentBean;
import project.graduate.zhpan.littlebird.bean.LikeBean;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.fragment.TopicFragment;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.view.GlideCircleTransform;

/**
 * Created by zhpan on 2016/11/5.
 */

public class TopicAdapter extends LittleBirdAdapter {
    Context context;
    Fragment fragment;

    public TopicAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final TopicViewHolder holder;
        if (convertView == null) {
            holder = new TopicViewHolder();
            convertView = View.inflate(context, R.layout.item_topic, null);
            holder.ivHead = (ImageView) convertView.findViewById(R.id.iv_head_pic);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tvView = (TextView) convertView.findViewById(R.id.tv_view);
            holder.ivPraise = (ImageView) convertView.findViewById(R.id.iv_praise);
            holder.ivComment = (ImageView) convertView.findViewById(R.id.iv_comment);
            holder.tvLikes = (TextView) convertView.findViewById(R.id.tv_likes);
            holder.llComment = (LinearLayout) convertView.findViewById(R.id.ll_comment);
            holder.view = convertView.findViewById(R.id.view_base_line);
            convertView.setTag(holder);
        } else {
            holder = (TopicViewHolder) convertView.getTag();
        }

        TopicBean topicBean = (TopicBean) mList.get(position);
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
        if(((TopicFragment)fragment).isParse(likes)){
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
                ((CommentCallBack) fragment).onCommentBtnClickListener(position);
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
                ((CommentCallBack) fragment).onPraiseClickListener(position);
            }
        });
        return convertView;
    }

    static class TopicViewHolder {
        private ImageView ivHead;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvContent;
        private TextView tvView;
        private ImageView ivPraise;
        private ImageView ivComment;
        private TextView tvLikes;
        private LinearLayout llComment;
        private View view;
    }

    public interface CommentCallBack {
        void onCommentBtnClickListener(int position);

        void onPraiseClickListener(int position);
    }
}
