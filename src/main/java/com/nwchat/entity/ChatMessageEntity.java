package com.nwchat.entity;

import com.nwchat.model.ChatMessage;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "chat_message", schema = "public", catalog = "nwchat")
public class ChatMessageEntity {
	private Integer id;
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

	public ChatMessageEntity() {
	}

	public ChatMessageEntity(ChatMessage chatMessage) {
		authorId = chatMessage.getUserId();
		chatId = chatMessage.getChatId();
		text = chatMessage.getContent();
		dateCreate = new Timestamp(chatMessage.getDateTimeInMs());
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

		return Objects.equals(id, that.id) &&
				Objects.equals(text, that.text) &&
				Objects.equals(chatId, that.chatId) &&
				Objects.equals(authorId, that.authorId) &&
				Objects.equals(dateCreate, that.dateCreate) &&
				Objects.equals(parentMessageId, that.parentMessageId) &&
				Objects.equals(responseToMessageId, that.responseToMessageId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, text, chatId, authorId, dateCreate, parentMessageId, responseToMessageId);
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
