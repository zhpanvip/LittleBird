package project.graduate.zhpan.littlebird.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhpan on 2016/12/13.
 */

public class DateUtils {

    //  星期
    private static String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    //  农历月份
    private static String[] lunarMonth = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    //  农历日
    private static String[] lunarDay = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
            "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
            "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    /**
     * 获得当天time点时间戳
     */
    public static long getSignTime(int time) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, time);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() / 1000);
    }

    /**
     * 格式化时间 hh:mm:ss
     */
    public static String timeFormat(long timeStamp) {
        long time=Math.abs(timeStamp);
        int hours = (int) time / 3600;
        String hourStr;
        if (hours < 10) {
            hourStr = "0" + hours;

        } else {
            hourStr = hours + "";
        }
        int min = (int) (time - hours * 3600) / 60;
        String minStr;
        if (min < 10) {
            minStr = "0" + min;

        } else {
            minStr = min + "";
        }
        int second = (int) (time - (time / 60) * 60);
        String secondStr;
        if (second < 10) {
            secondStr = "0" + second;

        } else {
            secondStr = second + "";
        }
        String timeStr = (hourStr + ":" + minStr + ":" + secondStr);

        return timeStr;
    }




    /**
     * 获取年月日 格式yyyy-MM-dd
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }


    /**
     * 获取年、月 格式 yyyy-MM
     *
     * @return
     */
    public static String getMonth() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String month = simpleDateFormat.format(date);
        return month;
    }

    /**
     * 获取当月日期
     * @return Day of month
     */
    public static String getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day+"";
    }

    /**
     * 获取星期几
     */
    public static String getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return week[dayOfWeek - 1];
    }

    /**
     * 获取农历月份
     * @return
     */
    public static String getLunarMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        return lunarMonth[lunarDate[1] - 1];
    }

    /**
     * 获取农历日
     * @return
     */
    public static String getLunarDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        return lunarDay[lunarDate[2] - 1];
    }

    /**
     * 日期转时间戳
     * @param time 日期 格式yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static long date2TimeStamp(String time){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        return timestamp;
    }

    /**
     * 获取年月日时分 格式yyyy-MM-dd HH:mm
     */
    public static String timeStampToExactlyDate(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date d = new Date(timeStamp);
        return simpleDateFormat.format(d);
    }

    public static String getDetailedTime(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date d = new Date(timeStamp);
        return simpleDateFormat.format(d);
    }

    public static String formatTopicDate(long timeStamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        Date d = new Date(timeStamp);
        return simpleDateFormat.format(d);
    }

}
