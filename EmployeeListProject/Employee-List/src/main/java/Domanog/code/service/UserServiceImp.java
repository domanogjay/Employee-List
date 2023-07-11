package Domanog.code.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Domanog.code.model.Role;
import Domanog.code.model.User;
import Domanog.code.model.repositoryUser.UserRepository;

import Domanog.code.web.dto.UserRegisterDto;

@Service
public class UserServiceImp implements UserService {
	
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public UserServiceImp(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public User save(UserRegisterDto registerDto) {
		User user = new User(registerDto.getFirstName(), 
				registerDto.getLastName(), registerDto.getEmail(),
				passwordEncoder.encode(registerDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
	return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Login Credentials");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		// TODO Auto-generated method stub
		return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
