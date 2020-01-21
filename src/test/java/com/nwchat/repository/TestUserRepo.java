package com.nwchat.repository;

import com.nwchat.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUserRepo extends AbstTestRepo<UserEntity> implements UserRepository {


	public TestUserRepo() {

		List<UserEntity> orderList = new ArrayList<>();
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

		orderList.add(userEntity);
		orderList.add(userEntity1);
		saveAll(orderList);
	}


	@Override
	public Page<UserEntity> findAllByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrderByRoleId(Pageable pageable, String firstname, String lastname) {
		return null;
	}

	@Override
	public UserEntity findById(int id) {
		return null;
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


		List<UserEntity> lists = new ArrayList<>();

		if (role_Id == 2){
		UserEntity userEntity1 = new UserEntity();
		userEntity1.setId(2);
		userEntity1.setRoleId(2);
		userEntity1.setFirstname("Vadim");
		userEntity1.setLastname("Golunov");
		userEntity1.setActive(1);
		userEntity1.setLogin("Vadim");
		userEntity1.setPassword("qwerty12");

		lists.add(userEntity1);
		}
		else if ( role_Id == 1){
			UserEntity userEntity = new UserEntity();
			userEntity.setId(1);
			userEntity.setRoleId(1);
			userEntity.setFirstname("Dmitry");
			userEntity.setLastname("Prokin");
			userEntity.setActive(1);
			userEntity.setLogin("Dima");
			userEntity.setPassword("qwerty12");
			lists.add(userEntity);
		}

		return lists;
	}
    @Override
    public Page<UserEntity> findAllByActive(Pageable pageable, int active) {

		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setRoleId(1);
		userEntity.setFirstname("Dmitry");
		userEntity.setLastname("Prokin");
		userEntity.setActive(1);
		userEntity.setLogin("Dima");
		userEntity.setPassword("qwerty12");
		List<UserEntity> userEntities = new ArrayList<>();
		userEntities.add(userEntity);
		Page<UserEntity> userEntityPage = new PageImpl<>(userEntities);
		return userEntityPage;
    }

	@Override
	public Optional<UserEntity> findByActiveAndRoleId(int active, int roleId) {
		return Optional.empty();
	}

	@Override
    public Iterable<UserEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return null;
    }
}

