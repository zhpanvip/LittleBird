package project.graduate.zhpan.littlebird.bean;

/**
 * Created by zhangpan on 2016/6/10.
 */
public class LoginBean extends LittleBirldBean {

    /**
     * result : 1
     * msg : 登录成功
     * data : {"id":"1","username":"kooeasy","userpass":"ZmU1MTgxYTJiODA4MTkzMDY0YmZiMWJlZWI5NjNkZTk=","usersex":null,"useremail":null,"nickname":"专骗小女生","birthday":null,"portrait":"20150826/55dd75d78083b.jpg","signature":null}
     */

    private int result;
    private String msg;
    /**
     * id : 1
     * username : kooeasy
     * userpass : ZmU1MTgxYTJiODA4MTkzMDY0YmZiMWJlZWI5NjNkZTk=
     * usersex : null
     * useremail : null
     * nickname : 专骗小女生
     * birthday : null
     * portrait : 20150826/55dd75d78083b.jpg
     * signature : null
     */

    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String username;
        private String userpass;
        private Object usersex;
        private Object useremail;
        private String nickname;
        private Object birthday;
        private String portrait;
        private Object signature;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserpass() {
            return userpass;
        }

        public void setUserpass(String userpass) {
            this.userpass = userpass;
        }

        public Object getUsersex() {
            return usersex;
        }

        public void setUsersex(Object usersex) {
            this.usersex = usersex;
        }

        public Object getUseremail() {
            return useremail;
        }

        public void setUseremail(Object useremail) {
            this.useremail = useremail;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }
    }
}
