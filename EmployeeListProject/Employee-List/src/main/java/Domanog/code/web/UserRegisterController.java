package Domanog.code.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Domanog.code.service.UserService;
import Domanog.code.web.dto.UserRegisterDto;

@Controller
@RequestMapping("/register")
public class UserRegisterController {
	
private UserService userService;
	
	public UserRegisterController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegisterDto userRegisterDto() {
		return new UserRegisterDto();
	}
	
	@GetMapping
	public String displayRegisterFrom() {
		return "register";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegisterDto registerDto) {
		userService.save(registerDto);
		return "redirect:/register?success";
	}
	
}