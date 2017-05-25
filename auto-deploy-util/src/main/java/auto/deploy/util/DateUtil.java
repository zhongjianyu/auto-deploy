package auto.deploy.util;

import java.util.Date;

import com.alibaba.fastjson.JSON;

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
	 * 
	 * @描述：日期转字符串
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:18:14
	 */
	public static String date2str(Date date) {
		if (date == null) {
			return null;
		} else {
			return JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * 
	 * @描述：字符串日期
	 *
	 * @返回：Date
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:18:29
	 */
	public static Date str2date(String str) {
		if (str == null) {
			return null;
		} else {
			return JSON.parseObject(str, Date.class);
		}
	}
}
