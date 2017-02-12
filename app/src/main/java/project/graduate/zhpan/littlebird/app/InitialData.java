package project.graduate.zhpan.littlebird.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import project.graduate.zhpan.littlebird.bean.CommentBean;
import project.graduate.zhpan.littlebird.bean.EncourageBean;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.bean.LikeBean;
import project.graduate.zhpan.littlebird.bean.NoticeBean;
import project.graduate.zhpan.littlebird.bean.TopicBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;

/**
 * Created by zhpan on 2017/1/1.
 */
//  初始化数据
public class InitialData {
    //  初始化用户
    public static void initUser() {
        UserBean userBean = new UserBean();
        userBean.setRealName("Admin");
        userBean.setEmail("admin@littlebird.com");
        userBean.setPassword("123456");
        userBean.setAdmin(true);
        long registerTime = getRegisterTime(8, 01, 9, 2013);
        userBean.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime)));
        userBean.setDepartment("技术部");
        userBean.setEntryTime("2013年09月01日");
        userBean.setIntegral(586);
        userBean.setJob("项目经理");
        userBean.setPersonalSign("一直被模仿 从未被超越");
        userBean.setTel("15515269670");
        userBean.setRank(4);
        userBean.setProjectGroup("河南大学软件中心");
        userBean.setAverageWeek(83.0);
        userBean.setAverageMonth(86.2);
        userBean.setAverageQuarter(85.0);
        userBean.setAverageYear(86.4);
        userBean.setHeadPic("http://up.qqjia.com/z/04/tu6180_9.jpg");
        userBean.save();

        UserBean userBean1 = new UserBean();
        userBean1.setRealName("弥萨");
        userBean1.setEmail("misa@littlebird.com");
        userBean1.setPassword("123456");
        userBean1.setAdmin(false);
        long registerTime1 = getRegisterTime(8, 12, 6, 2015);
        userBean1.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime1)));
        userBean1.setDepartment("人事部");
        userBean1.setEntryTime("2015年06月12日");
        userBean1.setIntegral(56);
        userBean1.setJob("HR");
        userBean1.setPersonalSign("在静怡的月光里隔世相望。");
        userBean1.setTel("15515643648");
        userBean1.setRank(3);
        userBean1.setProjectGroup("河南大学软件中心");
        userBean1.setAverageWeek(19.0);
        userBean1.setAverageMonth(82.2);
        userBean1.setAverageQuarter(84.0);
        userBean1.setAverageYear(81.8);
        userBean1.setHeadPic("http://up.qqjia.com/z/23/tu27501_21.jpg");
        userBean1.save();

        UserBean userBean2 = new UserBean();
        userBean2.setRealName("法维安");
        userBean2.setEmail("fwa@littlebird.com");
        userBean2.setPassword("123456");
        userBean2.setAdmin(false);
        long registerTime2 = getRegisterTime(8, 12, 2, 2013);
        userBean2.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime2)));
        userBean2.setDepartment("技术部");
        userBean2.setEntryTime("2013年09月01日");
        userBean2.setIntegral(586);
        userBean2.setJob("android开发工程师");
        userBean2.setPersonalSign("断罪之书终将夺回");
        userBean2.setTel("15515269672");
        userBean2.setRank(2);
        userBean2.setProjectGroup("河南大学软件中心");
        userBean2.setAverageWeek(86.6);
        userBean2.setAverageMonth(82.1);
        userBean2.setAverageQuarter(86.0);
        userBean2.setAverageYear(85.1);
        userBean2.setHeadPic("http://v1.qzone.cc/avatar/201504/15/21/24/552e66a78ae1e875.jpg%21200x200.jpg");
        userBean2.save();

        UserBean userBean3 = new UserBean();
        userBean3.setRealName("周洪");
        userBean3.setEmail("zhouhong@littlebird.com");
        userBean3.setPassword("123456");
        userBean3.setAdmin(false);
        long registerTime3 = getRegisterTime(8, 12, 3, 2016);
        userBean3.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime3)));
        userBean3.setDepartment("技术部");
        userBean3.setEntryTime("2016年03月01日");
        userBean3.setIntegral(389);
        userBean3.setJob("IOS开发工程师");
        userBean3.setPersonalSign("执着于心");
        userBean3.setTel("15515269673");
        userBean3.setRank(1);
        userBean3.setProjectGroup("河南大学软件中心");
        userBean3.setAverageWeek(80.1);
        userBean3.setAverageMonth(84.5);
        userBean3.setAverageQuarter(81.6);
        userBean3.setAverageYear(0);
        userBean3.setHeadPic("http://i0.hdslb.com/video/97/971b7fa02aaec6ce0c36949af91f07ac.jpg");
        userBean3.save();

        UserBean userBean4 = new UserBean();
        userBean4.setRealName("斯图尔特");
        userBean4.setEmail("stewart@littlebird.com");
        userBean4.setPassword("123456");
        userBean4.setAdmin(false);
        long registerTime4 = getRegisterTime(8, 1, 4, 2016);
        userBean4.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime4)));
        userBean4.setDepartment("技术部");
        userBean4.setEntryTime("2013年04月01日");
        userBean4.setIntegral(103);
        userBean4.setJob("项目经理");
        userBean4.setPersonalSign("冰川，那是我最美丽的遐想。");
        userBean4.setTel("15515269674");
        userBean4.setRank(1);
        userBean4.setProjectGroup("河南大学软件中心");
        userBean4.setAverageWeek(81.0);
        userBean4.setAverageMonth(79.0);
        userBean4.setAverageQuarter(78.0);
        userBean4.setAverageYear(0);
        userBean4.setHeadPic("http://img.pipaw.net/wy/editor/news/2015/05/11/b7e0b829552cbdb22eecf42f3b471cb4.jpg");
        userBean4.save();

        UserBean userBean5 = new UserBean();
        userBean5.setRealName("海伦");
        userBean5.setEmail("hellen@littlebird.com");
        userBean5.setPassword("123456");
        userBean5.setAdmin(false);
        long registerTime5 = getRegisterTime(8, 5, 5, 2013);
        userBean5.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime5)));
        userBean5.setDepartment("技术部");
        userBean5.setEntryTime("2013年5月5日");
        userBean5.setIntegral(686);
        userBean5.setJob("项目经理");
        userBean5.setPersonalSign("海潮降至");
        userBean5.setTel("15515269675");
        userBean5.setRank(3);
        userBean5.setProjectGroup("河南大学软件中心");
        userBean5.setAverageWeek(84.0);
        userBean5.setAverageMonth(82.6);
        userBean5.setAverageQuarter(81.3);
        userBean5.setAverageYear(84.2);
        userBean5.setHeadPic("http://im5.tongbu.com/webgames/38d570c0-1.jpg");
        userBean5.setImei("123456789000");
        userBean5.setNotFirstLogin(true);
        userBean5.save();

        UserBean userBean6 = new UserBean();
        userBean6.setRealName("费奥纳");
        userBean6.setEmail("fan@littlebird.com");
        userBean6.setPassword("123456");
        userBean6.setAdmin(false);
        long registerTime6 = getRegisterTime(8, 1, 1, 2017);
        userBean6.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime6)));
        userBean6.setDepartment("技术部");
        userBean6.setEntryTime("2013年09月01日");
        userBean6.setIntegral(586);
        userBean6.setJob("Web前端工程师");
        userBean6.setPersonalSign("休闲的时刻");
        userBean6.setTel("15515269670");
        userBean6.setRank(1);
        userBean6.setProjectGroup("河南大学软件中心");
        userBean6.setAverageWeek(82.1);
        userBean6.setAverageMonth(82.4);
        userBean6.setAverageQuarter(0);
        userBean6.setAverageYear(0);
        userBean6.setHeadPic("http://zyzz.appgame.com/wp-content/uploads/sites/116/2015/12/38.jpg");
        userBean6.save();

        UserBean userBean7 = new UserBean();
        userBean7.setRealName("德古拉");
        userBean7.setEmail("dgl@littlebird.com");
        userBean7.setPassword("123456");
        userBean7.setAdmin(false);
        long registerTime7 = getRegisterTime(8, 12, 9, 2013);
        userBean7.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime7)));
        userBean7.setDepartment("技术部");
        userBean7.setEntryTime("2013年09月01日");
        userBean7.setIntegral(586);
        userBean7.setJob("UI设计师");
        userBean7.setPersonalSign("一杯茶品人生沉浮，平常心越万千世界。");
        userBean7.setTel("15515269676");
        userBean7.setRank(3);
        userBean7.setProjectGroup("河南大学软件中心");
        userBean7.setAverageWeek(82.0);
        userBean7.setAverageMonth(83.2);
        userBean7.setAverageQuarter(84.0);
        userBean7.setAverageYear(82.5);
        userBean7.setHeadPic("http://img8.d.cn/be/image/1502/j32d0i5w2nuh4.png");
        userBean7.save();

        UserBean userBean8 = new UserBean();
        userBean8.setRealName("查尔斯");
        userBean8.setEmail("charles@littlebird.com");
        userBean8.setPassword("123456");
        userBean8.setAdmin(false);
        long registerTime8 = getRegisterTime(8, 2, 7, 2014);
        userBean8.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime8)));
        userBean8.setDepartment("技术部");
        userBean8.setEntryTime("2013年07月02日");
        userBean8.setIntegral(586);
        userBean8.setJob("项目经理");
        userBean8.setPersonalSign("以我所能尽你所愿");
        userBean8.setTel("15515269677");
        userBean8.setRank(2);
        userBean8.setProjectGroup("河南大学软件中心");
        userBean8.setAverageWeek(86.0);
        userBean8.setAverageMonth(80.2);
        userBean8.setAverageQuarter(83.0);
        userBean8.setAverageYear(85.2);
        userBean8.setHeadPic("http://n.sinaimg.cn/transform/20150918/toqd-fxhytwr2165853.jpg");
        userBean8.save();

        UserBean userBean9 = new UserBean();
        userBean9.setRealName("李逍遥");
        userBean9.setEmail("lxy@littlebird.com");
        userBean9.setPassword("123456");
        userBean9.setAdmin(false);
        long registerTime9 = getRegisterTime(8, 9, 11, 2016);
        userBean9.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime9)));
        userBean9.setDepartment("技术部");
        userBean9.setEntryTime("2016年11月09日");
        userBean9.setIntegral(506);
        userBean9.setJob("Web前端工程师");
        userBean9.setPersonalSign("魔非魔道非道 善恶在我心");
        userBean9.setTel("15515269679");
        userBean9.setRank(3);
        userBean9.setProjectGroup("河南大学软件中心");
        userBean9.setAverageWeek(78.0);
        userBean9.setAverageMonth(80.2);
        userBean9.setAverageQuarter(0);
        userBean9.setAverageYear(0);
        userBean9.setHeadPic("http://images.liqucn.com/img/h60/h89/img_localize_1ce1ebe7df3fbf73f170584a226cbb64.png");
        userBean9.save();

        UserBean userBean10 = new UserBean();
        userBean10.setRealName("洛克");
        userBean10.setEmail("locke@littlebird.com");
        userBean10.setPassword("123456");
        userBean10.setAdmin(false);
        long registerTime10 = getRegisterTime(8, 12, 10, 2015);
        userBean10.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime10)));
        userBean10.setDepartment("技术部");
        userBean10.setEntryTime("2015年09月01日");
        userBean10.setIntegral(586);
        userBean10.setJob("Android开发工程师");
        userBean10.setPersonalSign("没有勇气开始，你将继续等待。");
        userBean10.setTel("15515269610");
        userBean10.setRank(2);
        userBean10.setProjectGroup("河南大学软件中心");
        userBean10.setAverageWeek(85.0);
        userBean10.setAverageMonth(86.2);
        userBean10.setAverageQuarter(82.0);
        userBean10.setAverageYear(85.7);
        userBean10.setHeadPic("http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=2912885598529822056631c7e2fa57fd/024f78f0f736afc316008bf7bb19ebc4b7451217.jpg");
        userBean10.save();

        UserBean userBean11 = new UserBean();
        userBean11.setRealName("奥斯卡");
        userBean11.setEmail("oscar@littlebird.com");
        userBean11.setPassword("123456");
        userBean11.setAdmin(false);
        long registerTime11 = getRegisterTime(8, 1,11, 2015);
        userBean11.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime11)));
        userBean11.setDepartment("技术部");
        userBean11.setEntryTime("2015年11月01日");
        userBean11.setIntegral(245);
        userBean11.setJob("IOS开发工程师");
        userBean11.setPersonalSign("见证奇迹的时刻");
        userBean11.setTel("15515269611");
        userBean11.setRank(2);
        userBean11.setProjectGroup("河南大学软件中心");
        userBean11.setAverageWeek(79.0);
        userBean11.setAverageMonth(81.2);
        userBean11.setAverageQuarter(80.0);
        userBean11.setAverageYear(81.2);
        userBean11.setHeadPic("http://diy.qqjay.com/u2/2012/0924/7032b10ffcdfc9b096ac46bde0d2925b.jpg");
        userBean11.save();

        UserBean userBean12 = new UserBean();
        userBean12.setRealName("安妮");
        userBean12.setEmail("anne@littlebird.com");
        userBean12.setPassword("123456");
        userBean12.setAdmin(false);
        long registerTime12 = getRegisterTime(8, 12, 12, 2014);
        userBean12.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime12)));
        userBean12.setDepartment("技术部");
        userBean12.setEntryTime("2014年12月01日");
        userBean12.setIntegral(286);
        userBean12.setJob("java工程师");
        userBean12.setPersonalSign("All time is no time when it is past.");
        userBean12.setTel("15515269671");
        userBean12.setRank(3);
        userBean12.setProjectGroup("河南大学软件中心");
        userBean12.setAverageWeek(80.0);
        userBean12.setAverageMonth(82.2);
        userBean12.setAverageQuarter(81.0);
        userBean12.setAverageYear(83.7);
        userBean12.setHeadPic("http://www.feizl.com/upload2007/2011_04/1104031701511610.jpg");
        userBean12.save();
    }
    //  初始化积分数据
    public static void initIntegral(){
        IntegralBean integralBean=new IntegralBean();
        integralBean.setEmail("zhpan@littlebird.com");
        integralBean.setDate("2016/12/19");
        integralBean.setHowGet("签到获取积分");
        integralBean.setIntegral(2);
        integralBean.save();

        IntegralBean integralBean0=new IntegralBean();
        integralBean0.setEmail("zhpan@littlebird.com");
        integralBean0.setDate("2016/12/20");
        integralBean0.setHowGet("签到获取积分");
        integralBean0.setIntegral(2);
        integralBean0.save();

        IntegralBean integralBean1=new IntegralBean();
        integralBean1.setEmail("zhpan@littlebird.com");
        integralBean1.setDate("2016/12/22");
        integralBean1.setHowGet("签到获取积分");
        integralBean1.setIntegral(2);
        integralBean1.save();

        IntegralBean integralBean2=new IntegralBean();
        integralBean2.setEmail("zhpan@littlebird.com");
        integralBean2.setDate("2016/12/22");
        integralBean2.setHowGet("签到获取积分");
        integralBean2.setIntegral(2);
        integralBean2.save();

        IntegralBean integralBean3=new IntegralBean();
        integralBean3.setEmail("zhpan@littlebird.com");
        integralBean3.setDate("2016/12/23");
        integralBean3.setHowGet("签到获取积分");
        integralBean3.setIntegral(2);
        integralBean3.save();

        IntegralBean integralBean4=new IntegralBean();
        integralBean4.setEmail("zhpan@littlebird.com");
        integralBean4.setDate("2016/12/24");
        integralBean4.setHowGet("签到获取积分");
        integralBean4.setIntegral(2);
        integralBean4.save();

        IntegralBean integralBean5=new IntegralBean();
        integralBean5.setEmail("zhpan@littlebird.com");
        integralBean5.setDate("2016/12/25");
        integralBean5.setHowGet("迟到扣除");
        integralBean5.setIntegral(-1);
        integralBean5.save();

        IntegralBean integralBean6=new IntegralBean();
        integralBean6.setEmail("zhpan@littlebird.com");
        integralBean6.setDate("2016/12/26");
        integralBean6.setHowGet("签到获取积分");
        integralBean6.setIntegral(2);
        integralBean6.save();

        IntegralBean integralBean7=new IntegralBean();
        integralBean7.setEmail("zhpan@littlebird.com");
        integralBean7.setDate("2016/12/27");
        integralBean7.setHowGet("签到获取积分");
        integralBean7.setIntegral(2);
        integralBean7.save();

        IntegralBean integralBean8=new IntegralBean();
        integralBean8.setEmail("zhpan@littlebird.com");
        integralBean8.setDate("2016/12/28");
        integralBean8.setHowGet("签到获取积分");
        integralBean8.setIntegral(2);
        integralBean8.save();

        IntegralBean integralBean9=new IntegralBean();
        integralBean9.setEmail("zhpan@littlebird.com");
        integralBean9.setDate("2016/12/29");
        integralBean9.setHowGet("签到获取积分");
        integralBean9.setIntegral(2);
        integralBean9.save();

        IntegralBean integralBean10=new IntegralBean();
        integralBean10.setEmail("zhpan@littlebird.com");
        integralBean10.setDate("2016/12/30");
        integralBean10.setHowGet("签到获取积分");
        integralBean10.setIntegral(2);
        integralBean10.save();

        IntegralBean integralBean11=new IntegralBean();
        integralBean11.setEmail("zhpan@littlebird.com");
        integralBean11.setDate("2016/12/31");
        integralBean11.setHowGet("迟到扣除");
        integralBean11.setIntegral(-1);
        integralBean11.save();

        IntegralBean integralBean12=new IntegralBean();
        integralBean12.setEmail("zhpan@littlebird.com");
        integralBean12.setDate("2017/01/01");
        integralBean12.setHowGet("签到获取积分");
        integralBean12.setIntegral(2);
        integralBean12.save();

        IntegralBean integralBean13=new IntegralBean();
        integralBean13.setEmail("zhpan@littlebird.com");
        integralBean13.setDate("2017/01/02");
        integralBean13.setHowGet("签到获取积分");
        integralBean13.setIntegral(2);
        integralBean13.save();

    }
    //  初始化奖励
    public static void initEncourage(){
        EncourageBean encourageBean=new EncourageBean();
        encourageBean.setEmail("zhpan@littlebird.com");
        encourageBean.setDate("2016年12月");
        encourageBean.setHowGet("员工之星");
        encourageBean.setIntegral(200);
        encourageBean.save();

        EncourageBean encourageBean1=new EncourageBean();
        encourageBean1.setEmail("zhpan@littlebird.com");
        encourageBean1.setDate("2016年3月");
        encourageBean1.setHowGet("全勤奖");
        encourageBean1.setIntegral(100);
        encourageBean1.save();

        EncourageBean encourageBean2=new EncourageBean();
        encourageBean2.setEmail("zhpan@littlebird.com");
        encourageBean2.setDate("2016年1月");
        encourageBean2.setHowGet("全勤奖");
        encourageBean2.setIntegral(100);
        encourageBean2.save();

        EncourageBean encourageBean3=new EncourageBean();
        encourageBean3.setEmail("zhpan@littlebird.com");
        encourageBean3.setDate("2015年7月");
        encourageBean3.setHowGet("员工之星");
        encourageBean3.setIntegral(200);
        encourageBean3.save();

        EncourageBean encourageBean4=new EncourageBean();
        encourageBean4.setEmail("zhpan@littlebird.com");
        encourageBean4.setDate("2015年4月");
        encourageBean4.setHowGet("优秀员工");
        encourageBean4.setIntegral(300);
        encourageBean4.save();
    }
    //  初始化话题
    public static void initTopic(){

        TopicBean topicBean0=new TopicBean();
        topicBean0.setEmail("stewart@littlebird.com");
        topicBean0.setContent("冰川，那是我最美丽的遐想。");
        topicBean0.setDate(System.currentTimeMillis()-24*60*60*1000*4);
        topicBean0.save();


        TopicBean topicBean=new TopicBean();

        LikeBean likeBean=new LikeBean();
        likeBean.setName("费奥纳");
        likeBean.setEmail("fan@littlebird.com");
        likeBean.setTopicBean(topicBean);
        likeBean.save();

        LikeBean likeBean1=new LikeBean();
        likeBean1.setName("安妮");
        likeBean1.setEmail("anne@littlebird.com");
        likeBean.setTopicBean(topicBean);
        likeBean1.save();

        LikeBean likeBean2=new LikeBean();
        likeBean2.setName("洛克");
        likeBean2.setEmail("locke@littlebird.com");
        likeBean.setTopicBean(topicBean);
        likeBean2.save();

        CommentBean commentBean=new CommentBean();
        commentBean.setContent("哎呀，好文艺！");
        commentBean.setEmail("fan@littlebird.com");
        commentBean.setName("费奥纳");
        commentBean.setTopicBean(topicBean);
        commentBean.save();
        CommentBean commentBean2=new CommentBean();
        commentBean2.setContent("哈哈，赞一个！");
        commentBean2.setEmail("anne@littlebird.com");
        commentBean2.setName("安妮");
        commentBean2.setTopicBean(topicBean);
        commentBean2.save();

        topicBean.setEmail("hellen@littlebird.com");
        topicBean.setContent("一杯茶品人生沉浮，平常心越万千世界。");
        topicBean.setDate(System.currentTimeMillis()-24*60*60*1000*2);
        topicBean.getLike().add(likeBean);
        topicBean.getLike().add(likeBean1);
        topicBean.getLike().add(likeBean2);
        topicBean.getComment().add(commentBean);
        topicBean.getComment().add(commentBean2);
        topicBean.save();
    }
    //  初始化公告数据
    public static void initNotice(){
        NoticeBean noticeBean=new NoticeBean();
        noticeBean.setTitle("2017年春节放假公告");
        noticeBean.setDate("2017-12-25");
        noticeBean.setUrl("");
        noticeBean.save();

        NoticeBean noticeBean1=new NoticeBean();
        noticeBean1.setTitle("2017年元旦放假通知");
        noticeBean1.setDate("2017-11-22");
        noticeBean1.setUrl("");
        noticeBean1.save();
        NoticeBean noticeBean2=new NoticeBean();
        noticeBean2.setTitle("");
        noticeBean2.setDate("2017-12-25");
        noticeBean2.setUrl("");
        noticeBean2.save();
        NoticeBean noticeBean3=new NoticeBean();
        noticeBean3.setTitle("表彰公告");
        noticeBean3.setDate("2017-11-17");
        noticeBean3.setUrl("");
        noticeBean3.save();
        NoticeBean noticeBean4=new NoticeBean();
        noticeBean4.setTitle("");
        noticeBean4.setDate("2017-11-08");
        noticeBean4.setUrl("1017年11月工资发放通知");
        noticeBean4.save();
        NoticeBean noticeBean5=new NoticeBean();
        noticeBean5.setTitle("关于员工餐补通知");
        noticeBean5.setDate("2017-10-25");
        noticeBean5.setUrl("");
        noticeBean5.save();
        NoticeBean noticeBean6=new NoticeBean();
        noticeBean6.setTitle("");
        noticeBean6.setDate("2017-12-25");
        noticeBean6.setUrl("");
        noticeBean6.save();
        NoticeBean noticeBean7=new NoticeBean();
        noticeBean7.setTitle("");
        noticeBean7.setDate("2017-12-25");
        noticeBean7.setUrl("");
        noticeBean7.save();
        NoticeBean noticeBean8=new NoticeBean();
        noticeBean8.setTitle("");
        noticeBean8.setDate("2017-12-25");
        noticeBean8.setUrl("");
        noticeBean8.save();
        NoticeBean noticeBean9=new NoticeBean();
        noticeBean9.setTitle("");
        noticeBean9.setDate("2017-12-25");
        noticeBean9.setUrl("");
        noticeBean9.save();
        NoticeBean noticeBean10=new NoticeBean();
        noticeBean10.setTitle("");
        noticeBean10.setDate("2017-12-25");
        noticeBean10.setUrl("");
        noticeBean10.save();
        NoticeBean noticeBean11=new NoticeBean();
        noticeBean11.setTitle("");
        noticeBean11.setDate("2017-12-25");
        noticeBean11.setUrl("");
        noticeBean11.save();

    }

    private static long getRegisterTime(int hour, int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.YEAR, year);
        return (cal.getTimeInMillis() / 1000);
    }
}


