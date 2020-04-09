package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	
	@GetMapping("/403")
	public String notAutorized() {

		return "403" ; 
	}
	@GetMapping("/login")
	public String login() {

		return "login" ; 
	}
	
	
	@RequestMapping(value="/logout")
	public String logout() {
		return "redirect:/login";
	}
	
	
}
