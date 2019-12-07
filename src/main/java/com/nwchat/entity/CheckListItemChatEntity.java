package com.nwchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "check_list_item_chat", schema = "public", catalog = "nwchat")
@IdClass(CheckListItemChatEntityPK.class)
public class CheckListItemChatEntity {
	private Integer chatId;
	private Integer checkListItemId;
	private ChatEntity chatsByChatId;
	private CheckListItemEntity checkListItemsByCheckListItemId;

	@Id
	@Basic
	@Column(name = "chat_id", nullable = false)
	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	@Id
	@Basic
	@Column(name = "check_list_item_id", nullable = false)
	public Integer getCheckListItemId() {
		return checkListItemId;
	}

	public void setCheckListItemId(int checkListItemId) {
		this.checkListItemId = checkListItemId;
	}

	public void setCheckListItemId(Integer checkListItemId) {
		this.checkListItemId = checkListItemId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CheckListItemChatEntity that = (CheckListItemChatEntity) o;

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

	@ManyToOne
	@JoinColumn(name = "chat_id", referencedColumnName = "id", insertable = false, updatable = false)
	public ChatEntity getChatsByChatId() {
		return chatsByChatId;
	}

	public void setChatsByChatId(ChatEntity chatsByChatId) {
		this.chatsByChatId = chatsByChatId;
	}

	@ManyToOne
	@JoinColumn(name = "check_list_item_id", referencedColumnName = "id", insertable = false, updatable = false)
	public CheckListItemEntity getCheckListItemsByCheckListItemId() {
		return checkListItemsByCheckListItemId;
	}

	public void setCheckListItemsByCheckListItemId(CheckListItemEntity checkListItemsByCheckListItemId) {
		this.checkListItemsByCheckListItemId = checkListItemsByCheckListItemId;
	}
}
