var stompClient = null;

/**
 * 建立连接
 */
function connect() {
	// 连接SockJS的endpoint，名称为"/endpointWisely"
	var socket = new SockJS('/endpointWisely');
	// 使用stomp子协议的WebSocket 客户端
	stompClient = Stomp.over(socket);
	// 链接Web Socket的服务端
	stompClient.connect({}, function(frame) {
		console.log('连接已建立: ' + frame);
		// 订阅/topic/getResponse
		stompClient.subscribe('/topic/getResponse', function(respnose) {
			// 目标发送的消息。这个是在控制器的@SendTo中定义的。
			showResponse(JSON.parse(respnose.body));
		});
	});
}
/**
 * 断开连接
 */
function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
		console.log("断开连接");
	}
}

function sendName() {
	var name = $('#name').val();
	// 通过stompClient.send 向/welcome 目标 发送消息,这个是在控制器的@messageMapping 中定义的。
	stompClient.send("/welcome", {}, JSON.stringify({
		'context' : name
	}));
}

function showResponse(message) {
	var response = $("#response");
	response.text(message.context);
}