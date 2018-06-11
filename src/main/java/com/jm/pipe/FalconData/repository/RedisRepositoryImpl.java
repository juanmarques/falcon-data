package com.jm.pipe.FalconData.repository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.pipe.FalconData.model.ChatMessage;

/**
 * @author Juan Marques
 * @Date 2018-06-09
 */

@Repository
public class RedisRepositoryImpl implements RedisRepository {

	private static final String KEY = "Falcon";

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Object> hashOperations;
	private final AtomicLong count = new AtomicLong(0);
	private final AtomicLong chatCounter = new AtomicLong(0);
	private final StringRedisTemplate stringRedisTemplate;

	@Autowired
	public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
		this.redisTemplate = redisTemplate;
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Object object) {
		long id = count.incrementAndGet();
		hashOperations.put(KEY, id, object);
	}

	@Override
	public Object find(Long id) {
		return hashOperations.get(KEY, id);
	}

	@Override
	public Map<Long, Object> findAll() {
		return hashOperations.entries(KEY);

	}

	@Override
	public void delete(Long id) {
		hashOperations.delete(KEY, id);
	}

	public void publishMessageToRedis(String message) throws JsonProcessingException {

		Long totalChatMessage = chatCounter.incrementAndGet();
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			hostName = "localhost";
		}

		ChatMessage chatMessage = new ChatMessage(totalChatMessage, message, hostName);
		ObjectMapper objectMapper = new ObjectMapper();
		String chatString = objectMapper.writeValueAsString(chatMessage);

		// Publish Message to Redis Channels
		stringRedisTemplate.convertAndSend("chat", chatString);
		stringRedisTemplate.convertAndSend("count", totalChatMessage.toString());

	}
}
