package com.nwchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "chat_user", schema = "public", catalog = "nwchat")
@IdClass(ChatUserEntityPK.class)
public class ChatUserEntity {
	private int chatId;
	private int userId;

	@Id
	@Column(name = "chat_id", nullable = false)
	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	@Id
	@Column(name = "user_id", nullable = false)
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

		ChatUserEntity that = (ChatUserEntity) o;

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
