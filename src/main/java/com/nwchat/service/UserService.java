package com.nwchat.service;

import com.nwchat.model.User;

public interface UserService {
    public User findUserByLogin(String login);

    public void saveUser(User user);
}
