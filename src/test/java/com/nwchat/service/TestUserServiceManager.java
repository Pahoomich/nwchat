package com.nwchat.service;

import com.nwchat.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class TestUserServiceManager implements UserService {

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

	}

	@Override
	public UserEntity getAuthenticationUser() {
		UserEntity user = new UserEntity();
		user.setRoleId(2);
		user.setId(2);
		return user;
	}

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<UserEntity> findAllByActive(Pageable pageable, int active) {
        return null;
    }

    @Override
    public Page<UserEntity> findAllByFioIgnoreCase(Pageable pageable, String name) {
        return null;
    }



    @Override
    public void deleteById(int id) {

    }

    @Override
    public UserEntity findById(int id) {
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
