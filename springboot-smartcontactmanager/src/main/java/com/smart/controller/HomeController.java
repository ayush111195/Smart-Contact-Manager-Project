package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home( Model m)
	{
		m.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
		
	}
	
	@GetMapping("/about")
	public String about( Model m)
	{
		m.addAttribute("title", "About-Smart Contact Manager");
		return "about";
		
	}
	@GetMapping("/signup")
	public String signup( Model m)
	{
		m.addAttribute("title", "signup-Smart Contact Manager");
		return "signup";
		
	}
}
