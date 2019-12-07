package com.nwchat.model;


public class ChatMessage {
	private MessageType type;
	private String content;
	private String sender;
	private int chatId;
	private int userId;
	private long dateTimeInMs;

	public enum MessageType {
		CHAT,
		JOIN,
		LEAVE
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Long getDateTimeInMs() {
		return dateTimeInMs;
	}

	public void setDateTimeInMs(Long ms) {
		this.dateTimeInMs = ms;
	}
}