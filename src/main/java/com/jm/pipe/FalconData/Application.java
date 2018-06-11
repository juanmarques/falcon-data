package com.jm.pipe.FalconData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import com.jm.pipe.FalconData.config.ApplicationProperties;


/**
 * @author Juan Marques 
 * @Date 2018-06-09
 */

@SpringBootApplication
@EnableAsync(proxyTargetClass=true)
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
