package com.nwchat.сontroller;

import com.nwchat.entity.CheckListItemEntity;
import com.nwchat.entity.OrderEntity;
import com.nwchat.repository.CheckListItemRepository;
import com.nwchat.repository.OrderRepository;
import com.nwchat.repository.UserRepository;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CheckListItemRepository checkListItemRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		if (roleId == 1) {
			Iterable<OrderEntity> listOrder = orderRepository.findAll();
			model.addObject("orderList", listOrder);
			model.addObject("userRole", roleId);
			model.setViewName("order/index");
		} else if (roleId == 2) {
			List<OrderEntity> listOrders = orderRepository.findAllByManagerIdEquals(userService.getAuthenticationUser().getId());
			model.addObject("orderList", listOrders);
			model.addObject("userRole", roleId);
			model.setViewName("order/index");
		}
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value = "id") int id) {
		ModelAndView model = new ModelAndView();
		OrderEntity orderEntity = orderRepository.findById(id).get();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		model.addObject("userRole", roleId);
		model.addObject("order", orderEntity);
		model.setViewName("order/show");

		return model;
	}

	@RequestMapping(value = "/{id}/send", method = RequestMethod.GET)
	public ModelAndView send(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		OrderEntity orderEntity = orderRepository.findById(id).get();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		if (roleId == 1) {
			orderEntity.setState(1);
			orderEntity = orderRepository.save(orderEntity);
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
		orderEntity.setCreatorId(userService.getAuthenticationUser().getId());
		orderEntity.setState(0);

		if (result.hasErrors()) {

			model.addObject("userList", userRepository.findAll());
			model.addObject("order", orderEntity);
			//model.addObject("checkListItem", checkListItemEntityList);
			model.setViewName("order/form");
			return model;
		}
		List<CheckListItemEntity> checkListItemsById = orderEntity.getCheckListItemsById();
		orderEntity = orderRepository.save(orderEntity);
		for (CheckListItemEntity entity : checkListItemsById) {
			entity.setOrderId(orderEntity.getId());
		}
		checkListItemRepository.saveAll(checkListItemsById);
		model.setViewName(String.format("redirect:%d", orderEntity.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();

		OrderEntity orderEntity = new OrderEntity();
		//CheckListItemEntity checkListItemEntity = new CheckListItemEntity();
		//List<CheckListItemEntity> checkListItemEntityList = new ArrayList<>();
		//checkListItemEntityList.add(checkListItemEntity);
		model.addObject("order", orderEntity);
		model.addObject("userList", userRepository.findAllByRoleIdEquals(2));
	//	model.addObject("checkListItem", checkListItemEntityList);
		model.setViewName("order/form");
		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Optional<OrderEntity> optOrder = orderRepository.findById(id);
	//	List<CheckListItemEntity> checkListItemEntity = checkListItemRepository.findAllByOrderIdEquals(id);
		OrderEntity orderEntity = optOrder.get();
		if (orderEntity.getState() == 0) {
			orderEntity.setCreatorId(userService.getAuthenticationUser().getId());

			if (optOrder.isPresent()) {
				orderEntity.setState(0);
				model.addObject("order", orderEntity);
				//model.addObject("checkListItem",checkListItemEntity);
				model.addObject("userList", userRepository.findAllByRoleIdEquals(2));
				model.setViewName("order/form");
			} else {
				model.setViewName("redirect:/order/");
			}
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
