package com.nwchat.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CheckListItemChatEntityPK implements Serializable {
	private Integer chatId;
	private Integer checkListItemId;

	@Column(name = "chat_id", nullable = false)
	@Basic
	@Id
	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	@Column(name = "check_list_item_id", nullable = false)
	@Basic
	@Id
	public Integer getCheckListItemId() {
		return checkListItemId;
	}

	public void setCheckListItemId(Integer checkListItemId) {
		this.checkListItemId = checkListItemId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CheckListItemChatEntityPK that = (CheckListItemChatEntityPK) o;
		return Objects.equals(chatId, that.chatId) &&
				Objects.equals(checkListItemId, that.checkListItemId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatId, checkListItemId);
	}
}
