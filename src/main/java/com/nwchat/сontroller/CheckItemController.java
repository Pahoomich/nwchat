package com.nwchat.сontroller;


import com.nwchat.entity.CheckListItemEntity;
import com.nwchat.repository.CheckListItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class CheckItemController {

	private final CheckListItemRepository checkListItemRepository;

	public CheckItemController(CheckListItemRepository checkListItemRepository) {
		this.checkListItemRepository = checkListItemRepository;
	}

	private Date getSqlDate() {
		java.util.Date uDate = new java.util.Date();
		return new java.sql.Date(uDate.getTime());
	}

	@RequestMapping(value = "/{id}/c", method = RequestMethod.POST)
	public void check(@PathVariable(value = "id") int id) {
		Optional<CheckListItemEntity> c = checkListItemRepository.findById(id);
		if (!c.isPresent()) return;
		c.get().setDateEndWork(getSqlDate());
		checkListItemRepository.save(c.get());
	}

	@RequestMapping(value = "/{id}/u", method = RequestMethod.POST)
	public void unCheck(@PathVariable(value = "id") int id) {
		Optional<CheckListItemEntity> c = checkListItemRepository.findById(id);
		if (!c.isPresent()) return;
		c.get().setDateEndWork(null);
		checkListItemRepository.save(c.get());

	}
}
