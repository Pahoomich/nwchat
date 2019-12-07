package com.nwchat.repository;

import com.nwchat.entity.PersistentLoginsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersistentLoginRepository
		extends CrudRepository<PersistentLoginsEntity, String> {
}
