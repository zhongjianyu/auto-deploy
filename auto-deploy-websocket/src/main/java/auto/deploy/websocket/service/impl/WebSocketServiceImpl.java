package auto.deploy.websocket.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import auto.deploy.websocket.WebSocketMsg;
import auto.deploy.websocket.service.WebSocketService;

/**
 * 
 * @描述：websocket(实现).
 *
 * @作者：zhongjy
 * 
 * @时间：2017年6月12日 上午10:37:10
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

	@Resource
	private SimpMessagingTemplate template;

	@Override
	public void pushMessage(WebSocketMsg msg) {
		template.convertAndSend("/topic/getResponse", msg);
	}

	@Override
	public void pushMessageToUser(WebSocketMsg msg, List<String> userNames) {
		for (String userName : userNames) {
			template.convertAndSendToUser(userName, "/point2point/getResponse", msg);
		}
	}

}
