package project.graduate.zhpan.littlebird.app;

import java.util.Calendar;
import java.util.Date;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.DateUtils;

/**
 * Created by zhpan on 2017/1/1.
 */
//  初始化数据
public class InitialData {

    public static void initUser() {
        UserBean userBean = new UserBean();
        userBean.setRealName("张攀");
        userBean.setEmail("zhpan@littlebird.com");
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
        userBean.setRank(1);
        userBean.setProjectGroup("河南大学软件中心");
        userBean.setAverageWeek(83.0);
        userBean.setAverageMonth(86.2);
        userBean.setAverageQuarter(85.0);
        userBean.setAverageYear(85.7);
        userBean.save();

        UserBean userBean1 = new UserBean();
        userBean1.setRealName("李鸣");
        userBean1.setEmail("liming@littlebird.com");
        userBean1.setPassword("123456");
        userBean1.setAdmin(false);
        long registerTime1 = getRegisterTime(8, 12, 6, 2015);
        userBean1.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime1)));
        userBean1.setDepartment("人事部");
        userBean1.setEntryTime("2015年06月12日");
        userBean1.setIntegral(56);
        userBean1.setJob("HR");
        userBean1.setPersonalSign("一直被模仿 从未被超越");
        userBean1.setTel("15515643648");
        userBean1.setRank(8);
        userBean1.setProjectGroup("河南大学软件中心");
        userBean1.setAverageWeek(19.0);
        userBean1.setAverageMonth(82.2);
        userBean1.setAverageQuarter(84.0);
        userBean1.setAverageYear(81.8);
        userBean1.save();

        UserBean userBean2 = new UserBean();
        userBean2.setRealName("赵洪");
        userBean2.setEmail("zhaohong@littlebird.com");
        userBean2.setPassword("123456");
        userBean2.setAdmin(false);
        long registerTime2 = getRegisterTime(8, 12, 2, 2013);
        userBean2.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime2)));
        userBean2.setDepartment("技术部");
        userBean2.setEntryTime("2013年09月01日");
        userBean2.setIntegral(586);
        userBean2.setJob("android开发工程师");
        userBean2.setPersonalSign("一直被模仿 从未被超越");
        userBean2.setTel("15515269672");
        userBean2.setRank(2);
        userBean2.setProjectGroup("河南大学软件中心");
        userBean2.setAverageWeek(86.6);
        userBean2.setAverageMonth(82.1);
        userBean2.setAverageQuarter(86.0);
        userBean2.setAverageYear(85.1);
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
        userBean3.setPersonalSign("一直被模仿 从未被超越");
        userBean3.setTel("15515269673");
        userBean3.setRank(3);
        userBean3.setProjectGroup("河南大学软件中心");
        userBean3.setAverageWeek(80.1);
        userBean3.setAverageMonth(84.5);
        userBean3.setAverageQuarter(81.6);
        userBean3.setAverageYear(81.7);
        userBean3.save();

        UserBean userBean4 = new UserBean();
        userBean4.setRealName("斯图尔特");
        userBean4.setEmail("stet@littlebird.com");
        userBean4.setPassword("123456");
        userBean4.setAdmin(false);
        long registerTime4 = getRegisterTime(8, 1, 4, 2016);
        userBean4.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime4)));
        userBean4.setDepartment("技术部");
        userBean4.setEntryTime("2013年04月01日");
        userBean4.setIntegral(103);
        userBean4.setJob("项目经理");
        userBean4.setPersonalSign("一直被模仿 从未被超越");
        userBean4.setTel("15515269674");
        userBean4.setRank(4);
        userBean4.setProjectGroup("河南大学软件中心");
        userBean4.setAverageWeek(81.0);
        userBean4.setAverageMonth(79.0);
        userBean4.setAverageQuarter(78.0);
        userBean4.setAverageYear(80.9);
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
        userBean5.setPersonalSign("一直被模仿 从未被超越");
        userBean5.setTel("15515269675");
        userBean5.setRank(5);
        userBean5.setProjectGroup("河南大学软件中心");
        userBean5.setAverageWeek(84.0);
        userBean5.setAverageMonth(82.6);
        userBean5.setAverageQuarter(81.3);
        userBean5.setAverageYear(84.2);
        userBean5.save();

        UserBean userBean6 = new UserBean();
        userBean6.setRealName("菲奥娜");
        userBean6.setEmail("fan@littlebird.com");
        userBean6.setPassword("123456");
        userBean6.setAdmin(false);
        long registerTime6 = getRegisterTime(8, 1, 1, 2017);
        userBean6.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime6)));
        userBean6.setDepartment("技术部");
        userBean6.setEntryTime("2013年09月01日");
        userBean6.setIntegral(586);
        userBean6.setJob("Web前端工程师");
        userBean6.setPersonalSign("一直被模仿 从未被超越");
        userBean6.setTel("15515269670");
        userBean6.setRank(6);
        userBean6.setProjectGroup("河南大学软件中心");
        userBean6.setAverageWeek(82.1);
        userBean6.setAverageMonth(82.4);
        userBean6.setAverageQuarter(86.0);
        userBean6.setAverageYear(84.1);
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
        userBean7.setPersonalSign("一直被模仿 从未被超越");
        userBean7.setTel("15515269676");
        userBean7.setRank(6);
        userBean7.setProjectGroup("河南大学软件中心");
        userBean7.setAverageWeek(82.0);
        userBean7.setAverageMonth(83.2);
        userBean7.setAverageQuarter(84.0);
        userBean7.setAverageYear(85.7);
        userBean7.save();

        UserBean userBean8 = new UserBean();
        userBean8.setRealName("郑好");
        userBean8.setEmail("zhenghao@littlebird.com");
        userBean8.setPassword("123456");
        userBean8.setAdmin(false);
        long registerTime8 = getRegisterTime(8, 2, 7, 2014);
        userBean8.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime8)));
        userBean8.setDepartment("技术部");
        userBean8.setEntryTime("2013年07月02日");
        userBean8.setIntegral(586);
        userBean8.setJob("项目经理");
        userBean8.setPersonalSign("一直被模仿 从未被超越");
        userBean8.setTel("15515269677");
        userBean8.setRank(7);
        userBean8.setProjectGroup("河南大学软件中心");
        userBean8.setAverageWeek(86.0);
        userBean8.setAverageMonth(80.2);
        userBean8.setAverageQuarter(83.0);
        userBean8.setAverageYear(85.2);
        userBean8.save();

        UserBean userBean9 = new UserBean();
        userBean9.setRealName("李逍遥");
        userBean9.setEmail("lxy@littlebird.com");
        userBean9.setPassword("123456");
        userBean9.setAdmin(false);
        long registerTime9 = getRegisterTime(8, 9, 7, 2013);
        userBean9.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime9)));
        userBean9.setDepartment("技术部");
        userBean9.setEntryTime("2013年07月09日");
        userBean9.setIntegral(506);
        userBean9.setJob("Web前端工程师");
        userBean9.setPersonalSign("一直被模仿 从未被超越");
        userBean9.setTel("15515269679");
        userBean9.setRank(9);
        userBean9.setProjectGroup("河南大学软件中心");
        userBean9.setAverageWeek(78.0);
        userBean9.setAverageMonth(80.2);
        userBean9.setAverageQuarter(79.0);
        userBean9.setAverageYear(80.7);
        userBean9.save();

        UserBean userBean10 = new UserBean();
        userBean10.setRealName("齐孟尧");
        userBean10.setEmail("qmy@littlebird.com");
        userBean10.setPassword("123456");
        userBean10.setAdmin(false);
        long registerTime10 = getRegisterTime(8, 12, 10, 2015);
        userBean10.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime10)));
        userBean10.setDepartment("技术部");
        userBean10.setEntryTime("2015年09月01日");
        userBean10.setIntegral(586);
        userBean10.setJob("Android开发工程师");
        userBean10.setPersonalSign("一直被模仿 从未被超越");
        userBean10.setTel("15515269610");
        userBean10.setRank(10);
        userBean10.setProjectGroup("河南大学软件中心");
        userBean10.setAverageWeek(85.0);
        userBean10.setAverageMonth(86.2);
        userBean10.setAverageQuarter(82.0);
        userBean10.setAverageYear(85.7);
        userBean10.save();

        UserBean userBean11 = new UserBean();
        userBean11.setRealName("郭小鹏");
        userBean11.setEmail("gxp@littlebird.com");
        userBean11.setPassword("123456");
        userBean11.setAdmin(false);
        long registerTime11 = getRegisterTime(8, 1,11, 2015);
        userBean11.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime11)));
        userBean11.setDepartment("技术部");
        userBean11.setEntryTime("2015年11月01日");
        userBean11.setIntegral(245);
        userBean11.setJob("IOS开发工程师");
        userBean11.setPersonalSign("一直被模仿 从未被超越");
        userBean11.setTel("15515269611");
        userBean11.setRank(11);
        userBean11.setProjectGroup("河南大学软件中心");
        userBean11.setAverageWeek(79.0);
        userBean11.setAverageMonth(81.2);
        userBean11.setAverageQuarter(80.0);
        userBean11.setAverageYear(81.2);
        userBean11.save();

        UserBean userBean12 = new UserBean();
        userBean12.setRealName("王飞龙");
        userBean12.setEmail("wfl@littlebird.com");
        userBean12.setPassword("123456");
        userBean12.setAdmin(false);
        long registerTime12 = getRegisterTime(8, 12, 12, 2014);
        userBean12.setUserId(Long.parseLong(DateUtils.getDetailedTime(registerTime12)));
        userBean12.setDepartment("技术部");
        userBean12.setEntryTime("2014年12月01日");
        userBean12.setIntegral(286);
        userBean12.setJob("java工程师");
        userBean12.setPersonalSign("一直被模仿 从未被超越");
        userBean12.setTel("15515269671");
        userBean12.setRank(5);
        userBean12.setProjectGroup("河南大学软件中心");
        userBean12.setAverageWeek(80.0);
        userBean12.setAverageMonth(82.2);
        userBean12.setAverageQuarter(81.0);
        userBean12.setAverageYear(83.7);
        userBean12.save();
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


