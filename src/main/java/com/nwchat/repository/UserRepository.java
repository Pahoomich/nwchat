package com.nwchat.repository;

import com.nwchat.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.firstname = :name AND u.lastname = :surname")
    List<UserEntity> findAllByNameAndSurnameEquals(@Param("name") String name,
                                             @Param("surname") String surname);

    List< UserEntity> findAllByRoleIdEquals(@Param ("role_id") Integer role_Id);

    Page<UserEntity> findAllByLastnameContainingIgnoreCase(Pageable pageable, @Param("lastname") String lastname);

    UserEntity findById(long id);

}