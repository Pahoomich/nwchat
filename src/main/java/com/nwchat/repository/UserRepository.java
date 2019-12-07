package com.nwchat.repository;

import com.nwchat.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.firstname = :name AND u.lastname = :surname")
    List<UserEntity> findAllByNameAndSurnameEquals(@Param("name") String name,
                                             @Param("surname") String surname);
}