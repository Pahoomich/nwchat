package com.nwchat.сontroller;

import com.nwchat.entity.CheckListItemEntity;
import com.nwchat.entity.OrderEntity;
import com.nwchat.repository.CheckListItemRepository;
import com.nwchat.repository.OrderRepository;
import com.nwchat.repository.UserRepository;
import com.nwchat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
	private final OrderRepository orderRepository;

	private final UserRepository userRepository;
	private final UserService userService;
	private final CheckListItemRepository checkListItemRepository;

	public OrderController(OrderRepository orderRepository, UserRepository userRepository, UserService userService, CheckListItemRepository checkListItemRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.checkListItemRepository = checkListItemRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		Integer roleId = userService.getAuthenticationUser().getRoleId();

		ModelAndView model = new ModelAndView();
		if (roleId == 1) {
			Iterable<OrderEntity> listOrder = orderRepository.findAll();
			model.addObject("orderList", listOrder);
			model.addObject("userRole", roleId);
			model.addObject("roleId", roleId);
			model.setViewName("order/index");
		} else if (roleId == 2) {
			List<OrderEntity> listOrders = orderRepository.findAllByManagerIdEquals(userService.getAuthenticationUser().getId());
			model.addObject("orderList", listOrders);
			model.addObject("userRole", roleId);
			model.addObject("roleId", roleId);
			model.setViewName("order/index");
		}
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value = "id") int id) {
		Integer roleId = userService.getAuthenticationUser().getRoleId();

		ModelAndView model = new ModelAndView();
		OrderEntity orderEntity = orderRepository.findById(id).get();
		model.addObject("userRole", roleId);
		model.addObject("order", orderEntity);
		model.addObject("roleId", roleId);
		model.setViewName("order/show");

		return model;
	}

	private Date getSqlDate() {
		java.util.Date uDate = new java.util.Date();
		return new java.sql.Date(uDate.getTime());
	}

	@RequestMapping(value = "/{id}/send", method = RequestMethod.GET)
	public ModelAndView send(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		OrderEntity orderEntity = orderRepository.findById(id).get();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		List<CheckListItemEntity> itemlist = checkListItemRepository.findAllByOrderIdEquals(orderEntity.getId());
		Date now = getSqlDate();
		for (CheckListItemEntity item : itemlist) {
			item.setDateStartWork(now);
		}

		if (roleId == 1) {
			orderEntity.setState(1);
			orderEntity = orderRepository.save(orderEntity);
			checkListItemRepository.saveAll(itemlist);

			model.addObject("order", orderEntity);
			model.setViewName("redirect:/order/");
		} else {
			model.setViewName("redirect:/order/");
		}
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("OrderEntity") OrderEntity orderEntity, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		orderEntity.setCreatorId(userService.getAuthenticationUser().getId());
		orderEntity.setState(0);

		if (result.hasErrors()) {

			model.addObject("userList", userRepository.findAll());
			model.addObject("order", orderEntity);
			model.addObject("roleId", roleId);
			model.setViewName("order/form");
			return model;
		}
		List<CheckListItemEntity> checkListItemsById = orderEntity.getCheckListItemsById();
		orderEntity = orderRepository.save(orderEntity);


		checkListItemRepository.deleteAllByOrderId(orderEntity.getId());

		List<CheckListItemEntity> endList = new ArrayList<>();
		for (CheckListItemEntity entity : checkListItemsById) {
			entity.setOrderId(orderEntity.getId());
			entity.setDateStartWork(getSqlDate());
			if (entity.getName() != null && !entity.getName().trim().equals(""))
				endList.add(entity);
		}

		checkListItemRepository.saveAll(endList);
		model.setViewName(String.format("redirect:%d", orderEntity.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		OrderEntity orderEntity = new OrderEntity();

		ModelAndView model = new ModelAndView();
		model.addObject("order", orderEntity);
		model.addObject("userList", userRepository.findAllByRoleIdEquals(2));
		model.addObject("roleId", roleId);
		model.setViewName("order/form");
		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Integer roleId = userService.getAuthenticationUser().getRoleId();

		Optional<OrderEntity> optOrder = orderRepository.findById(id);
		OrderEntity orderEntity = optOrder.get();
		if (optOrder.isPresent() && orderEntity.getState() == 0) {

			orderEntity.setState(0);
			model.addObject("order", orderEntity);
			model.addObject("userList", userRepository.findAllByRoleIdEquals(2));
			model.addObject("roleId", roleId);
			model.setViewName("order/form");
		} else {
			model.setViewName("redirect:/order/");
		}

		return model;
	}

	@RequestMapping(value = "/{id}/delete")
	public ModelAndView delete(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		OrderEntity orderEntity = orderRepository.findById(id).get();
		Integer state = orderEntity.getState();
		if ((roleId == 1) && (state == 0)) {
			orderRepository.deleteById(id);
			model.setViewName("redirect:/order/");
		} else {
			model.setViewName("redirect:/order/");
		}
		return model;
	}


}
