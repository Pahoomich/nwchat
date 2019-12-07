package com.nwchat.service;

import com.nwchat.entity.UserEntity;

public interface UserService {
    UserEntity findUserByLogin(String login);

    void saveUser(UserEntity user);
}
