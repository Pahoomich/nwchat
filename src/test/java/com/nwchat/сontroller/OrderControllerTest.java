//package com.nwchat.сontroller;
//
//import com.nwchat.entity.UserEntity;
//import com.nwchat.repository.TestCheckListRepo;
//import com.nwchat.repository.TestOrderRepo;
//import com.nwchat.repository.TestUserRepo;
//import com.nwchat.service.TestUserService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.servlet.ModelAndView;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class OrderControllerTest {
//	private OrderController orderController;
//	private TestOrderRepo testOrderRepo;
//	private TestUserRepo testUserRepo;
//	private TestCheckListRepo testCheckListRepo;
//	private TestUserService testUserService;
//
//
//	@BeforeEach
//	void setUp() {
//		testOrderRepo = new TestOrderRepo();
//		testUserRepo = new TestUserRepo();
//		testCheckListRepo = new TestCheckListRepo();
//		testUserService = new TestUserService();
//
//		orderController = new OrderController(testOrderRepo, testUserRepo, testUserService, testCheckListRepo);
//
//	}
//
//	@AfterEach
//	void tearDown() {
//	}
//
//	@Test
//	void index() {
//
//	}
//
//	@Test
//	void showGood() {
//		int id = 0;
//
//		UserEntity expend = testUserService.getAuthenticationUser();
//
//
//		ModelAndView show = orderController.show(id);
//		assertEquals("order/show", show.getViewName());
//		assertEquals(expend.getRoleId(), show.getModel().get("userRole"));
//	}
//
//	@Test
//	void showBad() {
//		int id = 99;
//
//		UserEntity expend = testUserService.getAuthenticationUser();
//
//
//
//		assertThrows(Exception.class,()->{
//			orderController.show(id);
//		});
//	}
//
//
//	@Test
//	void send() {
//	}
//
//	@Test
//	void save() {
//	}
//
//	@Test
//	void create() {
//	}
//
//	@Test
//	void update() {
//	}
//
//	@Test
//	void delete() {
//	}
//}