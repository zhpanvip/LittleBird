package project.graduate.zhpan.littlebird.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2017/1/2.
 */

public class CommentBean extends DataSupport {
    private String Name;
    private String content;
    private String email;
    private TopicBean topicBean;

    public TopicBean getTopicBean() {
        return topicBean;
    }

    public void setTopicBean(TopicBean topicBean) {
        this.topicBean = topicBean;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
