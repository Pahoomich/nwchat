package com.nwchat.DTO;

import com.nwchat.entity.ChatEntity;

import java.util.ArrayList;
import java.util.List;

public class ChatFormDto {
	private ChatEntity chatEntity;
	private List<Integer> userIds = new ArrayList<>();
	private Integer orderId;

	public ChatEntity getChatEntity() {
		return chatEntity;
	}

	public void setChatEntity(ChatEntity chatEntity) {
		this.chatEntity = chatEntity;
	}

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		userIds = userIds;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
