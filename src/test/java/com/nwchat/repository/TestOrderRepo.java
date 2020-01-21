package com.nwchat.repository;

import com.nwchat.entity.OrderEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestOrderRepo extends AbstTestRepo<OrderEntity> implements OrderRepository {


	public TestOrderRepo() {
		List<OrderEntity> orderList = new ArrayList<>();

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(0);
		orderEntity.setState(1);
		orderEntity.setNum("123");
		orderEntity.setText("123");
		orderEntity.setTitle("123");
		orderEntity.setCreatorId(1);
		orderEntity.setManagerId(2);
		orderEntity.setAt(new Date(0));

		OrderEntity orderEntity2 = new OrderEntity();
		orderEntity2.setId(1);
		orderEntity2.setState(0);
		orderEntity2.setNum("12223");
		orderEntity2.setText("12223");
		orderEntity2.setTitle("12223");
		orderEntity2.setCreatorId(1);
		orderEntity2.setManagerId(2);
		orderEntity2.setAt(new Date(0));

//		OrderEntity orderEntity3 = new OrderEntity();
//		orderEntity3.setId(3);
//		orderEntity3.setState(1);
//		orderEntity3.setNum("ыфвпмсмч");
//		orderEntity3.setText("чмссисми");
//		orderEntity3.setTitle("мсимс");
//		orderEntity3.setCreatorId(1);
//		orderEntity3.setManagerId(2);
//		orderEntity3.setAt(new Date(0));

		orderList.add(orderEntity);
		orderList.add(orderEntity2);
	//	orderList.add(orderEntity3);

		saveAll(orderList);
	}

	@Override
	public List<OrderEntity> findAllByManagerIdEquals(Integer id) {
		OrderEntity orderEntity1 = new OrderEntity();
		orderEntity1.setId(0);
		orderEntity1.setState(1);
		orderEntity1.setNum("123");
		orderEntity1.setText("123");
		orderEntity1.setTitle("123");
		orderEntity1.setCreatorId(1);
		orderEntity1.setManagerId(2);
		orderEntity1.setAt(new Date(0));

		OrderEntity orderEntity2 = new OrderEntity();
		orderEntity2.setId(1);
		orderEntity2.setState(0);
		orderEntity2.setNum("12223");
		orderEntity2.setText("12223");
		orderEntity2.setTitle("12223");
		orderEntity2.setCreatorId(1);
		orderEntity2.setManagerId(2);
		orderEntity2.setAt(new Date(0));
		List<OrderEntity> orderEntityList = new ArrayList<>();

			if (orderEntity1.getManagerId() == id) orderEntityList.add(orderEntity1);
			if (orderEntity2.getManagerId() == id)orderEntityList.add(orderEntity2);

		return orderEntityList;
	}
}
