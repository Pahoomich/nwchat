package com.nwchat.entity;

import javax.persistence.*;
import java.util.Objects;

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
		return Objects.equals(chatId, that.chatId) &&
				Objects.equals(checkListItemId, that.checkListItemId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatId, checkListItemId);
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
