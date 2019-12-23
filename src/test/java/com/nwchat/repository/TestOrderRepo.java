//package com.nwchat.repository;
//
//import com.nwchat.entity.OrderEntity;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestOrderRepo extends AbstTestRepo<OrderEntity> implements OrderRepository {
//
//
//	public TestOrderRepo() {
//		List<OrderEntity> orderList = new ArrayList<>();
//
//		OrderEntity orderEntity = new OrderEntity();
//		orderEntity.setId(0);
//		orderEntity.setState(0);
//		orderEntity.setNum("123");
//		orderEntity.setText("123");
//		orderEntity.setTitle("123");
//		orderEntity.setCreatorId(1);
//		orderEntity.setManagerId(2);
//		orderEntity.setAt(new Date(0));
//
//		orderList.add(orderEntity);
//
//
//		saveAll(orderList);
//	}
//
//	@Override
//	public List<OrderEntity> findAllByManagerIdEquals(Integer id) {
//		return null;
//	}
//}
