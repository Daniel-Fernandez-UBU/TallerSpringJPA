package es.ubu.lsi.TallerJPA.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import es.ubu.lsi.TallerJPA.Model.Product;
import es.ubu.lsi.TallerJPA.Model.User;
import es.ubu.lsi.TallerJPA.Repository.*;
import jakarta.annotation.PostConstruct;


/**
 * Clase LoadInitialConfig.
 * 
 * Si el valor de "load.initial.data" es true, carga varios usuarios y productos.
 * 
 * @author Daniel Fernández Barrientos
 * @author Ismael Manazanera López
 * 
 * @version 1.0
 */
@Configuration
@PropertySource("classpath:custom.properties")
public class LoadInitialConfig {
	
	/**  The loadInitialData value. */
    @Value("${load.initial.data}")
    private boolean loadInitialData;

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;
    
    /** The product repository. */
    @Autowired
    private ProductRepository productRepository;
    
    
    /**
     * Método init.
     */
    @PostConstruct
    public void init() {
        if (loadInitialData) {
            // Cargar datos iniciales
            loadInitialUsers();
            loadInitialProducts();
        }
    }
    
    /**
     * Método loadInitialUsers.
     * 
     * Carga varios usuarios en la aplicación.
     */
    public void loadInitialUsers() {
        User user1 = new User("dfb1001@alu.ubu.es", "pass", "Daniel", "Fernández");
        userRepository.save(user1);
        User user2 = new User("iml1012@alu.ubu.es", "pass", "Ismael", "Manzanera");
        userRepository.save(user2);
        User user3 = new User("jmafernandez@ubu.es", "pass", "Jose Manuel", "Aroca");
        userRepository.save(user3);
        
    }

    /**
     * Método loadInitialProducts.
     * 
     * Carga varios productos en el supermercado.
     */
    public void loadInitialProducts() {
        Product product1 = new Product("C0042DC", "Clesa", "Leche desnatada con calcio", 30, "2024-09-28", 1.22F);
        productRepository.save(product1);
        Product product2 = new Product("P0272SF", "Pascual", "Leche semidesnatada con fibra", 30, "2024-12-14", 1.08F);
        productRepository.save(product2);
        Product product3 = new Product("H0028SL", "Hacendado", "Leche entera sin lactosa", 30, "2024-10-05", 1.13F);
        productRepository.save(product3);
    }

}
