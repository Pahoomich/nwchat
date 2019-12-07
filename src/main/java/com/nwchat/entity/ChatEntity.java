package com.nwchat.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "chat", schema = "public", catalog = "nwchat")
public class ChatEntity {
	private int id;
	private String name;
	private Collection<ChatMessageEntity> chatMessagesById;
	private Collection<ChatUserEntity> chatUsersById;
	private Collection<CheckListItemChatEntity> checkListItemChatsById;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = true, length = -1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ChatEntity that = (ChatEntity) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,name);
	}

	@OneToMany(mappedBy = "chatsByChatId")
	public Collection<ChatMessageEntity> getChatMessagesById() {
		return chatMessagesById;
	}

	public void setChatMessagesById(Collection<ChatMessageEntity> chatMessagesById) {
		this.chatMessagesById = chatMessagesById;
	}

	@ManyToMany
	@JoinTable(name = "chat_user")
	public Collection<ChatUserEntity> getChatUsersById() {
		return chatUsersById;
	}

	public void setChatUsersById(Collection<ChatUserEntity> chatUsersById) {
		this.chatUsersById = chatUsersById;
	}

	@OneToMany(mappedBy = "chatsByChatId")
	public Collection<CheckListItemChatEntity> getCheckListItemChatsById() {
		return checkListItemChatsById;
	}

	public void setCheckListItemChatsById(Collection<CheckListItemChatEntity> checkListItemChatsById) {
		this.checkListItemChatsById = checkListItemChatsById;
	}
}
