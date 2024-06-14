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

import es.ubu.lsi.TallerJPA.Model.Product;
import es.ubu.lsi.TallerJPA.Services.databaseService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductsController {
	
	
	@Autowired
	private databaseService databaseService;
	
	@GetMapping("/listarProductos")
	public String listar(Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		model.addAttribute("productos", databaseService.findAllProducts());
		model.addAttribute("titulo", "Listado de productos");
		return "listar";
	}
	
	@GetMapping("/formProducto")
	public String crear(Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		Product producto = new Product();
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Añadir producto");
		return "form";
	}
	
	
	@GetMapping("/formProducto/{id}")
	public String editar(@PathVariable(value = "identifier") String id, Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		Product producto = null;
		if(!id.isEmpty()) {
			producto = databaseService.findOneProducto(id);
		}else {
			return "redirect:/listarProductos";
		}
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Listado productos");
		model.addAttribute("edit", true);
		
		return "form";
	}
	
	@PostMapping("/formProducto")
	public String guardar(@Validated Product producto, BindingResult result, Model model, SessionStatus status, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		System.out.println(producto.toString());
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			model.addAttribute("titulo", "Listado productos");
			return "form";
		}
		databaseService.save(producto);
		status.setComplete();
		return "redirect:/listarProductos";
	}
	
	@GetMapping("/eliminarProducto/{id}")
	public String eliminar(@PathVariable(value = "identifier") String id, Model model, HttpSession session) {
		
		if (!(boolean) session.getAttribute("logued")) {
			model.addAttribute("info", "Tienes que iniciar sesión.");
			return "home";
		}
		
		if (!id.isEmpty()) {
			databaseService.eliminarProducto(id);
		}
		return "redirect:/listarProductos";
	}

}
