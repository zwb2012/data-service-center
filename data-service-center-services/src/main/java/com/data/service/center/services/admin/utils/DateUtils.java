package com.data.service.center.services.admin.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 日期工具类
 *
 * @author 志
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyyMMddHHmmss
     */
    public static final String FORMAT2 = "yyyyMMddHHmmss";

    /**
     * yyyy-MM-dd
     */
    public static final String FORMAT3 = "yyyy-MM-dd";

    /**
     * yyyyMMdd
     */
    public static final String FORMAT4 = "yyyyMMdd";

    private static final String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", FORMAT2, "HHmmss"};

    private static final String DAY = "天";

    private static final String HOUR = "时";

    private static final String MINUTE = "分";

    private static final String SECOND = "秒";

    private static final long ONE_DAY = 86400;

    private static final long ONE_HOUR = 3600;

    private static final long ONE_MINUTE = 60;

    private static final String[] WEEKS_CN = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    public static DateTimeFormatter DATE_TIME_FORMAT_HHMM = DateTimeFormatter.ofPattern("HH:mm");

    private static final long ND = 1000 * 24 * 60 * 60;
    private static final long NH = 1000 * 60 * 60;
    private static final long NM = 1000 * 60;

    private static final long MILLISECONDS_PER_HOUR = 3600000L;

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 得到星期
     *
     * @param date yyyy-MM-d
     * @return
     */
    public static String getWeek(String date) {
        return formatDate(DateUtils.string2Date(date, FORMAT3), "E");
    }

    /**
     * 获取date是周几
     *
     * @param date yyyy-MM-dd
     * @return "周一","周二","周三","周四","周五","周六","周日"
     */
    public static String getWeekCN(String date) {
        DayOfWeek dayOfWeek = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).getDayOfWeek();
        int index = dayOfWeek.getValue() - 1;
        return WEEKS_CN[index];
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateStr + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将date转成string
     *
     * @param date       需转换的日期
     * @param dateFormat 日期格式 如yyyy-MM-dd HH:mm:ss S
     * @return
     */
    public static String date2String(Date date, String dateFormat) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            return df.format(date);
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将String类型的日期转成date
     *
     * @param date
     * @return
     */
    public static Date string2Date(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static Integer StrToInt(String accoTime) {
        int i = 0;
        if (accoTime.equals("ZERO")) {
            i = 0;
        } else if (accoTime.equals("ONE")) {
            i = 1;
        } else if (accoTime.equals("TWO")) {
            i = 2;
        } else if (accoTime.equals("THREE")) {
            i = 3;
        } else if (accoTime.equals("FOUR")) {
            i = 4;
        } else if (accoTime.equals("FIVE")) {
            i = 5;
        } else if (accoTime.equals("SIX")) {
            i = 6;
        } else if (accoTime.equals("SEVEN")) {
            i = 7;
        } else if (accoTime.equals("EIGHT")) {
            i = 8;
        } else if (accoTime.equals("NINE")) {
            i = 9;
        } else if (accoTime.equals("TEN")) {
            i = 10;
        } else if (accoTime.equals("ELEVEN")) {
            i = 11;
        } else if (accoTime.equals("TWELVE")) {
            i = 12;
        } else if (accoTime.equals("THIRTEEN")) {
            i = 13;
        } else if (accoTime.equals("FOURTEEN")) {
            i = 14;
        } else if (accoTime.equals("FIFTEEN")) {
            i = 15;
        } else if (accoTime.equals("SIXTEEN")) {
            i = 16;
        } else if (accoTime.equals("SEVENTEEN")) {
            i = 17;
        } else if (accoTime.equals("EIGHTEEN")) {
            i = 18;
        } else if (accoTime.equals("NINETEEN")) {
            i = 19;
        } else if (accoTime.equals("TWENTY")) {
            i = 20;
        } else if (accoTime.equals("TWENTYONE")) {
            i = 21;
        } else if (accoTime.equals("TWENTYTWO")) {
            i = 22;
        } else if (accoTime.equals("TWENTYTHREE")) {
            i = 23;
        }
        return i;
    }

    public static String StrToBigStr(String accoTime) {
        String i = "ZERO";
        if (accoTime.equals("0")) {
            i = "ZERO";
        } else if (accoTime.equals("1")) {
            i = "ONE";
        } else if (accoTime.equals("2")) {
            i = "TWO";
        } else if (accoTime.equals("3")) {
            i = "THREE";
        } else if (accoTime.equals("4")) {
            i = "FOUR";
        } else if (accoTime.equals("5")) {
            i = "FIVE";
        } else if (accoTime.equals("6")) {
            i = "SIX";
        } else if (accoTime.equals("7")) {
            i = "SEVEN";
        } else if (accoTime.equals("8")) {
            i = "EIGHT";
        } else if (accoTime.equals("9")) {
            i = "NINE";
        } else if (accoTime.equals("10")) {
            i = "TEN";
        } else if (accoTime.equals("11")) {
            i = "ELEVEN";
        } else if (accoTime.equals("12")) {
            i = "TWELVE";
        } else if (accoTime.equals("13")) {
            i = "THIRTEEN";
        } else if (accoTime.equals("14")) {
            i = "FOURTEEN";
        } else if (accoTime.equals("15")) {
            i = "FIFTEEN";
        } else if (accoTime.equals("16")) {
            i = "SIXTEEN";
        } else if (accoTime.equals("17")) {
            i = "SEVENTEEN";
        } else if (accoTime.equals("18")) {
            i = "EIGHTEEN";
        } else if (accoTime.equals("19")) {
            i = "NINETEEN";
        } else if (accoTime.equals("20")) {
            i = "TWENTY";
        } else if (accoTime.equals("21")) {
            i = "TWENTYONE";
        } else if (accoTime.equals("22")) {
            i = "TWENTYTWO";
        } else if (accoTime.equals("23")) {
            i = "TWENTYTHREE";
        }
        return i;
    }

    /**
     * 计算指定日期是某年的第几周
     *
     * @return int
     * @throws ParseException ParseException
     */
    public static int getWeekNumOfYear(String strDate, String format) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat sformat = new SimpleDateFormat(format);
        Date curDate = sformat.parse(strDate);
        calendar.setTime(curDate);
        int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
        return iWeekNum;
    }

    /**
     * 计算某年某周的开始日期，星期一为每周第一天
     *
     * @return interger
     * @throws ParseException
     */
    public static String getFirstDayOfWeek(int yearNum, int weekNum, String format) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    /**
     * 计算某年某周的结束日期 ，星期日为每周最后一天
     *
     * @return interger
     * @throws ParseException
     */
    public static String getLastDayOfWeek(int yearNum, int weekNum, String format) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat(format).format(cal.getTime());
    }


    /**
     * 计算某年某周某日日期
     */
    public static Date getDayOfWeek(int yearNum, int weekNum, int dayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return cal.getTime();
    }

    /**
     * 计算某年某月某日日期
     */
    public static Date getDayOfMonth(int yearNum, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return cal.getTime();
    }

    /**
     * 计算指定日期所属周的第一天
     *
     * @param date
     * @param format 接收参数和返回数据日期格式
     * @return
     * @throws Exception
     */
    public static String getFirstDayOfWeek(String date, String format) throws ParseException {
        return getFirstDayOfWeek(Integer.valueOf(date.substring(0, 4)), getWeekNumOfYear(date, format), format);
    }

    /**
     * 计算指定日期所属周的第一天
     *
     * @param date
     * @param format  date日期格式
     * @param format2 返回日期格式
     * @return
     * @throws Exception
     */
    public static String getFirstDayOfWeek(String date, String format, String format2) throws ParseException {
        return getFirstDayOfWeek(Integer.valueOf(date.substring(0, 4)), getWeekNumOfYear(date, format), format2);
    }

    /**
     * 上一周的第一天
     *
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static String getFirstDayOfPreWeek(String date, String format) throws ParseException {
        Date d = sumDate(string2Date(date, format), -7);
        return getFirstDayOfWeek(Integer.valueOf(date2String(d, "yyyy")), getWeekNumOfYear(date2String(d, format), format), format);
    }

    /**
     * 上一周的最后一天
     *
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static String getLastDayOfPreWeek(String date, String format) throws Exception {
        Date d = sumDate(string2Date(date, format), -7);
        return getLastDayOfWeek(Integer.valueOf(date2String(d, "yyyy")), getWeekNumOfYear(date2String(d, format), format), format);
    }

    /**
     * 计算指定日期所属周的最后一天
     *
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static String getLastDayOfWeek(String date, String format) throws Exception {
        return getLastDayOfWeek(Integer.valueOf(date.substring(0, 4)), getWeekNumOfYear(date, format) + 1, format);
    }

    /**
     * 计算指定日期所属周的最后一天
     *
     * @param date
     * @param format  date日期格式
     * @param format2 返回日期格式
     * @return
     * @throws Exception
     */
    public static String getLastDayOfWeek(String date, String format, String format2) throws Exception {
        return getLastDayOfWeek(Integer.valueOf(date.substring(0, 4)), getWeekNumOfYear(date, format) + 1, format2);
    }

    /**
     * 取指定月份最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        return getLastDayOfMonth(year, month, "yyyy-MM-dd");
    }

    /**
     * 取指定月份最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month, String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    /**
     * 取指定日期所属月的最后一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(date, format));
        return getLastDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 取指定日期所属月的最后一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String date, String format, String format2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(date, format));
        return getLastDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, format2);
    }

    /**
     * 前一个月的最后一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getLastDayOfPreMonth(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(date, format));
        return getLastDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }

    /**
     * 取指定月份的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        return getFirstDayOfMonth(year, month, "yyyy-MM-dd");
    }

    /**
     * 取指定月份的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month, String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    /**
     * 取指定日期所属月的第一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(date, format));
        return getFirstDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 取指定日期所属月的第一天
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 取指定日期所属月的第一天
     *
     * @param date
     * @param format 接收日期格式
     * @param format 返回日期格式
     * @return
     */
    public static String getFirstDayOfMonth(String date, String format, String format2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(date, format));
        return getFirstDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, format2);
    }

    /**
     * 前一个月的第一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFirstDayOfPreMonth(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(date, format));
        return getFirstDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }

    /**
     * 计算date1和date2相差的秒数（date2-date1）
     *
     * @param date1 较小的日期
     * @param date2 较大的日期
     * @return
     */
    public static long subDate(Date date1, Date date2) {
        long l = date2.getTime() - date1.getTime();
        return l / 1000;
    }

    /**
     * 计算两个日期相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long daysBetween(Date date1, Date date2) {
        return subDate(date1, date2) / (60 * 60 * 24);
    }

    /**
     * 计算两个日期相差周数，同一周算0
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long weeksBetween(Date date1, Date date2) {
        return daysBetween(date1, date2) / 7;
    }

    /**
     * 计算两个日期之间相差月数，同一个月份算0
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long monthsBetween(Date date1, Date date2) {
        int year1 = Integer.valueOf(formatDate(date1, "yyyy"));
        int year2 = Integer.valueOf(formatDate(date2, "yyyy"));
        int month1 = Integer.valueOf(formatDate(date1, "MM"));
        int month2 = Integer.valueOf(formatDate(date2, "MM"));
        return (year2 - year1) * 12 + (month2 - month1);
    }

    /**
     * 计算日期加指定天数后所得日期
     *
     * @param date 原始日期
     * @param day  所加天数（负数时为减）
     * @return
     */
    public static Date sumDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // System.out.println(calendar.get(Calendar.DAY_OF_MONTH));// 今天的日期
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);// 让日期加1
        // System.out.println(calendar.get(Calendar.DATE));// 加1之后的日期Top
        return calendar.getTime();
    }

    /**
     * 获取指定日期前几周的周一
     *
     * @param date
     * @param week
     * @return
     */
    public static Date getFirstDayOfPreWeek(Date date, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7 * week);// 让日期加1
        return calendar.getTime();
    }

    /**
     * 日期格式转换
     *
     * @param date
     * @param format1
     * @param format2
     * @return
     */
    public static String changeFormat(String date, String format1, String format2) {
        SimpleDateFormat df = new SimpleDateFormat(format1);
        SimpleDateFormat df2 = new SimpleDateFormat(format2);
        try {
            return df2.format(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 参数1日期是否大于参数2
     *
     * @param large
     * @param small
     * @return
     */
    public static boolean isLager(Date large, Date small) {
        if (large == null) {
            return false;
        } else if (small == null) {
            return true;
        } else {
            return large.getTime() > small.getTime();
        }
    }

    /**
     * 字符串日期转换成时间戳
     *
     * @param datatime
     * @param fm
     * @return
     */
    public static long string2long(String datatime, String fm) {
        long time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(fm);
        try {
            time = sdf.parse(datatime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 获取多少天之前的日期
     *
     * @param days
     * @return
     */
    public static Date getDateBeforeTody(int days) {
        //获取当前日期
        Date today = new Date();
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(Calendar.DATE, -days);
        return theCa.getTime();
    }

    /**
     * 取一时间段内的每个日期
     */
    public static List<Date> getDateListBetweenTwoDate(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> list = new ArrayList<>();
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(start);
            while (startCalendar.getTime().before(end)) {
                list.add(startCalendar.getTime());
                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            list.add(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 取一时间段内的每个日期
     */
    public static List<Date> getDateListBetweenTwoDate(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> list = new ArrayList<>();
        try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            while (startCalendar.getTime().before(endDate)) {
                list.add(startCalendar.getTime());
                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            list.add(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 取一时间段内的每个周
     */
    public static List<Map<String, Object>> getWeekListBetweenTwoDate(Date startDate, Date endDate) {
        List<Map<String, Object>> list = new ArrayList<>();
        Integer currWeek = 0;

        try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setFirstDayOfWeek(Calendar.MONDAY);
            startCalendar.setTime(startDate);
            while (startCalendar.getTime().before(endDate)) {
                startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                currWeek = startCalendar.get(Calendar.WEEK_OF_YEAR);
                Map<String, Object> map = new HashMap<>(2);
                map.put("year", startCalendar.getWeekYear());
                map.put("week", currWeek);
                map.put("first", startCalendar.getTime());
                startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                map.put("end", startCalendar.getTime());
                list.add(map);
                startCalendar.add(Calendar.DAY_OF_MONTH, 7);
            }
            startCalendar.setTime(endDate);
            if (currWeek != startCalendar.get(Calendar.WEEK_OF_YEAR)) {
                Map<String, Object> map = new HashMap<>(2);
                startCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                map.put("year", startCalendar.getWeekYear());
                map.put("week", startCalendar.get(Calendar.WEEK_OF_YEAR));
                map.put("first", startCalendar.getTime());
                map.put("end", endDate);
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 取一时间段内的每个周
     */
    public static List<Map<String, Object>> getWeekListBetweenTwoDate(String startDateStr, String endDateStr, String format) {
        try {
            format = StringUtils.isEmpty(format) ? FORMAT3 : format;
            Date startDate = parseDate(startDateStr, format);
            Date endDate = parseDate(endDateStr, format);
            return getWeekListBetweenTwoDate(startDate, endDate);
        } catch (Exception e) {
            //throw new BusinessException(CodeConstant.ERROR_PARAMETER, "日期参数格式错误");
        }
        return null;
    }

    /**
     * 取一时间段内的每个月
     */
    public static List<Map<String, Object>> getMonthListBetweenTwoDate(Date startDate, Date endDate) {
        List<Map<String, Object>> list = new ArrayList<>();
        Integer currMonth = 0;

        try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            while (startCalendar.getTime().before(endDate)) {
                startCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.getActualMinimum(Calendar.DATE));
                currMonth = startCalendar.get(Calendar.MONTH) + 1;
                Map<String, Object> map = new HashMap<>(2);
                map.put("year", startCalendar.getWeekYear());
                map.put("month", currMonth);
                map.put("first", startCalendar.getTime());
                startCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.getActualMaximum(Calendar.DATE));
                map.put("end", startCalendar.getTime());
                list.add(map);
                startCalendar.add(Calendar.MONTH, 1);
            }
            startCalendar.setTime(endDate);
            if (currMonth == startCalendar.get(Calendar.MONTH)) {
                Map<String, Object> map = new HashMap<>(2);
                startCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.getActualMinimum(Calendar.DATE));
                map.put("year", startCalendar.getWeekYear());
                map.put("month", startCalendar.get(Calendar.MONTH) + 1);
                map.put("first", startCalendar.getTime());
                map.put("end", endDate);
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 格式化时间段
     *
     * @param mss 单位秒
     * @return
     * @author tiankuo
     * @time 2017年10月19日
     */
    public static String formatSeconds(Long mss) {
        if (null == mss || mss < 0) {
            return "";
        }
        StringBuilder dateTimes = new StringBuilder();
        long days = mss / ONE_DAY;
        long hours = (mss % (ONE_DAY)) / (ONE_HOUR);
        long minutes = (mss % (ONE_HOUR)) / ONE_MINUTE;
        long seconds = mss % ONE_MINUTE;
        if (days > 0) {
            dateTimes.append(days).append(DAY);
        }
        if (hours > 0) {
            dateTimes.append(hours).append(HOUR);
        }
        if (minutes > 0) {
            dateTimes.append(minutes).append(MINUTE);
        }
        if (seconds >= 0) {
            dateTimes.append(seconds).append(SECOND);
        }
        return dateTimes.toString();
    }

    /**
     * 格式化时间
     * 5天2时34分10秒 换算为 秒
     */
    public static Long format2Seconds(String dateTimeStr) {
        if (null == dateTimeStr || "".equals(dateTimeStr)) {
            return null;
        }
        Long seconds = 0L;
        // 正则匹配 n天 matcher 对象
        Matcher dayMatcher = Pattern.compile("(\\d*)" + DAY).matcher(dateTimeStr);
        if (dayMatcher.find()) {
            int days = Integer.parseInt(dayMatcher.group(0).split(DAY)[0]);
            seconds += days * ONE_DAY;
        }
        // 正则匹配 n时 matcher 对象
        Matcher hourMatcher = Pattern.compile("(\\d*)" + HOUR).matcher(dateTimeStr);
        if (hourMatcher.find()) {
            int hours = Integer.parseInt(hourMatcher.group(0).split(HOUR)[0]);
            seconds += hours * ONE_HOUR;
        }
        // 正则匹配 n分 matcher 对象
        Matcher minMatcher = Pattern.compile("(\\d*)" + MINUTE).matcher(dateTimeStr);
        if (minMatcher.find()) {
            int mins = Integer.parseInt(minMatcher.group(0).split(MINUTE)[0]);
            seconds += mins * ONE_MINUTE;
        }
        // 正则匹配 n秒 matcher 对象
        Matcher secMatcher = Pattern.compile("(\\d*)" + SECOND).matcher(dateTimeStr);
        if (secMatcher.find()) {
            int sec = Integer.parseInt(secMatcher.group(0).split(SECOND)[0]);
            seconds += sec;
        }
        return seconds;
    }

    public static boolean isAfter(String begin, String end, DateTimeFormatter formatter) {
        try {
            return LocalTime.parse(begin, formatter).isAfter(LocalTime.parse(end, formatter));
        } catch (DateTimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isAfter4HHmm(String begin, String end) {
        return isAfter(begin, end, DATE_TIME_FORMAT_HHMM);
    }

    public static boolean checkInclude(String begin, String end, String compareBegin, String compareEnd) {
        return (DateUtils.isAfter4HHmm(compareBegin, begin) && !DateUtils.isAfter4HHmm(end, compareBegin)) || (DateUtils.isAfter4HHmm(begin, compareBegin) && !DateUtils.isAfter4HHmm(compareEnd, begin));
    }

    /**
     * 获取指定周的开始和结束时间，格式 M/dd-M/dd
     *
     * @param week
     * @param year
     * @return
     */
    public static String getWeekStartAndEnd(int week, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        String start = date2String(calendar.getTime(), "M/dd");
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String end = date2String(calendar.getTime(), "M/dd");
        return Objects.requireNonNull(start).concat("-").concat(end != null ? end : "");
    }

    /**
     * 报表前端展示 日期格式 MMdd
     * 如果不是当前年 则需要把年份展示出来 yyyyMMdd
     */
    public static String getDateFormat(String bussDate, String bussYear, String separate, String formatFrom, String formatTo) {
        String date = "";
        if (DateUtils.isValidDate(bussDate, formatFrom)) {
            //格式化日期 1220
            if (!StringUtils.isEmpty(bussDate)) {
                date = DateUtils.changeFormat(bussDate, formatFrom, formatTo);
            }
        } else {
            //周 月 统计的显示 不做处理
            date = bussDate;
        }
        //如果不是当前年，则把年份显示出来
        if (!DateUtils.getYear().equals(bussYear)) {
            date = bussYear + separate + date;
        }
        return date;
    }

    /**
     * 判断时间格式 格式必须为“YYYY-MM-dd”
     */
    public static boolean isValidDate(String str, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    static class DateTimeFormatterLocal {
        public static ThreadLocal<DecimalFormat> dateTimeFormatterThreadLocal = ThreadLocal.withInitial(() -> new DecimalFormat("0"));
    }


    public static long dateMinus(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0L;
        }
        long diff = date1.getTime() - date2.getTime();
        return diff % ND % NH / NM;
    }
}
