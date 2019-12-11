package com.nwchat.repository;

import com.nwchat.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository
		extends CrudRepository<OrderEntity, Integer> {
    
}
