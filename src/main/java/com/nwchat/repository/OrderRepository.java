package com.nwchat.repository;

import com.nwchat.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository  extends CrudRepository<OrderEntity, Integer> {

	List<OrderEntity> findAllByManagerIdEquals (@Param("manager_id") Integer id);
}
