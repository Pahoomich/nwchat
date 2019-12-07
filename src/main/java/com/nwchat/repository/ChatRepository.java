package com.nwchat.repository;

import com.nwchat.entity.ChatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends CrudRepository<ChatEntity, Integer> {
}
