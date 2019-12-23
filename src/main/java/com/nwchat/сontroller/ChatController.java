package com.nwchat.сontroller;

import com.nwchat.DTO.ChatFormDto;
import com.nwchat.DTO.ChatMessage;
import com.nwchat.entity.*;
import com.nwchat.repository.*;
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

import java.util.ArrayList;
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
	private final OrderRepository orderRepository;
	private final CheckListItemChatRepository checkListItemChatRepository;

	public ChatController(UserService userService,
	                      ChatRepository chatRepository,
	                      ChatUserRepository chatUserRepository,
	                      ChatMessageRepository chatMessageRepository,
	                      UserRepository userRepository,
	                      OrderRepository orderRepository,
	                      CheckListItemChatRepository checkListItemChatRepository) {
		this.userService = userService;
		this.chatRepository = chatRepository;
		this.chatUserRepository = chatUserRepository;
		this.chatMessageRepository = chatMessageRepository;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.checkListItemChatRepository = checkListItemChatRepository;
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
		Integer roleId = userService.getAuthenticationUser().getRoleId();


		model.addObject("chatList", chats);
		model.addObject("roleId", roleId);
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
		Integer roleId = userService.getAuthenticationUser().getRoleId();


		if (optChatEntity.isPresent() && chatUser.isPresent()) {
			model.addObject("chatName", optChatEntity.get().getName());
			model.addObject("chatId", optChatEntity.get().getId());
			model.addObject("userId", user.getId());
			model.addObject("userName", user.getFirstname() + " " + user.getLastname());
			model.addObject("roleId", roleId);


			ChatEntity chat = optChatEntity.get();
			List<CheckListItemChatEntity> li = chat.getCheckListItemChatsById();

			if (li.size()>0) {
				model.addObject("checkLists",li);
				model.addObject("eorderList", true);
			} else {
				model.addObject("eorderList", false);
			}

			model.setViewName("chat/chat");
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView save(ChatFormDto chatDto, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Integer roleId = userService.getAuthenticationUser().getRoleId();
		List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();
		UserEntity user = userService.getAuthenticationUser();
		userList.remove(user);

		List<ChatUserEntity> chatUserEntities = chatUserRepository.findAllByChatId(chatDto.getChatEntity().getId());
		List<Integer> selectUserList = chatUserEntities.stream().map(ChatUserEntity::getUserId).collect(Collectors.toList());


		if (result.hasErrors() || chatDto.getUserIds() == null) {
			if (roleId == 2) {
				List<OrderEntity> orderList = orderRepository.findAllByManagerIdEquals(user.getId());
				model.addObject("orderList", orderList);
				model.addObject("selectedOrder", chatDto.getOrderId());
				model.addObject("eorderList", true);
			} else {
				model.addObject("eorderList", false);

			}

			model.addObject("chat", chatDto.getChatEntity());
			model.addObject("roleId", roleId);
			model.addObject("userList", userList);
			model.addObject("selectUserList", selectUserList);
			model.setViewName("chat/form");
			return model;
		}
		ChatEntity chatEntity = chatRepository.save(chatDto.getChatEntity());
		int charId = chatEntity.getId();

		if (chatDto.getOrderId() != null) {
			Integer orderId = chatDto.getOrderId();
			Optional<OrderEntity> optOrder = orderRepository.findById(orderId);
			if (optOrder.isPresent()) {
				OrderEntity order = optOrder.get();
				List<CheckListItemEntity> items = order.getCheckListItemsById();

				for (CheckListItemEntity item : items) {
					CheckListItemChatEntity chatItem = new CheckListItemChatEntity();
					chatItem.setChatId(charId);
					chatItem.setCheckListItemId(item.getId());

					checkListItemChatRepository.save(chatItem);
				}
			}
		}

		chatUserRepository.deleteAllByChatId(chatEntity.getId());
		chatDto.getUserIds().add(user.getId());

		for (Integer userId : chatDto.getUserIds()) {
			ChatUserEntity chatUser = new ChatUserEntity();
			chatUser.setChatId(chatEntity.getId());
			chatUser.setUserId(userId);
			chatUserRepository.save(chatUser);
		}

		model.setViewName(String.format("redirect:%d", chatEntity.getId()));
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		ChatEntity chat = new ChatEntity();
		List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();
		UserEntity user = userService.getAuthenticationUser();
		userList.remove(user);

		Integer roleId = userService.getAuthenticationUser().getRoleId();

		ChatFormDto dto = new ChatFormDto();
		dto.setChatEntity(chat);
		Iterable<Integer> selectUserList = new ArrayList<>();


		if (roleId == 2) {
			List<OrderEntity> orderList = orderRepository.findAllByManagerIdEquals(user.getId());
			model.addObject("orderList", orderList);
			model.addObject("eorderList", true);

		} else {
			model.addObject("eorderList", false);

		}

		model.addObject("chat", chat);
		model.addObject("dto", dto);
		model.addObject("userList", userList);
		model.addObject("selectUserList", selectUserList);
		model.addObject("roleId", roleId);

		model.setViewName("chat/form");
		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Optional<ChatEntity> optChatEntity = chatRepository.findById(id);
		ChatEntity chatEntity = optChatEntity.get();

		List<ChatUserEntity> chatUserEntities = chatUserRepository.findAllByChatId(id);
		List<Integer> selectUserList = chatUserEntities.stream().map(ChatUserEntity::getUserId).collect(Collectors.toList());

		Integer roleId = userService.getAuthenticationUser().getRoleId();

		List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();
		UserEntity user = userService.getAuthenticationUser();
		userList.remove(user);

//		if (roleId == 2) {
//			List<OrderEntity> orderList = orderRepository.findAllByManagerIdEquals(user.getId());
//			model.addObject("orderList", orderList);
//		}

		model.addObject("eorderList", false);

		model.addObject("chat", chatEntity);
		model.addObject("userList", userList);
		model.addObject("selectUserList", selectUserList);
		model.addObject("roleId", roleId);
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
