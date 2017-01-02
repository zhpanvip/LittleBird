package project.graduate.zhpan.littlebird.bean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zhpan on 2017/1/2.
 */

public class TopicBean extends DataSupport {
    private String email;
    private String content;
    private long date;
    private int view;
    private List<CommentBean> comment;
    private List<LikeBean> like;

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public List<LikeBean> getLike() {
        return like;
    }

    public void setLike(List<LikeBean> like) {
        this.like = like;
    }
}
