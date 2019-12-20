package com.nwchat.repository;

import com.nwchat.entity.OrderEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class testOrderRepo implements OrderRepository {
	List<OrderEntity> orderList;

	public testOrderRepo() {
		orderList = new ArrayList<>();

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(0);
		orderEntity.setState(0);
		orderEntity.setNum("123");
		orderEntity.setText("123");
		orderEntity.setTitle("123");
		orderEntity.setCreatorId(1);
		orderEntity.setManagerId(2);
		orderEntity.setAt(new Date(0));

		orderList.add(orderEntity);
	}

	@Override
	public List<OrderEntity> findAllByManagerIdEquals(Integer id) {
		return null;
	}

	@Override
	public <S extends OrderEntity> S save(S s) {
		return null;
	}

	@Override
	public <S extends OrderEntity> Iterable<S> saveAll(Iterable<S> iterable) {
		return null;
	}

	@Override
	public Optional<OrderEntity> findById(Integer integer) {
		if (integer > -1 && integer < orderList.size()) {
			return Optional.of(orderList.get(integer));
		}
		return Optional.empty();
	}

	@Override
	public boolean existsById(Integer integer) {
		return false;
	}

	@Override
	public Iterable<OrderEntity> findAll() {
		return null;
	}

	@Override
	public Iterable<OrderEntity> findAllById(Iterable<Integer> iterable) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(Integer integer) {

	}

	@Override
	public void delete(OrderEntity orderEntity) {

	}

	@Override
	public void deleteAll(Iterable<? extends OrderEntity> iterable) {

	}

	@Override
	public void deleteAll() {

	}
}
