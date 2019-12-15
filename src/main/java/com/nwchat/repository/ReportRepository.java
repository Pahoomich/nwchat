package com.nwchat.repository;

import com.nwchat.entity.OrderEntity;
import com.nwchat.entity.ReportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends CrudRepository<ReportEntity, Integer> {
    List<ReportEntity> findAllByCreatorIdEquals (@Param("creator_id") Integer id);
    List<ReportEntity> findAllByStateEquals(@Param("state")Integer state);
}
