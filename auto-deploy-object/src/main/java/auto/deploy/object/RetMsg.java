package auto.deploy.object;

/**
 * 
 * @描述：ajax访问统一返回对象
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月25日 下午8:24:41
 */
public class RetMsg {

	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 返回消息
	 */
	private String message;
	/**
	 * 返回对象
	 */
	private Object object;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
