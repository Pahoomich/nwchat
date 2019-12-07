package com.nwchat.сontroller;

import com.nwchat.entity.ChatEntity;
import com.nwchat.entity.ChatUserEntity;
import com.nwchat.entity.UserEntity;
import com.nwchat.model.ChatMessage;
import com.nwchat.repository.ChatRepository;
import com.nwchat.repository.ChatUserRepository;
import com.nwchat.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping("/chat")
public class ChatController {

	private final UserService userService;
	private final ChatRepository chatRepository;
	private final ChatUserRepository chatUserRepository;

	public ChatController(UserService userService,
	                      ChatRepository chatRepository,
	                      ChatUserRepository chatUserRepository) {
		this.userService = userService;
		this.chatRepository = chatRepository;
		this.chatUserRepository = chatUserRepository;
	}

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,
	                           SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		UserEntity user = userService.getAuthenticationUser();
		Set<ChatEntity> chats = user.getChats();

		model.addObject("chatList", chats);
		model.setViewName("chat/index");
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable(value = "id") int id) {
		ModelAndView model = new ModelAndView();
		UserEntity user = userService.getAuthenticationUser();
		Optional<ChatEntity> optChatEntity = chatRepository.findById(id);
		Optional<ChatUserEntity> chatUser = chatUserRepository.findByChatIdAndUserId(id, user.getId());

		if (optChatEntity.isPresent() && chatUser.isPresent()) {
			model.addObject("chatName", optChatEntity.get().getName());
			model.addObject("chatId", optChatEntity.get().getId());
			model.addObject("userName", user.getFirstname() + " " + user.getLastname());
			model.setViewName("chat/chat");
		} else {
			model.setViewName("redirect:/");
		}

		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView create(ChatEntity chatEntity, BindingResult result) {
		ModelAndView model = new ModelAndView();

		if (result.hasErrors()) {
			model.addObject("chat", chatEntity);
			model.setViewName("chat/form");
			return model;
		}

		chatEntity = chatRepository.save(chatEntity);

		model.setViewName(String.format("redirect:%d", chatEntity.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		UserEntity user = userService.getAuthenticationUser();
		ChatEntity chat = new ChatEntity();

		model.addObject("chat", chat);
		model.setViewName("chat/form");
		return model;
	}
}
