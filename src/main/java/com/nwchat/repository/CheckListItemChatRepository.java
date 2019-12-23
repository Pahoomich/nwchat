package com.nwchat.repository;

import com.nwchat.entity.CheckListItemChatEntity;
import com.nwchat.entity.CheckListItemChatEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckListItemChatRepository
		extends CrudRepository<CheckListItemChatEntity, CheckListItemChatEntityPK> {

}
