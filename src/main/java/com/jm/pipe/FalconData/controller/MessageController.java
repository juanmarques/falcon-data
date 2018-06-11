package com.jm.pipe.FalconData.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jm.pipe.FalconData.repository.RedisRepository;

/**
 * @author Juan Marques
 * @Date 2018-06-10
 */
@Controller
public class MessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private RedisRepository redisRepository;

	@MessageMapping("/message")
	public void sendWsChatMessage(String message) throws JsonProcessingException {
		LOGGER.info("Incoming WebSocket Message : {}", message);
		redisRepository.publishMessageToRedis(message);
	}

}
