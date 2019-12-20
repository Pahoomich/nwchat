package com.nwchat.service;

import com.nwchat.entity.UserEntity;

public class TestUserService implements UserService {

	@Override
	public UserEntity findUserByLogin(String login) {
		return null;
	}

	@Override
	public void saveUser(UserEntity user) {

	}

	@Override
	public UserEntity getAuthenticationUser() {
		UserEntity user = new UserEntity();
		user.setRoleId(1);
		return user;
	}
}
