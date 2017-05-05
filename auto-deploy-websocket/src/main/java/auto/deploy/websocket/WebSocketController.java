package auto.deploy.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@MessageMapping("/welcome") // 浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
	@SendTo("/topic/getResponse") // 服务器端有消息时,会订阅@SendTo中的路径的浏览器发送消息。
	public WebScoketMsg say(WebScoketMsg msg) throws Exception {
		Thread.sleep(1000);
		msg.setContext(msg.getContext() + ",你好");
		return msg;
	}

	@RequestMapping("ws")
	public String ws() {
		return "ws";
	}
}
