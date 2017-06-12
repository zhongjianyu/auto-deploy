package auto.deploy.websocket;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auto.deploy.websocket.service.WebSocketService;

/**
 * 
 * @描述：websocket控制器
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月4日 下午10:12:03
 */
@Controller
public class WebSocketController {

	@Resource
	private WebSocketService webSocketService;

	@MessageMapping("/say")
	@ResponseBody
	public WebSocketMsg say(WebSocketMsg msg) {
		webSocketService.pushMessage(msg);
		return null;
	}

	@RequestMapping("ws")
	public String ws() {
		return "ws";
	}
	
	@RequestMapping("/say1")
	public WebSocketMsg say() {
		WebSocketMsg msg = new WebSocketMsg();
		msg.setMessage("玻璃春天");
		webSocketService.pushMessage(msg);
		return null;
	}
}
