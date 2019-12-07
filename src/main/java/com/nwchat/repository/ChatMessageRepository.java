package com.nwchat.repository;

import com.nwchat.entity.ChatMessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessageEntity, Integer> {
}
