package cn.yt4j.message.server.controller;

import cn.yt4j.message.core.client.MessageClient;
import cn.yt4j.message.core.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gyv12345@163.com
 */
@RequestMapping("message")
@RestController
@AllArgsConstructor
public class MessageController {

	private final MessageClient messageClient;

	@RequestMapping("send")
	public void send(Message message) {
		messageClient.send(message);
	}

}
