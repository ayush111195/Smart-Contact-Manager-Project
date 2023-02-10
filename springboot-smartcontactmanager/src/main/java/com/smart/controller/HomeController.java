package com.smart.controller;

import java.util.EmptyStackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepo;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.Session;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncodery;

	@Autowired
	private UserRepo userRepo;

	//handler for custom home--->>
	
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Home-Smart Contact Manager");
		return "home";

	}

	//handler for custom about--->>
	
	@GetMapping("/about")
	public String about(Model m) {
		m.addAttribute("title", "About-Smart Contact Manager");
		return "about";

	}

	//handler for custom signup--->>
	
	@GetMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("title", "signup-Smart Contact Manager");
		m.addAttribute("user", new User());
		return "signup";

	}

	// this handler for registering user

	@PostMapping("/do_register")
	public String registerUser( @Valid@ModelAttribute("user") User user,BindingResult result1,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m,
			HttpSession session ) {

		try {
			if (!agreement) {
				System.out.println("You Have Not Agreed Terms And Conditions");

				throw new Exception("You Have Not Agreed Terms And Conditions");

			}
			
			if(result1.hasErrors()) {
				System.out.println("errors"+result1.toString());
				m.addAttribute("user", user);
				return"signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("image");
			user.setPassword(passwordEncodery.encode(user.getPassword()));

			System.out.println("Agreement" + agreement);
			System.out.println("user" + user);

			User save = this.userRepo.save(user);

			m.addAttribute("user", new User());

			session.setAttribute("message", new Message("Successfully Registered!!", "alert-success"));

			return "signup";

		} catch (Exception e) {

			e.printStackTrace();
			m.addAttribute("user", user);
			session.setAttribute("message", new Message("Somthing  Went Worng!!" + e.getMessage(), "alert-danger"));
			return "signup";
		}

	}
	
	//handler for custom login--->>
	
	@GetMapping("/signin")
	public String customLoging(Model m)
	{
		m.addAttribute("title", "login-Smart Contact Manager");

		return"login";
		
	}
}
