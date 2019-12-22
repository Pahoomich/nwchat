package com.nwchat.repository;

import com.nwchat.entity.ChatMessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessageEntity, Integer> {
	List<ChatMessageEntity> findAllByChatId(int chatId);

}
