package com.nwchat.repository;

import com.nwchat.entity.ChatUserEntity;
import com.nwchat.entity.ChatUserEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface ChatUserRepository extends CrudRepository<ChatUserEntity, ChatUserEntityPK> {
	Optional<ChatUserEntity> findByChatIdAndUserId(int chatId, int userId);
	List<ChatUserEntity> findAllByChatId(int chatId);

	@Transactional
	void deleteAllByChatId(int chatId);
}
