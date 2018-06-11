package com.jm.pipe.FalconData.repository;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Juan Marques
 * @Date 2018-06-09
 */
public interface RedisRepository {

	/**
	 * @param object
	 *            Add key-value pair to Redis.
	 */
	void save(Object object);

	/**
	 * @param id
	 * @return a specific object from Redis
	 */
	Object find(Long id);

	/**
	 * @return all objects from Redis
	 */
	Map<Long, Object> findAll();

	/**
	 * @param id
	 *            Delete a key-value pair in Redis.
	 */
	void delete(Long id);

	/**
	 * @param message
	 * @throws JsonProcessingException
	 *             Publish message to Redis Channels
	 */
	void publishMessageToRedis(String message) throws JsonProcessingException;

}
