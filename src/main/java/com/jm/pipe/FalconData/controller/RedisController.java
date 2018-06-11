package com.jm.pipe.FalconData.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jm.pipe.FalconData.repository.RedisRepository;

/**
 * @author Juan Marques
 * @Date 2018-06-09
 */
@RestController
public class RedisController {

	@Autowired
	private RedisRepository redisRepository;

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public void add(@RequestBody @Valid Object object, HttpServletResponse response) {
		redisRepository.save(object);
		response.setStatus(HttpStatus.CREATED.value());
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public Map<Long, Object> findAll() {
		Map<Long, Object> object = redisRepository.findAll();
		return object;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public Object findById(@RequestParam("id") Long id) {
		return redisRepository.find(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam("id") Long id, HttpServletResponse response) {
		redisRepository.delete(id);
		response.setStatus(HttpStatus.OK.value());
	}
}
