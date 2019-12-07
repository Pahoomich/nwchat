package com.nwchat.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "chat_message", schema = "public", catalog = "nwchat")
public class ChatMessageEntity {
	private int id;
	private String text;
	private Integer chatId;
	private Integer authorId;
	private Timestamp dateCreate;
	private Integer parentMessageId;
	private Integer responseToMessageId;
	private ChatEntity chatsByChatId;
	private ChatMessageEntity chatMessageByParentMessageId;
	private Collection<ChatMessageEntity> chatMessagesById;
	private ChatMessageEntity chatMessageByResponseToMessageId;
	private Collection<ChatMessageEntity> chatMessagesById_0;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "text", nullable = true, length = -1)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Basic
	@Column(name = "chat_id", nullable = true)
	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	@Basic
	@Column(name = "author_id", nullable = true)
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Basic
	@Column(name = "date_create", nullable = true)
	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Basic
	@Column(name = "parent_message_id", nullable = true)
	public Integer getParentMessageId() {
		return parentMessageId;
	}

	public void setParentMessageId(Integer parentMessageId) {
		this.parentMessageId = parentMessageId;
	}

	@Basic
	@Column(name = "response_to_message_id", nullable = true)
	public Integer getResponseToMessageId() {
		return responseToMessageId;
	}

	public void setResponseToMessageId(Integer responseToMessageId) {
		this.responseToMessageId = responseToMessageId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ChatMessageEntity that = (ChatMessageEntity) o;

		if (id != that.id) return false;
		if (text != null ? !text.equals(that.text) : that.text != null) return false;
		if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;
		if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
		if (dateCreate != null ? !dateCreate.equals(that.dateCreate) : that.dateCreate != null) return false;
		if (parentMessageId != null ? !parentMessageId.equals(that.parentMessageId) : that.parentMessageId != null)
			return false;
		if (responseToMessageId != null ? !responseToMessageId.equals(that.responseToMessageId) : that.responseToMessageId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
		result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
		result = 31 * result + (dateCreate != null ? dateCreate.hashCode() : 0);
		result = 31 * result + (parentMessageId != null ? parentMessageId.hashCode() : 0);
		result = 31 * result + (responseToMessageId != null ? responseToMessageId.hashCode() : 0);
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
	@JoinColumn(name = "parent_message_id", referencedColumnName = "id", insertable = false, updatable = false)
	public ChatMessageEntity getChatMessageByParentMessageId() {
		return chatMessageByParentMessageId;
	}

	public void setChatMessageByParentMessageId(ChatMessageEntity chatMessageByParentMessageId) {
		this.chatMessageByParentMessageId = chatMessageByParentMessageId;
	}

	@OneToMany(mappedBy = "chatMessageByParentMessageId")
	public Collection<ChatMessageEntity> getChatMessagesById() {
		return chatMessagesById;
	}

	public void setChatMessagesById(Collection<ChatMessageEntity> chatMessagesById) {
		this.chatMessagesById = chatMessagesById;
	}

	@ManyToOne
	@JoinColumn(name = "response_to_message_id", referencedColumnName = "id", insertable = false, updatable = false)
	public ChatMessageEntity getChatMessageByResponseToMessageId() {
		return chatMessageByResponseToMessageId;
	}

	public void setChatMessageByResponseToMessageId(ChatMessageEntity chatMessageByResponseToMessageId) {
		this.chatMessageByResponseToMessageId = chatMessageByResponseToMessageId;
	}

	@OneToMany(mappedBy = "chatMessageByResponseToMessageId")
	public Collection<ChatMessageEntity> getChatMessagesById_0() {
		return chatMessagesById_0;
	}

	public void setChatMessagesById_0(Collection<ChatMessageEntity> chatMessagesById_0) {
		this.chatMessagesById_0 = chatMessagesById_0;
	}
}
