package com.nwchat.repository;

import com.nwchat.entity.CheckListItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CheckListItemRepository
		extends CrudRepository<CheckListItemEntity, Integer> {
	List<CheckListItemEntity> findAllByOrderIdEquals(@Param("order_id") Integer id);
}