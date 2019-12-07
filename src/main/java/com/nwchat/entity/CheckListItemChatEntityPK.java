package com.nwchat.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

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

		if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;
		if (checkListItemId != null ? !checkListItemId.equals(that.checkListItemId) : that.checkListItemId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = chatId != null ? chatId.hashCode() : 0;
		result = 31 * result + (checkListItemId != null ? checkListItemId.hashCode() : 0);
		return result;
	}
}
