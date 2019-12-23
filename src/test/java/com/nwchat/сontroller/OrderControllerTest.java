package com.nwchat.сontroller;

import com.nwchat.entity.OrderEntity;
import com.nwchat.entity.UserEntity;
import com.nwchat.plug.TestResultBind;
import com.nwchat.repository.TestCheckListRepo;
import com.nwchat.repository.TestOrderRepo;
import com.nwchat.repository.TestUserRepo;
import com.nwchat.service.TestUserServiceLK;
import com.nwchat.service.TestUserServiceManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderControllerTest {
	private OrderController orderController;
	private TestOrderRepo testOrderRepo;
	private TestUserRepo testUserRepo;
	private TestCheckListRepo testCheckListRepo;
	private TestUserServiceLK testUserServiceLK;
	private TestUserServiceManager testUserServiceManager;


	@BeforeEach
	void setUp() {
		testOrderRepo = new TestOrderRepo();
		testUserRepo = new TestUserRepo();
		testCheckListRepo = new TestCheckListRepo();
		testUserServiceLK = new TestUserServiceLK();

		orderController = new OrderController(testOrderRepo, testUserRepo, testUserServiceLK, testCheckListRepo);

	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void indexLK() {
		int ManagerId = 2;

		ModelAndView show = orderController.index();

		UserEntity userEntity = testUserServiceLK.getAuthenticationUser();

		assertEquals(testOrderRepo.findAll(),show.getModel().get("orderList"));
		assertEquals(userEntity.getRoleId(), show.getModel().get("userRole"));
		Object orderList = show.getModel().get("orderList");
		List<OrderEntity> allByManagerIdEquals = testOrderRepo.findAllByManagerIdEquals(1);
		assertEquals( testOrderRepo.findAllByManagerIdEquals(ManagerId), show.getModel().get("orderList"));
		assertEquals("order/index", show.getViewName());

	}

	@Test
	void indexManager() {
		int ManagerId = 2;
		testUserServiceManager = new TestUserServiceManager();
		orderController = new OrderController(testOrderRepo, testUserRepo, testUserServiceManager, testCheckListRepo);
		ModelAndView show = orderController.index();

		UserEntity userEntity = testUserServiceManager.getAuthenticationUser();

		assertEquals(testOrderRepo.findAllByManagerIdEquals(2),show.getModel().get("orderList"));
		assertEquals(userEntity.getRoleId(), show.getModel().get("userRole"));
		Object orderList = show.getModel().get("orderList");
		List<OrderEntity> allByManagerIdEquals = testOrderRepo.findAllByManagerIdEquals(1);
		assertEquals( testOrderRepo.findAllByManagerIdEquals(ManagerId), show.getModel().get("orderList"));
		assertEquals("order/index", show.getViewName());

	}


	@Test
	void showGood() {
		int id = 0;

		UserEntity expend = testUserServiceLK.getAuthenticationUser();
		OrderEntity orderEntity =testOrderRepo.findById(id).get();

		ModelAndView show = orderController.show(id);
		assertEquals("order/show", show.getViewName());
		assertEquals(expend.getRoleId(), show.getModel().get("userRole"));
		assertEquals(orderEntity, show.getModel().get("order"));
	}

	@Test
	void showBad() {
		int id = 99;

		UserEntity expend = testUserServiceLK.getAuthenticationUser();

		assertThrows(java.util.NoSuchElementException.class,()->{
			orderController.show(id);
		});

	}


	@Test
	void send() {
		int id = 0;
		ModelAndView show = orderController.send(id);
		UserEntity userEntity = testUserServiceLK.getAuthenticationUser();
		OrderEntity orderEntity =testOrderRepo.findById(id).get();

		assertEquals("redirect:/order/", show.getViewName());
		assertEquals(1, userEntity.getRoleId());
		//assertEquals(orderEntity, show.getModel().get("order"));
		assertEquals(testOrderRepo.save(orderEntity),show.getModel().get("order"));
	}

	@Test
	void sendBad() {
		int id = 9923;
		assertThrows(java.util.NoSuchElementException.class,()->{
			orderController.send(id);
		});

	}
	@Test
	void save() {
		int id =3;
		UserEntity userEntity = new UserEntity();
		userEntity.setId(2);
		userEntity.setRoleId(2);
		userEntity.setFirstname("Pavel");
		userEntity.setLastname("Ivanov");
		userEntity.setActive(1);
		userEntity.setLogin("Pavel");
		userEntity.setPassword("qwerty12");

		OrderEntity orderEntity =new OrderEntity();
		orderEntity.setId(id);
		orderEntity.setState(0);
		orderEntity.setNum("ы");
		orderEntity.setText("ы");
		orderEntity.setTitle("ы");
		orderEntity.setCreatorId(1);
		orderEntity.setManagerId(2);
		orderEntity.setManager(userEntity);
		orderEntity.setAt(new Date(0));
		orderEntity.setCheckListItemsById(testCheckListRepo.findAllByOrderIdEquals(3));
		BindingResult bindingResult = new TestResultBind() {
			@Override
			public boolean hasErrors() {
				return false;
			}
		};
		ModelAndView show = orderController.save(orderEntity,bindingResult);

		assertEquals("redirect:"+ id,show.getViewName() );
		assertEquals(orderEntity,testOrderRepo.findById(2).get());
	}

	@Test
	void saveBad(){
		int id =3;
		UserEntity userEntity = new UserEntity();
		userEntity.setId(2);
		userEntity.setRoleId(2);
		userEntity.setFirstname("Pavel");
		userEntity.setLastname("Ivanov");
		userEntity.setActive(1);
		userEntity.setLogin("Pavel");
		userEntity.setPassword("qwerty12");

		OrderEntity orderEntity =new OrderEntity();
		orderEntity.setId(id);
		orderEntity.setState(0);
		orderEntity.setNum("ы");
		orderEntity.setText("ы");
		orderEntity.setTitle("ы");
		orderEntity.setCreatorId(1);
		orderEntity.setManagerId(2);
		orderEntity.setManager(userEntity);
		orderEntity.setAt(new Date(0));
		orderEntity.setCheckListItemsById(testCheckListRepo.findAllByOrderIdEquals(3));
		BindingResult bindingResult = new TestResultBind() {
			@Override
			public boolean hasErrors() {
				return true;
			}
		};
		ModelAndView show = orderController.save(orderEntity,bindingResult);
		assertEquals("order/form",show.getViewName() );
		assertEquals( orderEntity,show.getModel().get("order"));
		assertEquals(testUserRepo.findAll(),show.getModel().get("userList"));
	}

	@Test
	void create() {
		ModelAndView show = orderController.create();
		OrderEntity orderEntity = new OrderEntity();
		assertEquals("order/form",show.getViewName());
		assertEquals(orderEntity,show.getModel().get("order"));
		assertEquals(testUserRepo.findAllByRoleIdEquals(2),show.getModel().get("userList"));
	}


	@Test
	void update() {
		int id =1;
		ModelAndView show = orderController.update(id);
		OrderEntity orderEntity =  testOrderRepo.findById(id).get();
		assertEquals("order/form",show.getViewName());
		assertEquals(orderEntity,show.getModel().get("order"));
		assertEquals(testUserRepo.findAllByRoleIdEquals(2),show.getModel().get("userList"));

	}

	@Test
	void updateBad() {
		int id =2313231;

		assertThrows(java.util.NoSuchElementException.class,()->{
			orderController.update(id);
		});
	}
	@Test
	void delete() {
		int id =1;
		List<OrderEntity> orderEntity = new ArrayList<>();
		orderEntity.addAll((List<OrderEntity>) testOrderRepo.findAll());
		ModelAndView show = orderController.delete(id);
		List<OrderEntity> orderEntity1 =  (List<OrderEntity>)testOrderRepo.findAll();

		assertEquals("redirect:/order/",show.getViewName());
		assertEquals(orderEntity.size()-1, orderEntity1.size());
	}

	@Test
	void deleteBad() {
		int id =2313231;

		assertThrows(java.util.NoSuchElementException.class,()->{
			orderController.delete(id);
		});
	}
}