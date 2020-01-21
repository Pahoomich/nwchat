package com.nwchat.сontroller;

import com.nwchat.entity.OrderEntity;
import com.nwchat.entity.ReportEntity;
import com.nwchat.entity.UserEntity;
import com.nwchat.plug.TestResultBind;
import com.nwchat.repository.TestCheckListRepo;
import com.nwchat.repository.TestOrderRepo;
import com.nwchat.repository.TestReportRepo;
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

class ReportControllerTest {

    private ReportController reportController;
    private TestOrderRepo testOrderRepo;
    private TestReportRepo testReportRepo;
    private TestUserRepo testUserRepo;
    private TestCheckListRepo testCheckListRepo;
    private TestUserServiceManager testUserServiceManager;
    private TestUserServiceLK testUserServiceLK;


    @BeforeEach
    void setUp() {
        testOrderRepo = new TestOrderRepo();
        testReportRepo = new TestReportRepo();
        testUserRepo = new TestUserRepo();
        testCheckListRepo = new TestCheckListRepo();
        testUserServiceManager = new TestUserServiceManager();

        reportController = new ReportController(testReportRepo, testOrderRepo, testUserRepo, testUserServiceManager);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void indexManager() {
        int id =2 ;
        ModelAndView show = reportController.index();

        UserEntity userEntity = testUserServiceManager.getAuthenticationUser();
        assertEquals(testReportRepo.findAllByCreatorIdEquals(id),show.getModel().get("reportList"));
        assertEquals(userEntity.getRoleId(), show.getModel().get("userRole"));
        assertEquals("report/index", show.getViewName());
    }

    @Test
    void indexLK() {
        int id =2 ;
        testUserServiceLK = new TestUserServiceLK();
        reportController = new ReportController(testReportRepo, testOrderRepo, testUserRepo, testUserServiceLK);
        ModelAndView show = reportController.index();

        UserEntity userEntity = testUserServiceLK.getAuthenticationUser();
        assertEquals(testReportRepo.findAllByStateEquals(id),show.getModel().get("reportList"));
        assertEquals(userEntity.getRoleId(), show.getModel().get("userRole"));
        assertEquals("report/index", show.getViewName());
    }
    @Test
    void show() {
        int id =0;
        UserEntity expend = testUserServiceManager.getAuthenticationUser();
        ReportEntity reportEntity =testReportRepo.findById(0).get();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity = testOrderRepo.findById(id).get();
        ModelAndView show = reportController.show(id);
        assertEquals("report/show", show.getViewName());
        assertEquals(expend.getRoleId(), show.getModel().get("userRole"));
        assertEquals(reportEntity, show.getModel().get("report"));
        assertEquals(orderEntity, show.getModel().get("order"));
    }

    @Test
    void showBad() {
        int id = 99;

        UserEntity expend = testUserServiceManager.getAuthenticationUser();

        assertThrows(java.util.NoSuchElementException.class,()->{
            reportController.show(id);
        });

    }
    @Test
    void save() {
        int id =2;

        OrderEntity orderEntity = testOrderRepo.findById(0).get();
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(2);
        reportEntity.setState(0);
        reportEntity.setNum("123");
        reportEntity.setText("123");
        reportEntity.setCreatorId(2);
        reportEntity.setOrderId(0);
        reportEntity.setAt(new Date(0));
        reportEntity.setOrdersByOrderId(orderEntity);
        BindingResult bindingResult = new TestResultBind() {
            @Override
            public boolean hasErrors() {
                return false;
            }
        };
        ModelAndView show = reportController.save(reportEntity,bindingResult);

        assertEquals("redirect:"+ id,show.getViewName() );
        assertEquals(reportEntity,testReportRepo.findById(id).get());
    }

    @Test
    void saveBad(){
        int id =2;

        OrderEntity orderEntity = testOrderRepo.findById(0).get();
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(2);
        reportEntity.setState(0);
        reportEntity.setNum("123");
        reportEntity.setText("123");
        reportEntity.setOrderId(0);
        reportEntity.setAt(new Date(0));
        reportEntity.setOrdersByOrderId(orderEntity);
        BindingResult bindingResult = new TestResultBind() {
            @Override
            public boolean hasErrors() {
                return false;
            }
        };
        ModelAndView show = reportController.save(reportEntity,bindingResult);
        assertEquals("redirect:"+ id,show.getViewName() );
    }
    @Test
    void create() {
        ModelAndView show = reportController.create();
        ReportEntity reportEntity = new ReportEntity();
        assertEquals("report/form",show.getViewName());
        assertEquals(reportEntity,show.getModel().get("report"));
        assertEquals(testOrderRepo.findAllByManagerIdEquals(2),show.getModel().get("orderList"));
    }

    @Test
    void send() {
        int id = 0;
        ModelAndView show = reportController.send(id);
        UserEntity userEntity = testUserServiceManager.getAuthenticationUser();
        ReportEntity reportEntity =testReportRepo.findById(id).get();

        assertEquals("redirect:/report/", show.getViewName());
        assertEquals(2, userEntity.getRoleId());
        Object report = show.getModel().get("report");
        ReportEntity save = testReportRepo.save(reportEntity);
        assertEquals(testReportRepo.save(reportEntity),show.getModel().get("report"));
    }

    @Test
    void sendBad() {
        int id = 2131312;
        assertThrows(java.util.NoSuchElementException.class,()->{
            reportController.update(id);
        });
    }
    @Test
    void update() {
        int id =0;
        List<OrderEntity> orderEntityList = new ArrayList<>();
        ModelAndView show = reportController.update(id);
        ReportEntity reportEntity = testReportRepo.findById(id).get();
        orderEntityList.addAll((List<OrderEntity>)testOrderRepo.findAll());
        assertEquals("report/form",show.getViewName());
        assertEquals(reportEntity,show.getModel().get("report"));
        assertEquals(orderEntityList,show.getModel().get("orderList"));
    }

    @Test
    void updateBad() {
        int id =2313231;

        assertThrows(java.util.NoSuchElementException.class,()->{
            reportController.update(id);
        });
    }


    @Test
    void delete() {
        int id =0;
        List<ReportEntity> reportEntityList = new ArrayList<>();
        reportEntityList.addAll((List<ReportEntity>) testReportRepo.findAll());
        ModelAndView show = reportController.delete(id);
        List<ReportEntity> reportEntityList1 =  (List<ReportEntity>)testReportRepo.findAll();

        assertEquals("redirect:/report/",show.getViewName());
        assertEquals(reportEntityList.size()-1, reportEntityList1.size());
    }
    @Test
    void deleteBad() {
        int id =2313231;

        assertThrows(java.util.NoSuchElementException.class,()->{
            reportController.delete(id);
        });
    }
}