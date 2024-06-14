package es.ubu.lsi.TallerJPA.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ubu.lsi.TallerJPA.Services.databaseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private databaseService databaseService;
	
	@GetMapping("/")
	public String mainPage(HttpSession session, Model model) {
		
		if (session.getAttribute("email") != null) {
			model.addAttribute("logued", true);
			session.setAttribute("logued", true);
		} else {
			session.setAttribute("logued", false);
		}
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		
		if (session.getAttribute("email") != null) {
			session.removeAttribute("email");
			session.setAttribute("logued", false);
			model.addAttribute("info", "Sesión cerrada");
		}
		return "home";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/checkLogin")
	public String checkLogin(Model model,  HttpSession session, @RequestParam("email") String email
			,@RequestParam("password") String password) {
				
		if (databaseService.checkRegistered(email, password)) {
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			model.addAttribute("logued", true);
			return "redirect:/";
		}

		
		model.addAttribute("nouser", true);
		return "login";
	}
	
	
	


}
