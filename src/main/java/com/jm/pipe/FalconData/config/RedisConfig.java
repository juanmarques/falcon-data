package com.jm.pipe.FalconData.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;

import com.jm.pipe.FalconData.queue.RedisReceiver;
import com.jm.pipe.FalconData.queue.WebSocketMessageService;

/**
 * @author Juan Marques
 * @Date 2018-06-09
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			@Qualifier("chatMessageListenerAdapter") MessageListenerAdapter chatMessageListenerAdapter,
			@Qualifier("countListenerAdapter") MessageListenerAdapter countListenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(chatMessageListenerAdapter, new PatternTopic("chat"));
		container.addMessageListener(countListenerAdapter, new PatternTopic("count"));
		return container;
	}

	@Bean("chatMessageListenerAdapter")
	MessageListenerAdapter chatMessageListenerAdapter(RedisReceiver redisReceiver) {
		return new MessageListenerAdapter(redisReceiver, "receiveChatMessage");
	}

	@Bean("countListenerAdapter")
	MessageListenerAdapter countListenerAdapter(RedisReceiver redisReceiver) {
		return new MessageListenerAdapter(redisReceiver, "receiveCountMessage");
	}

	@Bean
	RedisReceiver receiver(WebSocketMessageService webSocketMessageService) {
		return new RedisReceiver(webSocketMessageService);
	}

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	@Bean // Redis Atomic Counter to store no. of total messages sent from multiple app
			// instances.
	RedisAtomicInteger getChatMessageCounter(RedisTemplate<?, ?> redisTemplate) {
		RedisAtomicInteger chatMessageCounter = new RedisAtomicInteger("Falcon-chat",
				redisTemplate.getConnectionFactory());
		return chatMessageCounter;
	}
}
