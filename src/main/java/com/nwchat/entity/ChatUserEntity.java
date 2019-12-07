package com.nwchat.entity;

import javax.persistence.*;
import java.util.Objects;

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

		return Objects.equals(chatId, that.chatId) &&
				Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatId, userId);
	}
}
