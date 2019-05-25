package com.nwchat.сontroller;

import com.nwchat.model.ChatMessage;
import com.nwchat.model.User;
import com.nwchat.repository.UserRepository;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */

@Controller

public class ChatController {
    private final UserService userService;

    public ChatController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private UserRepository userRepository;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView index() {
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByLogin(auth.getName());
//        String username = user.getFirstName();
//
//        model.addObject("username", username);
//        model.setViewName("home/chat");
//        return model;
//    }
@RequestMapping(value = "/chat/chat", method = RequestMethod.GET)
public ModelAndView chat(HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByLogin(auth.getName());

    model.addObject("userName", user.getFirstName() + " " + user.getLastName());
    model.setViewName("chat/chat");
    return model;
}

    @MessageMapping("chat/chat.sendMessage")
    @SendTo("/chat/chat/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("chat/chat.addUser")
    @SendTo("/chat/chat/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}

