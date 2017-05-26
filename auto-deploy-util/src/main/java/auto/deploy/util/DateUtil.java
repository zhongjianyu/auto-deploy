package auto.deploy.util;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @描述：日期工具类
 *
 * @作者：zhongjy
 * 
 * @时间：2017年5月25日 下午2:15:32
 */
public class DateUtil {
	/**
	 * 年、月、星期、日、时、分、秒、毫秒
	 */
	public static final String UNIT_YEAR = "year";
	public static final String UNIT_MONTH = "month";
	public static final String UNIT_WEEK = "week";
	public static final String UNIT_DAY = "day";
	public static final String UNIT_HOUR = "hour";
	public static final String UNIT_MINUTE = "minute";
	public static final String UNIT_SECOND = "second";
	public static final String UNIT_MILLI = "milli";

	/**
	 * 
	 * @描述：时间转字符串,如果传入参数为空则返回当前时间
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:10:30
	 */
	public static String datetime2str(Date date) {
		if (date == null) {
			return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
		} else {
			return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
	}

	/**
	 * 
	 * @描述：日期转字符串,如果传入参数为空则返回当前日期
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:10:18
	 */
	public static String date2str(Date date) {
		if (date == null) {
			return new DateTime().toString("yyyy-MM-dd");
		} else {
			return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
		}
	}

	/**
	 * 
	 * @描述：字符串转日期,如果参数为空则返回当前日期
	 *
	 * @返回：Date
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:10:07
	 */
	public static Date str2date(String str) throws ParseException {
		if (str == null) {
			return new Date();
		} else {
			return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str);
		}
	}

	/**
	 * 
	 * @描述：字符串转时间,如果参数为空则返回当前时间
	 *
	 * @返回：Date
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:09:58
	 */
	public static Date str2datetime(String str) throws ParseException {
		if (str == null) {
			return new Date();
		} else {
			return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		}
	}

	/**
	 * 
	 * @描述：判断tag是否在beg到end这个时间段中(不含临界时间).
	 *
	 * @返回：boolean
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:21:26
	 */
	public static boolean isInPeriod(Date beg, Date end, Date tag) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 转DateTime
		DateTime beg1 = DateTime.parse(datetime2str(beg), format);
		DateTime end1 = DateTime.parse(datetime2str(end), format);
		DateTime tag1 = DateTime.parse(datetime2str(tag), format);

		Interval inteval = new Interval(beg1, end1);
		return inteval.contains(tag1);
	}

	/**
	 * 
	 * @描述：判断tag是否在beg到end这个时间段中(不含临界时间).
	 *
	 * @返回：boolean
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:21:26
	 */
	public static boolean isInPeriod(String beg, String end, String tag) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 转DateTime
		DateTime beg1 = DateTime.parse(beg, format);
		DateTime end1 = DateTime.parse(end, format);
		DateTime tag1 = DateTime.parse(tag, format);

		Interval inteval = new Interval(beg1, end1);
		return inteval.contains(tag1);
	}

	/**
	 * 
	 * @描述：获取两个时间之间相差的时间间隔
	 *
	 * @返回：long
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:42:12
	 */
	public static long getPeriod(Date beg, Date end, String unit) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 转DateTime
		DateTime beg1 = DateTime.parse(datetime2str(beg), format);
		DateTime end1 = DateTime.parse(datetime2str(end), format);

		Duration duration = new Duration(beg1, end1);
		if (DateUtil.UNIT_DAY.equals(unit)) {
			return duration.getStandardDays();
		} else if (DateUtil.UNIT_HOUR.equals(unit)) {
			return duration.getStandardHours();
		} else if (DateUtil.UNIT_MINUTE.equals(unit)) {
			return duration.getStandardMinutes();
		} else if (DateUtil.UNIT_SECOND.equals(unit)) {
			return duration.getStandardSeconds();
		} else {
			return duration.getMillis();
		}

	}

	/**
	 * 
	 * @描述：获取两个时间之间相差的时间间隔
	 *
	 * @返回：long
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午1:42:03
	 */
	public static long getPeriod(String beg, String end, String unit) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 转DateTime
		DateTime beg1 = DateTime.parse(beg, format);
		DateTime end1 = DateTime.parse(end, format);

		Duration duration = new Duration(beg1, end1);
		if (DateUtil.UNIT_DAY.equals(unit)) {
			return duration.getStandardDays();
		} else if (DateUtil.UNIT_HOUR.equals(unit)) {
			return duration.getStandardHours();
		} else if (DateUtil.UNIT_MINUTE.equals(unit)) {
			return duration.getStandardMinutes();
		} else if (DateUtil.UNIT_SECOND.equals(unit)) {
			return duration.getStandardSeconds();
		} else {
			return duration.getMillis();
		}
	}

	/**
	 * 
	 * @描述：获取n年、月、周...前的日期
	 *
	 * @返回：Date
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午2:57:21
	 */
	public static Date getBefore(Date datetime, String unit, int n) throws ParseException {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 转DateTime
		DateTime dt = DateTime.parse(datetime2str(datetime), format);
		// 根据时间单位获取之前的某个时间
		if (DateUtil.UNIT_YEAR.equals(unit)) {
			return str2datetime(dt.minusYears(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_MONTH.equals(unit)) {
			return str2datetime(dt.minusMonths(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_WEEK.equals(unit)) {
			return str2datetime(dt.minusWeeks(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_DAY.equals(unit)) {
			return str2datetime(dt.minusDays(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_HOUR.equals(unit)) {
			return str2datetime(dt.minusHours(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_MINUTE.equals(unit)) {
			return str2datetime(dt.minusMinutes(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_SECOND.equals(unit)) {
			return str2datetime(dt.minusSeconds(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else {
			return str2datetime(dt.minusMillis(n).toString("yyyy-MM-dd HH:mm:ss"));
		}
	}

	/**
	 * 
	 * @描述：获取n年、月、周...后的日期
	 *
	 * @返回：Date
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午2:58:01
	 */
	public static Date getAfter(Date datetime, String unit, int n) throws ParseException {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 转DateTime
		DateTime dt = DateTime.parse(datetime2str(datetime), format);

		// 根据时间单位获取之后的某个时间
		if (DateUtil.UNIT_YEAR.equals(unit)) {
			return str2datetime(dt.plusYears(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_MONTH.equals(unit)) {
			return str2datetime(dt.plusMonths(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_WEEK.equals(unit)) {
			return str2datetime(dt.plusWeeks(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_DAY.equals(unit)) {
			return str2datetime(dt.plusDays(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_HOUR.equals(unit)) {
			return str2datetime(dt.plusHours(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_MINUTE.equals(unit)) {
			return str2datetime(dt.plusMinutes(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else if (DateUtil.UNIT_SECOND.equals(unit)) {
			return str2datetime(dt.plusSeconds(n).toString("yyyy-MM-dd HH:mm:ss"));
		} else {
			return str2datetime(dt.plusMillis(n).toString("yyyy-MM-dd HH:mm:ss"));
		}
	}

	/**
	 * 
	 * @描述：根据日期获取星期(如果参数为空则返回当天星期几).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午2:46:04
	 */
	public static String getWeek(Date date) {
		int w = 0;
		if (date == null) {
			w = new DateTime().getDayOfWeek();
		} else {
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			// 转DateTime
			DateTime dt = DateTime.parse(datetime2str(date), format);
			w = dt.getDayOfWeek();
		}
		String week = "";
		if (w == 1) {
			week = "星期一";
		} else if (w == 2) {
			week = "星期 二";
		} else if (w == 3) {
			week = "星期三";
		} else if (w == 4) {
			week = "星期四";
		} else if (w == 5) {
			week = "星期五";
		} else if (w == 6) {
			week = "星期六";
		} else {
			week = "星期天";
		}
		return week;
	}

	/**
	 * 
	 * @描述：获取传入的时间是一年中的第几个星期（如果传入的时间为null则返回当前时间是第几个星期）.
	 *
	 * @返回：int
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月26日 下午2:54:45
	 */
	public static int getWeekNumber(Date date) {
		if (date == null) {
			return new DateTime().getWeekOfWeekyear();
		} else {
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			// 转DateTime
			DateTime dt = DateTime.parse(datetime2str(date), format);
			return dt.getWeekOfWeekyear();
		}
	}

	public static void main(String[] args) throws Exception {

	}
}
