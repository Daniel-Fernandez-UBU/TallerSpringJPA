package es.ubu.lsi.TallerJPA.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.ubu.lsi.TallerJPA.Model.User;
import es.ubu.lsi.TallerJPA.Services.databaseService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private databaseService databaseService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("clientes", databaseService.findAll());
		model.addAttribute("titulo", "Listando la lista de clientes");
		return "listar";
	}
	
	@GetMapping("/form")
	public String crear(Map<String, Object> model) {
		User cliente = new User();
		model.put("cliente", cliente);
		model.put("titulo", "Listado Clientes");
		return "form";
	}
	
	@GetMapping("/form/{email}")
	public String editar(@PathVariable(value = "email") String email, Map<String, Object> model) {
		
		User cliente = null;
		if(!email.isEmpty()) {
			cliente = databaseService.findOne(email);
		}else {
			return "redirect:listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Listado Clientes");
		model.put("edit", true);
		
		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Validated User cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado Clientes");
			return "form";
		}
		
		databaseService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@GetMapping("/eliminar/{email}")
	public String eliinar(@PathVariable(value = "email") String email) {
		if (!email.isEmpty()) {
			databaseService.eliminar(email);
		}
		return "redirect:/listar";
	}

}
