package com.xingchen.core.utils;

/**
 * 日期帮助类
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DateUtil {

	/**
	 * 格式化日期
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getDateTimeStringFormat(String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		return simpleDateFormat.format(date);
	}

	public static String getDateTimeStringFormat(Calendar date, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date.getTime());
	}

	public static String getDateTimeStringFormat(Date date, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

	public static String getDateTimeStringFormat(Calendar date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date.getTime());
	}

	public static String getDateTimeStringFormat(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	public static String getDateTimeString() {
		return getDateTimeStringFormat("yyyyMMddHHmmsssss");
	}

	public static String getCurrentDate() {
		return getDateTimeStringFormat("MM-dd");
	}

	public static String getCurrentDate(String dateFormat) {
		return getDateTimeStringFormat(dateFormat);
	}

	public static String getLocalTimeString(int time) {
		return new Date(time * 1000l).toLocaleString();
	}

	public static String getDateString(Date date, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将格式为 yyyy-MM-dd HH:mm:ssss 的时间 转成 MM-dd HH:mm
	 */
	public static String getSimpleDateString(String date) {
		return null == date ? "" : date.substring(5, 16);
	}

	/**
	 * 得到当天的凌晨事件
	 * 
	 * @return
	 */
	public static Date getCurrentDayOfZeroTime() {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}

	/**
	 * 得到毫秒时间
	 * 
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 得到相隔day的日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date operationDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 系统时间如果是个位数补0，格式为： xxxx-xx-xx xx:xx:xx
	 * 
	 * @return
	 */
	public static String getSystemDateFormat() {
		String time = null;
		Calendar c = Calendar.getInstance();
		int nYear = c.get(Calendar.YEAR);
		int nMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DATE);
		int nHour = c.get(Calendar.HOUR_OF_DAY);// 设置时间为24小时制
		int nMin = c.get(Calendar.MINUTE);
		int nSec = c.get(Calendar.SECOND);
		time = getSystemDateFormat(nYear, nMonth, mDay) + " " + getSystemTime(nHour, nMin, nSec);
		return time;
	}

	/**
	 * 获取当前时间，并将格式为 yyyy-MM-dd HH:mm:ssss 的时间 转成 MM-dd HH:mm
	 */
	public static String getSimpleDateString_System() {
		String date = getSystemDateFormat();
		return getSimpleDateString(date);
	}
	
	/**
	 * 系统时间如果是个位数补0 ，格式为：xxxx-xx-xx
	 * 
	 * @return
	 */
	public static String getSystemDate() {
		String time = null;
		Calendar c = Calendar.getInstance();
		int nYear = c.get(Calendar.YEAR);
		int nMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DATE);
		time = getSystemDateFormat(nYear, nMonth, mDay);
		return time;
	}

	public static String getSystemDateFormat(int year, int month, int day) {
		String nMonth, nDay;
		if (month < 9) {
			nMonth = "0" + (month + 1);
		} else {
			nMonth = "" + (month + 1);
		}
		if (day < 10) {
			nDay = "0" + day;
		} else {
			nDay = "" + day;
		}
		return year + "-" + nMonth + "-" + nDay;
	}

	public static String getSystemTime(int hour, int min, int sec) {
		String nHour, nMin, nSec;
		if (hour < 10) {
			nHour = "0" + hour;
		} else {
			nHour = "" + hour;
		}
		if (min < 10) {
			nMin = "0" + min;
		} else {
			nMin = "" + min;
		}
		if (sec < 10) {
			nSec = "0" + sec;
		} else {
			nSec = "" + sec;
		}
		return nHour + ":" + nMin + ":" + nSec;
	}

	/**
	 * 获得指定日期的前N天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */

	public static Calendar getSpecifiedDayBeforeNumDay(String specifiedDay, int beforeDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - beforeDay);
		return c;
	}

	public static Date getSpecifiedDayBeforeDate(String specifiedDay, int beforeDay) {
		Calendar c = getSpecifiedDayBeforeNumDay(specifiedDay, beforeDay);
		return c.getTime();
	}

	public static String getSpecifiedDayBeforeNumberDay(String specifiedDay, int beforeDay) {
		Date c = getSpecifiedDayBeforeDate(specifiedDay, beforeDay);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c);
		return dayBefore;
	}

	/**
	 * 获得指定日期的后N天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Calendar getSpecifiedDayAfterNumDay(String specifiedDay, int beforeDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + beforeDay);
		return c;
	}

	public static Date getSpecifiedDayAfterDate(String specifiedDay, int beforeDay) {
		Calendar c = getSpecifiedDayAfterNumDay(specifiedDay, beforeDay);
		return c.getTime();
	}

	public static String getSpecifiedDayAfter(String specifiedDay, int beforeDay) {
		Date c = getSpecifiedDayAfterDate(specifiedDay, beforeDay);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c);
		return dayBefore;
	}

	/**
	 * 获取当前系统时间段 前N天或后N天
	 * 
	 * @param specifiedDay
	 * @param beforeDay
	 *            前一天为负数，后一天为正数
	 * @return
	 */

	public static Calendar getSpecifiedDayNearNumDay(String specifiedDay, int beforeDay) {
		Calendar c = getCalendarByString(specifiedDay, "yyyy-MM-dd");
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - beforeDay);
		return c;
	}

	/**
	 * 根据字符串得到Calendar
	 * 
	 * @param dateString
	 *            "2012-12-13"
	 * @param dateFormat
	 *            yyyy-MM-dd
	 * @return
	 */
	public static Calendar getCalendarByString(String dateString, String dateFormat) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		return c;
	}

	/**
	 * 第day天的日期
	 * 
	 * @param c
	 * @param day
	 * @return
	 */
	public static Calendar getCalendarByNumDay(Calendar c, int day) {
		Calendar cc = Calendar.getInstance();
		cc.setTimeInMillis(c.getTimeInMillis() + ((long) day) * 24 * 3600 * 1000);
		return cc;
	}

	/**
	 * 解析日期
	 * 
	 * @param dateString
	 * @param day
	 * @return
	 */
	public static String parseDateString(String dateString, int day) {
		Calendar c = DateUtil.getCalendarByString(dateString, "yyyy-MM-dd");
		Calendar whenCalendar = DateUtil.getCalendarByNumDay(c, day);
		return DateUtil.getDateTimeStringFormat(whenCalendar);
	}

	/**
	 * 获取时间，转换成Calendar
	 * 
	 * @param button
	 * @return
	 */
	public static Calendar gerCalendarByView(View view) {
		String date = null;
		if (view instanceof Button) {
			Button button = (Button) view;
			date = button.getText().toString().trim();
		} else if (view instanceof TextView) {
			TextView textview = (TextView) view;
			date = textview.getText().toString().trim();
		} else if (view instanceof EditText) {
			EditText editText = (EditText) view;
			date = editText.getText().toString().trim();
		}
		Calendar calendar = DateUtil.getCalendarByString(date, "yyyy-MM-dd");
		return calendar;
	}

	public static String getTimeStringFormat(Date date, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

	/**
	 * 得到默认时间的日期字符串
	 * 
	 * @param dateFormat
	 * @return String
	 */
	public static String getDefaultTimeStringFormat(String dateFormat) {
		return getTimeStringFormat(new Date(), dateFormat);
	}

}
