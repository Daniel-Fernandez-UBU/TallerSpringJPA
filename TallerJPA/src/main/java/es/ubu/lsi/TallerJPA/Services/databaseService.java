package es.ubu.lsi.TallerJPA.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ubu.lsi.TallerJPA.Model.Product;
import es.ubu.lsi.TallerJPA.Model.User;
import es.ubu.lsi.TallerJPA.Repository.ProductRepository;
import es.ubu.lsi.TallerJPA.Repository.UserRepository;

/**
 * Clase databaseService.
 * 
 * Esta clase contendrá todos los métodos necesarios para las comprobaciones de las tablas de la base de datos.
 * 
 * @author Daniel Fernández Barrientos
 * @author Ismael Manazanera López
 * 
 * @version 1.0
 */
@Service
public class databaseService {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;
	
	
	/**
	 * Method checkRegistered.
	 * 
	 * @param email the email
	 * @param password the password
	 * @return true o false
	 */
	public boolean checkRegistered(String email, String password) {
		Optional<User> userOpt;
		User user = new User();
		
		if (userRepository.existsById(email)){
			userOpt = userRepository.findById(email);
			if (userOpt.isPresent()) {
				user = userOpt.get();
				
				return user.getPassword().equals(password);
			}
			
		}
		return false;		
		
	} 
	
		
	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	/**
	 * Find all products.
	 *
	 * @return the list
	 */
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}


	/**
	 * Save User.
	 *
	 * @param usuario the usuario
	 */
	public void save(User usuario) {
		userRepository.save(usuario);
	}
	
	/**
	 * Save Product.
	 *
	 * @param product the product
	 */
	public void save(Product product) {
		productRepository.save(product);
	}


	/**
	 * Find one User.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findOne(String email) {
		return userRepository.findById(email).orElse(null);
	}
	
	/**
	 * Find one Product.
	 *
	 * @param id the id
	 * @return the product
	 */
	public Product findOneProducto(String id) {
		return productRepository.findById(id).orElse(null);
	}

	/**
	 * Delete User.
	 *
	 * @param email the email
	 */
	public void eliminar(String email) {
		userRepository.deleteById(email);
	}
	
	/**
	 * Delete Product.
	 *
	 * @param id the id
	 */
	public void eliminarProducto(String id) {
		productRepository.deleteById(id);
	}
	
	
	


}
