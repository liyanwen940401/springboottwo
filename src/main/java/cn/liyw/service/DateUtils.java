package cn.liyw.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author yuhuitao
 * @version 1.0
 */
public class DateUtils {
    public static long ONE_DAY_MILLIS = 24 * 3600 * 1000;

    /**
     * 得到几分种前的时间,时间格式为"yyyy-MM-dd HH:mm:00"
     *
     * @param minute
     */
    public static String getBeforeMinuteDateTime(final Integer minute) {
        final Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        cal.add(Calendar.MINUTE, -minute);// 取当前日期的前一天.
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        return format.format(cal.getTime());
    }

    /**
     * 得到几秒种前的时间,时间格式为"yyyy-MM-dd HH:mm:ss"
     *
     * @param seconds
     */
    public static String getBeforeSecondDateTime(final Integer seconds) {
        final Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        cal.add(Calendar.SECOND, -seconds);// 取当前日期的前几秒.
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(cal.getTime());

    }


    /**
     *
     * @param seconds
     */
    public static String getFistDayForMonth(final Integer seconds,String formatStr) {
        final Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        cal.setTime(new Date()); // 设置为当前时间
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - seconds); // 设置为上几个月
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(cal.getTime());
    }



    public static Long getBeforeDays(final Integer days) {
        final Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, -days);
        return cal.getTimeInMillis();
    }

    /**
     * 　　 *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        cal.setTime(format.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(format.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 将时间格式化为自定义格式字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String date2DateStr(Date date, String formatStr) {
        return new SimpleDateFormat(formatStr).format(date);
    }
    public static String date2DateStr(long dateTime) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }
    public static Date dateStr2Date(String dateStr) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static long getTimeInMilSecond(String str) {
        try {
            return dateStr2Date(str).getTime();
        } catch (Throwable th) {
            return 0;
        }
    }
    public static void main(String[] args) {
        System.out.println(getBeforeMinuteDateTime(0));
    }

}
