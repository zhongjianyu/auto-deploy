package auto.deploy.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @描述：json相关工具类
 *
 * @作者：zhongjy
 * 
 * @时间：2017年5月25日 下午1:50:27
 */
public class JsonUtil {

	/**
	 * 
	 * @描述：对象转json字符串
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午1:54:34
	 */
	public static String obj2str(Object object) {
		if (object == null) {
			return null;
		} else {
			return JSON.toJSONString(object, true);
		}
	}

	/**
	 * 
	 * @描述：根据对象字符串返回对象
	 *
	 * @返回：T.
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:01:41
	 */
	public static <T> T str2obj(String str, Class<T> c) {
		if (str == null) {
			return null;
		} else {
			return JSON.parseObject(str, c);
		}
	}

	/**
	 * 
	 * @描述：根据对象字符串返回list对象
	 *
	 * @返回：List<T>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:08:50
	 */
	public static <T> List<T> str2list(String str, Class<T> c) {
		if (str == null) {
			return null;
		} else {
			return JSON.parseArray(str, c);
		}
	}

	/**
	 * 
	 * @描述：字符串转json对象
	 *
	 * @返回：JSONObject
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:11:04
	 */
	public static JSONObject str2json(String str) {
		if (str == null) {
			return null;
		} else {
			return JSONObject.parseObject(str);
		}
	}

	/**
	 * 
	 * @描述：字符串转json对象数组
	 *
	 * @返回：JSONArray
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:11:52
	 */
	public static JSONArray str2jsonArr(String str) {
		if (str == null) {
			return null;
		} else {
			return JSONObject.parseArray(str);
		}
	}

	/**
	 * 
	 * @描述：对象转json对象
	 *
	 * @返回：Object
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午2:14:05
	 */
	public Object obj2json(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return JSON.toJSON(obj);
		}
	}

}
