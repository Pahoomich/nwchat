package com.nwchat.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chat", schema = "public", catalog = "nwchat")
public class ChatEntity {
	private int id;
	private String name;
	private List<ChatMessageEntity> chatMessagesById;
//	private List<UserEntity> usersById;
	private List<CheckListItemChatEntity> checkListItemChatsById;

	@Id
	@GeneratedValue
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

		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@OneToMany(mappedBy = "chatsByChatId", fetch = FetchType.LAZY)
	public List<ChatMessageEntity> getChatMessagesById() {
		return chatMessagesById;
	}

	public void setChatMessagesById(List<ChatMessageEntity> chatMessagesById) {
		this.chatMessagesById = chatMessagesById;
	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "chat_user")
//	public List<UserEntity> getUsersById() {
//		return usersById;
//	}
//
//	public void setUsersById(List<UserEntity> chatUsersById) {
//		this.usersById = chatUsersById;
//	}

	@OneToMany(mappedBy = "chatsByChatId",fetch = FetchType.LAZY)
	public List<CheckListItemChatEntity> getCheckListItemChatsById() {
		return checkListItemChatsById;
	}

	public void setCheckListItemChatsById(List<CheckListItemChatEntity> checkListItemChatsById) {
		this.checkListItemChatsById = checkListItemChatsById;
	}
}
