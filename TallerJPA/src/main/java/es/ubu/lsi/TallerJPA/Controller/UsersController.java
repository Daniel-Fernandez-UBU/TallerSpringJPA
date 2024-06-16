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

/**
 * Clase UsersController.
 * 
 * Controladores de los usuarios de la aplicación.
 * 
 * @author Daniel Fernández Barrientos
 * @author Ismael Manzanera López
 * 
 * @version 1.0
 * 
 */
@Controller
public class UsersController {
	
	/** The database service. */
	@Autowired
	private databaseService databaseService;
	
	/**
	 * Listar.
	 *
	 * @param model the model
	 * @param session the session
	 * @return the string
	 */
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
	
	/**
	 * Crear.
	 *
	 * @param model the model
	 * @param session the session
	 * @return the string
	 */
	@GetMapping("/formUsuario")
	public String crear(Model model, HttpSession session) {
		
		User usuario = new User();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Lista de usuarios");
		return "form";
	}
		
	/**
	 * Editar.
	 *
	 * @param email the email
	 * @param model the model
	 * @param session the session
	 * @return the string
	 */
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
		model.addAttribute("titulo", "Hola, " + usuario.getEmail());
		model.addAttribute("edit", true);
		
		return "form";
	}
	
	/**
	 * Guardar.
	 *
	 * @param usuario the usuario
	 * @param result the result
	 * @param model the model
	 * @param status the status
	 * @param session the session
	 * @return the string
	 */
	@PostMapping("/formUsuario")
	public String guardar(@Validated User usuario, BindingResult result, Model model, SessionStatus status, HttpSession session) {	
		
		// Si la contraseña no se actualiza, se mantiene la misma.
		if (usuario.getPassword().isEmpty()) {
			User user = databaseService.findOne(usuario.getEmail());
			usuario.setPassword(user.getPassword());
		// Si la contraseña solo contiene espacios en blanco, se avisa para que se solucione esto.
		} else if (usuario.getPassword().isBlank()) {
			model.addAttribute("titulo", "Hola, " + usuario.getEmail());
			model.addAttribute("blank", true);
			model.addAttribute("edit", true);
			model.addAttribute("usuario", usuario);
			return "form";
		}
				
		databaseService.save(usuario);
		status.setComplete();
		return "home";
	}
	
	/**
	 * Eliminar.
	 *
	 * @param email the email
	 * @param model the model
	 * @param session the session
	 * @return the string
	 */
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
