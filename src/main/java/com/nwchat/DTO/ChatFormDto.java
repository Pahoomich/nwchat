package com.nwchat.DTO;

import com.nwchat.entity.ChatEntity;

import java.util.List;

public class ChatFormDto {
	private ChatEntity chatEntity;
	private List<String> UserIds;

	public ChatEntity getChatEntity() {
		return chatEntity;
	}

	public void setChatEntity(ChatEntity chatEntity) {
		this.chatEntity = chatEntity;
	}

	public List<String> getUserIds() {
		return UserIds;
	}

	public void setUserIds(List<String> userIds) {
		UserIds = userIds;
	}
}
