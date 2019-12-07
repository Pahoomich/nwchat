package com.nwchat.repository;

import com.nwchat.entity.CheckListItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckListItemRepository
		extends CrudRepository<CheckListItemEntity, Integer> {
}
