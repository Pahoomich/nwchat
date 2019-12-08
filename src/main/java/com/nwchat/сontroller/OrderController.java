package com.nwchat.сontroller;

import com.nwchat.entity.OrderEntity;
import com.nwchat.entity.UserEntity;
import com.nwchat.model.Doc;
import com.nwchat.repository.OrderRepository;
import com.nwchat.repository.UserRepository;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
	private  UserService userService;

	

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        Iterable<OrderEntity> listOrder =  orderRepository.findAll();
        model.addObject("orderList", listOrder);
        model.setViewName("order/index");
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value = "id") int id) {
		ModelAndView model = new ModelAndView();

		OrderEntity orderEntity = orderRepository.findById(id).get();
        model.addObject("order", orderEntity);
		model.setViewName("order/show");
		return model;
	}

    @RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView create(@Valid OrderEntity orderEntity, BindingResult result) {
		ModelAndView model = new ModelAndView();
		orderEntity.setCreatorId(userService.getAuthenticationUser().getId());
		System.out.println(result.getAllErrors());
		if (result.hasErrors()) {
			model.addObject("userList",userRepository.findAll());
			model.addObject("order", orderEntity);
			model.setViewName("order/form");
			return model;
		}
		System.out.println(userService.getAuthenticationUser().getId());

		orderEntity = orderRepository.save(orderEntity);

		model.setViewName(String.format("redirect:%d", orderEntity.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();

		OrderEntity orderEntity = new OrderEntity();
		model.addObject("order", orderEntity);
		model.addObject("userList",userRepository.findAll());//todo list manager
		model.setViewName("order/form");
		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Optional<OrderEntity> optOrder = orderRepository.findById(id);
		//UserEntity user = userService.getAuthenticationUser();
		if (optOrder.isPresent()) {
			model.addObject("order", optOrder.get());
			model.addObject("userList",userRepository.findAll());//todo list manager
			model.setViewName("order/form");
		}
		else {
			model.setViewName("redirect:/order/");
		}


		return model;
	}

	@RequestMapping(value = "/{id}/delete")
	public ModelAndView delete(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		orderRepository.deleteById(id);
		model.setViewName("redirect:/order/");
		return model;
	}


}
