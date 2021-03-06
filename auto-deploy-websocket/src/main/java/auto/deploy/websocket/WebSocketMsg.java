package auto.deploy.websocket;

/**
 * 
 * @描述：websocket消息类
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月4日 下午9:43:37
 */
public class WebSocketMsg {

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
