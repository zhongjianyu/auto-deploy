package auto.deploy.websocket.service;

import java.util.List;

import auto.deploy.websocket.WebScoketMsg;

/**
 * 
 * @描述：websocket(接口).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface WebSocketService {

	/**
	 * 
	 * @描述：广播式消息推送
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月12日 下午12:58:18
	 */
	public void pushMessage(WebScoketMsg msg);

	/**
	 * 
	 * @描述：点对点消息推送
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月12日 下午12:58:18
	 */
	public void pushMessageToUser(WebScoketMsg msg, List<Long> userIds);
}
