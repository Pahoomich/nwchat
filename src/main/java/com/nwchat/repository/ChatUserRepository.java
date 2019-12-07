package com.nwchat.repository;

import com.nwchat.entity.ChatUserEntity;
import com.nwchat.entity.ChatUserEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatUserRepository extends CrudRepository<ChatUserEntity, ChatUserEntityPK> {
}
