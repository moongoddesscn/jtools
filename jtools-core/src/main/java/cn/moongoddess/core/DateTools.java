package cn.moongoddess.core;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间及日期工具类
 * @author yanghd
 */
public class DateTools extends DateUtils
{
    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static Date getNowDate()
    {
        return new Date();
    }

    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date)
    {
		if(date == null) {
			return null;
		}
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    public static String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    public static Date parseDate(String str)
    {
        if (str == null)  return null;

        try
        {
            return parseDate(str, parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public static long getDateDifference(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        return diff / nd;
    }

    public static Date toDate(LocalDateTime localDateTime)
    {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static Date toDate(LocalDate localDate)
    {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

	public static Date addDays(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); //设置开始时间
		calendar.add(Calendar.DATE, num);
		date = calendar.getTime();
		return date;
	}

    public static Date addMonths(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        date = calendar.getTime();
        return date;
    }

	public static Date addYears(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, num);
		date = calendar.getTime();
		return date;
	}


}
