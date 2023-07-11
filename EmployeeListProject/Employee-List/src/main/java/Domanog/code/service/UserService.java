package Domanog.code.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import Domanog.code.model.User;
import Domanog.code.web.dto.UserRegisterDto;

public interface UserService extends UserDetailsService {
	User save(UserRegisterDto registerDto);
}
