package com.nwchat.repository;

import com.nwchat.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class TestUserRepo extends AbstTestRepo<UserEntity> implements UserRepository {


	public TestUserRepo() {
		List<UserEntity> orderList = new ArrayList<>();

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
	public UserEntity findByLogin(String login) {
		return null;
	}

	@Override
	public List<UserEntity> findAllByNameAndSurnameEquals(String name, String surname) {
		return null;
	}

	@Override
	public List<UserEntity> findAllByRoleIdEquals(Integer role_Id) {
		return null;
	}
}
