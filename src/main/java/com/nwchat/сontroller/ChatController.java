package com.nwchat.сontroller;

import com.nwchat.DTO.ChatFormDto;
import com.nwchat.DTO.ChatMessage;
import com.nwchat.entity.ChatEntity;
import com.nwchat.entity.ChatMessageEntity;
import com.nwchat.entity.ChatUserEntity;
import com.nwchat.entity.UserEntity;
import com.nwchat.repository.ChatMessageRepository;
import com.nwchat.repository.ChatRepository;
import com.nwchat.repository.ChatUserRepository;
import com.nwchat.repository.UserRepository;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/chat")
public class ChatController {

	private final UserService userService;
	private final ChatRepository chatRepository;
	private final ChatUserRepository chatUserRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final UserRepository userRepository;

	public ChatController(UserService userService,
	                      ChatRepository chatRepository,
	                      ChatUserRepository chatUserRepository,
	                      ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
		this.userService = userService;
		this.chatRepository = chatRepository;
		this.chatUserRepository = chatUserRepository;
		this.chatMessageRepository = chatMessageRepository;
		this.userRepository = userRepository;
	}

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		ChatMessageEntity chatMessageEntity = new ChatMessageEntity(chatMessage);
		chatMessageRepository.save(chatMessageEntity);
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
		//user.getRole();
		Optional<ChatEntity> optChatEntity = chatRepository.findById(id);
		Optional<ChatUserEntity> chatUser = chatUserRepository.findByChatIdAndUserId(id, user.getId());

		if (optChatEntity.isPresent() && chatUser.isPresent()) {
			model.addObject("chatName", optChatEntity.get().getName());
			model.addObject("chatId", optChatEntity.get().getId());
			model.addObject("userId", user.getId());
			model.addObject("userName", user.getFirstname() + " " + user.getLastname());
			model.setViewName("chat/chat");
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView save(ChatFormDto chatDto, BindingResult result) {
		ModelAndView model = new ModelAndView();

		if (result.hasErrors() || chatDto.getUserIds() == null) {
			model.addObject("chat", chatDto);
			model.setViewName("chat/form");
			return model;
		}
		ChatEntity chatEntity = chatRepository.save(chatDto.getChatEntity());
		UserEntity user = userService.getAuthenticationUser();

		for (String strUserId : chatDto.getUserIds()) {
			if (!strUserId.trim().equals("")) {

				int userId = Integer.parseInt(strUserId);

				ChatUserEntity chatUser = new ChatUserEntity();
				chatUser.setChatId(chatEntity.getId());
				chatUser.setUserId(userId);
				chatUserRepository.save(chatUser);
			}
		}

		model.setViewName(String.format("redirect:%d", chatEntity.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		ChatEntity chat = new ChatEntity();
		Iterable<UserEntity> userList = userRepository.findAll();


		model.addObject("chat", chat);
		model.addObject("userList", userList);
		model.setViewName("chat/form");
		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Optional<ChatEntity> optChatEntity = chatRepository.findById(id);
		ChatEntity chatEntity = optChatEntity.get();


		Iterable<UserEntity> userList = userRepository.findAll();

		model.addObject("chat", chatEntity);
		model.addObject("userList", userList);
		model.setViewName("chat/form");


		return model;
	}

	@RequestMapping(value = "/{id}/hist", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ChatMessage> getHistory(@PathVariable int id) {
		List<ChatMessageEntity> entities = chatMessageRepository.findAllByChatId(id);
		return entities.stream().map(ChatMessage::new).collect(Collectors.toList());
	}
}
