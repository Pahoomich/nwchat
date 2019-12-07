package com.nwchat.service;

import com.nwchat.entity.RoleEntity;
import com.nwchat.entity.UserEntity;
import com.nwchat.repository.RoleRepository;
import com.nwchat.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImplements implements UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImplements(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserEntity findUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public void saveUser(UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		RoleEntity userRole = roleRepository.findByRole("admin");
		user.setRole(userRole);
		userRepository.save(user);
	}

	@Override
	public UserEntity getAuthenticationUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return findUserByLogin(auth.getName());
	}
}
