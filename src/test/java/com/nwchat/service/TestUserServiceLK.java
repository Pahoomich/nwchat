package com.nwchat.service;

import com.nwchat.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUserServiceLK implements UserService {
	List<UserEntity> userEntitiesList = new ArrayList<>();

	public TestUserServiceLK( ) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setRoleId(1);
		userEntity.setFirstname("Dmitry");
		userEntity.setLastname("Prokin");
		userEntity.setActive(1);
		userEntity.setLogin("Dima");
		userEntity.setPassword("qwerty12");

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setId(2);
		userEntity1.setRoleId(2);
		userEntity1.setFirstname("Vadim");
		userEntity1.setLastname("Golunov");
		userEntity1.setActive(1);
		userEntity1.setLogin("Vadim");
		userEntity1.setPassword("qwerty12");

		saveUser(userEntity);
		saveUser(userEntity1);
	}

	@Override
	public UserEntity findUserByLogin(String login) {
		return null;
	}

	@Override
	public List<UserEntity> findAllUserByFIOEquals(String name, String surname) {
		return null;
	}

	@Override
	public void saveUser(UserEntity user) {
		userEntitiesList.add(user);
	}

	@Override
	public UserEntity getAuthenticationUser() {
		UserEntity user = new UserEntity();
		user.setRoleId(1);
		user.setId(0);
		return user;
	}

	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		Page<UserEntity> userEntityPage = new PageImpl<>(userEntitiesList);
		return userEntityPage;
	}

	@Override
	public Page<UserEntity> findAllByActive(Pageable pageable, int active) {


		List<UserEntity> userEntityList = new ArrayList<>();
		for (UserEntity user1 : userEntitiesList){
				if (user1.getActive() == 1) {
					userEntityList.add(user1);
				}
			}

		Page<UserEntity> userEntityPage = new PageImpl<>(userEntityList);
		return userEntityPage;
	}

	@Override
	public Page<UserEntity> findAllByFioIgnoreCase(Pageable pageable, String lastname) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setRoleId(1);
		userEntity.setFirstname("Dmitry");
		userEntity.setLastname("Prokin");
		userEntity.setActive(1);
		userEntity.setLogin("Dima");
		userEntity.setPassword("qwerty12");


		UserEntity userEntity1 = new UserEntity();
		userEntity1.setId(2);
		userEntity1.setRoleId(2);
		userEntity1.setFirstname("asd");
		userEntity1.setLastname("ivanov");
		userEntity1.setActive(1);
		userEntity1.setLogin("asd");
		userEntity1.setPassword("qwerty12");
		List<UserEntity> userEntityList = new ArrayList<>();
		if( userEntity.getLastname().equals(lastname)) {
			userEntityList.add(userEntity);
		}
		if( userEntity1.getLastname().equals(lastname)) {
			userEntityList.add(userEntity1);
		}
		Page<UserEntity> userEntityPage = new PageImpl<>(userEntityList);
		return userEntityPage;
	}

	@Override
	public void deleteById(int id) {

	}

	@Override
	public UserEntity findById(int id) {
		if (id > -1) {
			int i =0;
			for (UserEntity user1 : userEntitiesList){
				if (user1.getId() == id) {
					return  userEntitiesList.get(i);
				}
				i++;
			}

		}
		return null;
	}

	@Override
	public void saveSingUpUser(UserEntity user) {

	}

	@Override
	public Optional<UserEntity> findByActiveAndRoleId(int i, int i1) {
		return Optional.empty();
	}
}
