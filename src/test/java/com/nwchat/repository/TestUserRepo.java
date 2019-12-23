//package com.nwchat.repository;
//
//import com.nwchat.entity.UserEntity;
//import org.apache.tomcat.util.buf.UEncoder;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class TestUserRepo extends AbstTestRepo<UserEntity> implements UserRepository {
//
//
//	public TestUserRepo() {
//		List<UserEntity> orderList = new ArrayList<>();
//
////		OrderEntity orderEntity = new OrderEntity();
////		orderEntity.setId(0);
////		orderEntity.setState(0);
////		orderEntity.setNum("123");
////		orderEntity.setText("123");
////		orderEntity.setTitle("123");
////		orderEntity.setCreatorId(1);
////		orderEntity.setManagerId(2);
////		orderEntity.setAt(new Date(0));
//
////		orderList.add(orderEntity);
//
//
//		saveAll(orderList);
//	}
//
//	public UserEntity findByLogin(String login) {
//		return null;
//	}
//
//	public List<UserEntity> findAllByNameAndSurnameEquals(String name, String surname) {
//		return null;
//	}
//
//	public List<UserEntity> findAllByRoleIdEquals(Integer role_Id) {
//		return null;
//	}
//
//	@Override
//	public Page<UserEntity> findAllByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrderByRoleId(Pageable pageable, String firstname, String lastname) {
//		return null;
//	}
//
//	@Override
//	public UserEntity findById(int id) {
//		return null;
//	}
//
//	@Override
//	public Page<UserEntity> findAllByActive(Pageable pageable, int active) {
//		return null;
//	}
//
//	@Override
//	public Optional<UserEntity> findByActiveAndRoleId(int active, int roleId) {
//		return Optional.empty();
//	}
//
//	@Override
//	public Iterable<UserEntity> findAll(Sort sort) {
//		return null;
//	}
//
//	@Override
//	public Page<UserEntity> findAll(Pageable pageable) {
//		return null;
//	}
//}
