package es.ubu.lsi.TallerJPA.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import es.ubu.lsi.TallerJPA.Model.User;
import es.ubu.lsi.TallerJPA.Services.databaseService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	
	@Autowired
	private databaseService databaseService;
	
	@GetMapping("/listarUsuarios")
	public String listar(Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		model.addAttribute("usuarios", databaseService.findAllUsers());
		model.addAttribute("titulo", "Lista de usuarios");
		return "listar";
	}
	
	@GetMapping("/formUsuario")
	public String crear(Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		User usuario = new User();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Listado usuarios");
		return "form";
	}
	
	@GetMapping("/profile")
	public String perfil(Model model, HttpSession session) {
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		User usuario = databaseService.findOne(session.getAttribute("email").toString());
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Perfil del usuario");
		return "form";
	}
	
	@GetMapping("/formUsuario/{email}")
	public String editar(@PathVariable(value = "email") String email, Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		User usuario = null;
		if(!email.isEmpty()) {
			usuario = databaseService.findOne(email);
		}else {
			return "redirect:/listarUsuarios";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Listado usuarios");
		model.addAttribute("edit", true);
		
		return "form";
	}
	
	@PostMapping("/formUsuario")
	public String guardar(@Validated User usuario, BindingResult result, Model model, SessionStatus status, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		System.out.println(usuario.toString());
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado usuarios");
			return "form";
		}
		databaseService.save(usuario);
		status.setComplete();
		return "redirect:/listarUsuarios";
	}
	
	@GetMapping("/eliminarUsuario/{email}")
	public String eliminar(@PathVariable(value = "email") String email, Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		if (!email.isEmpty()) {
			databaseService.eliminar(email);
		}
		return "redirect:/listarUsuarios";
	}

}
