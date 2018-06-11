package com.jm.pipe.FalconData.model;

/**
 * @author Juan Marques
 * @Date 2018-06-10
 */
public class ChatMessage {

	private Long id;
	private String message;
	private String hostname;

	public ChatMessage(Long totalChatMessage, String message, String hostname) {
		this.id = totalChatMessage;
		this.message = message;
		this.hostname = hostname;
	}

	public ChatMessage() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Override
	public String toString() {
		return "ChatMessage{" + "id=" + id + ", message='" + message + '\'' + ", hostname='" + hostname + '\'' + '}';
	}
}
