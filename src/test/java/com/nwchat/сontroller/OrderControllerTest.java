package com.nwchat.сontroller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {
    OrderController orderController;

    @BeforeEach
    void setUp() {
        orderController = new OrderController();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void index() {

    }

    @Test
    void show() {

        ModelAndView show = orderController.show(0);
        assertEquals("order/show",show.getViewName());
        assertEquals(show.getModel().get("userRole"));
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