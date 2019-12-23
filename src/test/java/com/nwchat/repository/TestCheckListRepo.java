package com.nwchat.repository;

import com.nwchat.entity.CheckListItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class TestCheckListRepo extends AbstTestRepo<CheckListItemEntity> implements CheckListItemRepository {
	List<CheckListItemEntity> checkListItemEntities = new ArrayList<>();
	public TestCheckListRepo() {


		CheckListItemEntity checkListItemEntity = new CheckListItemEntity();
		checkListItemEntity.setId(1);
		checkListItemEntity.setName("challenge1");
		checkListItemEntity.setOrderId(3);
		checkListItemEntity.setDateStartWork(new Date(0));

		checkListItemEntities.add(checkListItemEntity);


		saveAll(checkListItemEntities);
	}

	@Override
	public List<CheckListItemEntity> findAllByOrderIdEquals(Integer id) {

		return checkListItemEntities;
	}

	@Override
	public void deleteAllByOrderId(int ids) {

	}
}
