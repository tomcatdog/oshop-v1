package me.xiaoy.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

	/**
	 * 默认的格式:yyyy-MM-dd
	 */
	public static final String PATTERN_DEFAULT = "yyyy-MM-dd";

	public static final String FORMAT_STANDARD = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String CHINESE_SHORT_DATE = "yyyy年M月d日";

	private static final String[] weekDays = { "", "星期日", "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六" };

	public static String formatDate(String format, Date d) {
		String date = "";
		try {
			date = new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			logger.error("调用DateUtil.fomatDate进行日期转换出错, 日期格式： " + format);
		}
		return date;
	}

	public static String getYYYYMMDD(Date d) {
		return formatDate(PATTERN_DEFAULT, d);
	}

	public static String getNow() {
		Date date = new Date();
		return formatDate(FORMAT_STANDARD, date);
	}

	public static String getNow(String format) {
		Date date = new Date();
		return formatDate(format, date);
	}

	/**
	 * 得到中文星期
	 * 
	 * @return
	 */
	public static final String getChineseWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return weekDays[c.get(Calendar.DAY_OF_WEEK)];
	}
	
	public static Date parseDate(String dateStr, String pattern) {
		Date date = null;
		if(dateStr==null || dateStr.length()==0) {
			logger.warn("传入日期为空");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				logger.error("日期转换出错", e);
			}
		}
		return date;
	}
	
	public static String getWelcome() {
		String result = "";
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(hour>= 3 && hour < 5) {
			result = "凌晨了";
		} else if(hour >= 5 && hour < 9) {
			result = "早上好";
		} else if(hour >= 9 && hour < 12) {
			result = "上午好";
		} else if(hour >= 12 && hour < 14) {
			result = "中午好";
		} else if(hour >= 14 && hour < 19) {
			result = "下午好";
		} else if(hour >= 19 && hour < 23) {
			result = "晚上好";
		} else if(hour >= 23 || hour < 3) {
			result = "夜已深";
		}
		return result;
	}

}
