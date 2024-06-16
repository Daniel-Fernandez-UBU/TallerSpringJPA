package es.ubu.lsi.TallerJPA.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.ubu.lsi.TallerJPA.Model.Product;


/**
 * Interfaz ProductRepository.
 * 
 * Interfaz para manejar los productos, y su CRUD con la base de datos.
 * 
 * @author Daniel Fernández Barrientos
 * @author Ismael Manzanera López
 * 
 * @version 1.0
 * 
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, String>{

}
