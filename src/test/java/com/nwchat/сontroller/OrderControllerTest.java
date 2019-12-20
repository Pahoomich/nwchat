package com.nwchat.сontroller;

import com.nwchat.entity.OrderEntity;
import com.nwchat.repository.testOrderRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderControllerTest {
	private OrderController orderController;
	private testOrderRepo testOrderRepo;


	@BeforeEach
	void setUp() {
		testOrderRepo = new testOrderRepo();
		orderController = new OrderController(testOrderRepo, );

	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void index() {

	}

	@Test
	void show() {
		int id = 0;

		Optional<OrderEntity> expend = testOrderRepo.findById(id);


		ModelAndView show = orderController.show(id);
		assertEquals("order/show", show.getViewName());
		assertEquals(expend, show.getModel().get("userRole"));
	}

	@Test
	void send() {
	}

	@Test
	void save() {
	}

	@Test
	void create() {
	}

	@Test
	void update() {
	}

	@Test
	void delete() {
	}
}