package com.nwchat.service;

import com.nwchat.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserEntity findUserByLogin(String login);

    List <UserEntity> findAllUserByFIOEquals(String name, String surname);

    void saveUser(UserEntity user);

    UserEntity getAuthenticationUser();

    Page<UserEntity> findAll(Pageable pageable);

    Page<UserEntity> findAllByActive(Pageable pageable, int active);

    Page<UserEntity> findAllByLastnameContainingIgnoreCase(Pageable pageable, String lastname);

    void deleteById(int id);

    UserEntity findById(int id);

    void saveSingUpUser(UserEntity user);

}
