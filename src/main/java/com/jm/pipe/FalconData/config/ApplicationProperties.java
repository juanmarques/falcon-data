package com.jm.pipe.FalconData.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Juan Marques
 * @Date 2018-06-10
 */
@ConfigurationProperties(prefix = "raw", ignoreUnknownFields = false)
public class ApplicationProperties {

	private final Topic topic = new Topic();

	public static class Topic {
		private String message;
		private String count;

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public Topic getTopic() {
		return topic;
	}
}
