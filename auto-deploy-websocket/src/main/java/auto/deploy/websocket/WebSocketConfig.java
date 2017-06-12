package auto.deploy.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 
 * @描述：websocket配置:开启使用STOMP协议来传输基于代理(message broker)的消息
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月4日 下午9:12:53
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/**
	 * 注册协议节点、并映射指定的URl、指定SockJS协议
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpointAutoDeployCIMS").withSockJS();
	}

	/**
	 * 广播式应配置一个/topic消息代理
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/point2point");
	}

}
