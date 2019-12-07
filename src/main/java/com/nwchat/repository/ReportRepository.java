package com.nwchat.repository;

import com.nwchat.entity.ReportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository
		extends CrudRepository<ReportEntity, Integer> {
}
