package com.nwchat.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ChatUserEntityPK implements Serializable {
	private int chatId;
	private int userId;

	@Column(name = "chat_id", nullable = false)
	@Id
	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	@Column(name = "user_id", nullable = false)
	@Id
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ChatUserEntityPK that = (ChatUserEntityPK) o;

		if (chatId != that.chatId) return false;
		if (userId != that.userId) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = chatId;
		result = 31 * result + userId;
		return result;
	}
}
