package com.nwchat.сontroller;

import com.nwchat.entity.UserEntity;
import com.nwchat.plug.TestResultBind;
import com.nwchat.repository.TestCheckListRepo;
import com.nwchat.repository.TestOrderRepo;
import com.nwchat.repository.TestReportRepo;
import com.nwchat.repository.TestUserRepo;
import com.nwchat.service.TestUserServiceLK;
import com.nwchat.service.TestUserServiceManager;
import org.apache.catalina.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;

    private TestUserServiceLK testUserServiceLK;
    @BeforeEach
    void setUp() {

        testUserServiceLK = new TestUserServiceLK();
        userController = new UserController(testUserServiceLK);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void userList() {
        Model model=new ConcurrentModel();

        UserEntity userEntity = testUserServiceLK.getAuthenticationUser();
        String url = userController.userList(model,1,10,"");

        Object roleId = model.getAttribute("roleId");
        PageRequest pageable = PageRequest.of(1 - 1, 10);
        assertEquals("users/list", url);
        assertEquals(userEntity.getRoleId(),model.getAttribute("roleId"));
        assertEquals(testUserServiceLK.findAllByActive(pageable,1),model.getAttribute("userPage"));
        String urls = userController.userList(model,1,10,"Prokin");
        Object userPage = model.getAttribute("userPage");
        assertEquals(testUserServiceLK.findAllByFioIgnoreCase(pageable,"Prokin"),model.getAttribute("userPage"));

    }

    @Test
    void showUser() {
        Model model=new ConcurrentModel();

        UserEntity userEntity = testUserServiceLK.getAuthenticationUser();
        UserEntity userEntity1 =null;
        String url = userController.showUser( model,userEntity);
        assertEquals("users/form", url);
        assertEquals(userEntity.getRoleId(), model.getAttribute("roleId"));
        assertEquals(userEntity, model.getAttribute("user"));
        String urls = userController.showUser( model,userEntity1);
        userEntity1 = new UserEntity();
        assertEquals(userEntity1, model.getAttribute("user"));
    }

    @Test
    void addNewUser() {
        BindingResult bindingResult = new TestResultBind() {
            @Override
            public boolean hasErrors() {
                return false;
            }
        };


        UserEntity user = new UserEntity();
        user.setPassword("userManager");
        user.setActive(1);
        user.setRoleId(2);
        user.setId(4);
        user.setFirstname("Vova");
        user.setLastname("bondarenko");
        user.setLogin("vova");
        SessionStatus status = new SessionStatus() {
            boolean stat = false;

            @Override
            public void setComplete() {
                stat = true;
            }

            @Override
            public boolean isComplete() {
                return stat;
            }
        };

        String url = userController.addNewUser( user,bindingResult,status);
        assertEquals("redirect:", url);
        assertEquals(user,testUserServiceLK.findById(4));
    }

    @Test
    void deleteUser() {
        int id=1;
        Pageable pageable = null;
        Page<UserEntity> all = testUserServiceLK.findAllByActive(pageable,1);
        UserEntity userEntity = testUserServiceLK.findById(id);
        String url = userController.deleteUser( id);
        Page<UserEntity> alls = testUserServiceLK.findAllByActive(pageable,1);
        assertEquals( "redirect:",url);
        assertEquals(all.getNumberOfElements()-1,alls.getNumberOfElements());

    }
}