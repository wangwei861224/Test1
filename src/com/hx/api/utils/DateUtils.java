package com.hx.api.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2010 All Rights Reserved. js_todaysoft 版权所有</p>
 * <p>Company: cn.com.todaysoft.js</p>
 * @author xuxin
 * @version 1.0
 * @Date 2011-11-26 下午2:40:10
 */
public abstract class DateUtils
{
    private static final SimpleDateFormat DB_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
      * <p>将日期格式化为yyyyMMddHHmmss的格式，作为Long型存入数据库</p>
      * @param date 日期对象
      * @return yyyyMMddHHmmss格式的日期值
     */
    public static Long format(Date date)
    {
        return Long.parseLong(DB_FORMAT.format(date));
    }
    
    /**
      * <p>将数据库中yyyyMMddHHmmss格式的值解析为日期对象</p>
      * @param date yyyyMMddHHmmss格式的日期值
      * @return 日期对象
     */
    public static Date parse(Long date)
    {
        try
        {
            return DB_FORMAT.parse(String.valueOf(date));
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
      * <p>将数据库中yyyyMMddHHmmss格式的值格式化为指定格式的字符串</p>
      * @param date yyyyMMddHHmmss格式的日期值
      * @param pattern 指定的格式
      * @return 格式化之后的日期值
     */
    public static String format(Long date, String pattern)
    {
        try
        {
            Date d = DB_FORMAT.parse(String.valueOf(date));
            return format(d, pattern);
        }
        catch (ParseException e)
        {
            return "";
        }
    }
    
    /**
      * <p>格式化日期对象</p>
      * @param date 日期对象
      * @param pattern 格式化的格式
      * @return 格式化之后的值
     */
    public static String format(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * 默认转化成yyyy-MM-dd类型
     * <一句话功能简述>
     * <功能详细描述>
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String formatDate(Date date)
    {
        return format(date, "yyyy-MM-dd");
    }
    
    /**
      * <p>将日期转换为当天的开始时间。即时分秒设置为00:00:00</p>
      * @param date 日期对象
      * @return 当天的开始时间
     */
    public static Date toStartDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    /**
      * <p>将日期转换为当天的结束时间。即时分秒设置为23:59:59</p>
      * @param date 日期对象
      * @return 当天的结束时间
     */
    public static Date toEndDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
    /**
     * 
      * <p>Description:</p>
      * @param date 日期对象
      * @param time 即时分秒设置为23:59:59
      * @return
      * @author YouShengRong
      * @date 2012-3-8 下午5:11:03
     */
    public static Date toDateTime(Date date, String time)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        time = StringUtils.trimToNull(time);
        calendar.set(Calendar.HOUR_OF_DAY, NumberUtils.toInt(StringUtils.split(time, ":")[0], 0));
        calendar.set(Calendar.MINUTE, NumberUtils.toInt(StringUtils.split(time, ":")[1], 0));
        calendar.set(Calendar.SECOND, NumberUtils.toInt(StringUtils.split(time, ":")[2], 0));
        return calendar.getTime();
    }
    
    /**
      * <p>返回两个日期之间相差的天数</p>
      * @param base 比较基准日期
      * @param date 比较日期
      * @return 基准日期与指定日期之间相差的天数
     */
    public static int getInterval(Date base, Date date)
    {
        GregorianCalendar baseCalendar = new GregorianCalendar();
        GregorianCalendar calendar = new GregorianCalendar();
        baseCalendar.setTime(base);
        calendar.setTime(date);
        return (int)((calendar.getTimeInMillis() - baseCalendar.getTimeInMillis()) / (1000 * 3600 * 24));
    }
    
    /**
      * <p>返回指定日期经过相应属性的间隔之后的日期对象</p>
      * @param base 基准时间
      * @param field Calendar中相应的属性
      * @param interval 间隔
      * @return 指定间隔后的日期
     */
    public static Date getDate(Date base, int field, int interval)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
        calendar.set(field, calendar.get(field) + interval);
        return calendar.getTime();
    }
    
    public static String getDayOfWeekText(int dayOfWeek)
    {
        String text = "周日";
        
        switch (dayOfWeek)
        {
            case Calendar.MONDAY:
                text = "周一";
                break;
            case Calendar.TUESDAY:
                text = "周二";
                break;
            case Calendar.WEDNESDAY:
                text = "周三";
                break;
            case Calendar.THURSDAY:
                text = "周四";
                break;
            case Calendar.FRIDAY:
                text = "周五";
                break;
            case Calendar.SATURDAY:
                text = "周六";
                break;
            default:
                text = "周日";
                break;
        }
        
        return text;
    }
    
    public static String getTime(Long millseconds)
    {
        String time = "";
        if (millseconds == null)
        {
            return "";
        }
        int days = (int)millseconds.longValue() / 1000 / 60 / 60 / 24;
        if (days > 0)
        {
            time = time + days + "天";
        }
        long hourMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24;
        int hours = (int)hourMillseconds / 1000 / 60 / 60;
        if (hours > 0)
        {
            time = time + hours + "小时";
        }
        long minuteMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24 - hours * 1000 * 60 * 60;
        int minutes = (int)minuteMillseconds / 1000 / 60;
        if (minutes > 0)
        {
            time = time + minutes + "分钟";
        }
        else
        {
            int seconds = (int)minuteMillseconds / 1000;
            if (seconds > 0)
            {
                time = seconds + "秒";
            }
        }
        
        return time;
    }
    
    public static Date stringToDate(String strDate, String pattern)
    {
        Date date = null;
        DateFormat df = new SimpleDateFormat(pattern);
        try
        {
            date = df.parse(strDate);
        }
        catch (ParseException e)
        {
            return null;
        }
        return date;
    }
    
    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek()
    {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }
    
    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek()
    {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }
    
    /**
    * 得到上个月的第一和最后一天带时间
    *
    * @return MAP{prevMonthFD:当前日期上个月的第一天带时间}{prevMonthPD:当前日期上个月的最后一天带时间}
    */
    public static Map<String, String> findLastMonth()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first_prevM = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first_prevM);
        day_first_prevM = str.toString();
        
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        String day_end_prevM = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_end_prevM);
        day_end_prevM = endStr.toString();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("prevMonthFD", day_first_prevM);
        map.put("prevMonthPD", day_end_prevM);
        return map;
    }
    
    //得到当前日期的后几天
    public static Date getNextDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }
    
    //得到当月第一天 和最后一天
    public static Map<String, Date> findNowMonth()
    {
        Calendar cal = Calendar.getInstance();
        
        //SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        //当前月的最后一天     
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();
        //String endTime1 = datef.format(endTime);
        //当前月的第一天            
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        Date beginTime = cal.getTime();
        //String beginTime1 = datef.format(beginTime);
        
        Map<String, Date> map = new HashMap<String, Date>();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return map;
    }
    
    //时间段的周几
    public static List<String> getWeekArray(String beginDateStr, String endDateStr)
    {
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weeks = dfs.getWeekdays();
        List<String> weekList = new ArrayList<String>();
        int year = Integer.valueOf(beginDateStr.substring(0, 4));
        int month = Integer.valueOf(beginDateStr.substring(5, 7)) - 1;
        int dayBegin = Integer.valueOf(beginDateStr.substring(8, 10));
        int dayEnd = Integer.valueOf(endDateStr.substring(8, 10));
        c_begin.set(year, month, dayBegin); //Calendar的月从0-11，所以4月是3.
        c_end.set(year, month, dayEnd); //Calendar的月从0-11，所以5月是4.        
        c_end.add(Calendar.DAY_OF_YEAR, 1); //结束日期下滚一天是为了包含最后一天
        while (c_begin.before(c_end))
        {
            weekList.add(weeks[c_begin.get(Calendar.DAY_OF_WEEK)].substring(2, 3));
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        return weekList;
    }
    
    /**
     * 根据日期,返回星期几 范围1-7,1=星期日,7=星期六
     * @param date
     * @return
     */
    public static int getWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 取得某个日期时本月的第几天
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
