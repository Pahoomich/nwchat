package com.nwchat.repository;

import com.nwchat.entity.CheckListItemEntity;

import java.util.ArrayList;
import java.util.List;

public class TestCheckListRepo extends AbstTestRepo<CheckListItemEntity> implements CheckListItemRepository {

	public TestCheckListRepo() {
		List<CheckListItemEntity> orderList = new ArrayList<>();

//		OrderEntity orderEntity = new OrderEntity();
//		orderEntity.setId(0);
//		orderEntity.setState(0);
//		orderEntity.setNum("123");
//		orderEntity.setText("123");
//		orderEntity.setTitle("123");
//		orderEntity.setCreatorId(1);
//		orderEntity.setManagerId(2);
//		orderEntity.setAt(new Date(0));

//		orderList.add(orderEntity);


		saveAll(orderList);
	}

	@Override
	public List<CheckListItemEntity> findAllByOrderIdEquals(Integer id) {
		return null;
	}
}
