package auto.deploy.websocket.service;

import java.util.List;

import auto.deploy.websocket.WebSocketMsg;

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
	public void pushMessage(WebSocketMsg msg);

	/**
	 * 
	 * @描述：点对点消息推送(多用户).
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月12日 下午12:58:18
	 */
	public void pushMessageToUserList(WebSocketMsg msg, List<String> userNames);

	/**
	 * 
	 * @描述：点对点消息推送(单用户).
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月12日 下午12:58:18
	 */
	public void pushMessageToUser(WebSocketMsg msg, String userName);
}
