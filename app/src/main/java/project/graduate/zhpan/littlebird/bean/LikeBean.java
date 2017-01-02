package project.graduate.zhpan.littlebird.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhpan on 2017/1/2.
 */

public class LikeBean extends DataSupport {
    private String name;
    private String email;
    private TopicBean topicBean;

    public TopicBean getTopicBean() {
        return topicBean;
    }

    public void setTopicBean(TopicBean topicBean) {
        this.topicBean = topicBean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
