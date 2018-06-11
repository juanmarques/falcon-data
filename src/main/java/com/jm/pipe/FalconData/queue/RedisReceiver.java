package com.jm.pipe.FalconData.queue;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.pipe.FalconData.model.ChatMessage;

/**
 * @author Juan Marques
 * @Date 2018-06-10
 */
public class RedisReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisReceiver.class);

	private final WebSocketMessageService webSocketMessageService;

	public RedisReceiver(WebSocketMessageService webSocketMessageService) {
		this.webSocketMessageService = webSocketMessageService;
	}

	// Invoked when message is publish to "chat" channel
	public void receiveChatMessage(String message) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);

		LOGGER.info("Notification Message Received: " + chatMessage);
		webSocketMessageService.sendChatMessage(chatMessage);

	}

	// Invoked when message is publish to "count" channel
	public void receiveCountMessage(String totalMessageCount) {

		LOGGER.info("Count Message Received :" + totalMessageCount);
		webSocketMessageService.sendMessageCount(Integer.parseInt(totalMessageCount));

	}
}
