package es.ubu.lsi.TallerJPA.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.ubu.lsi.TallerJPA.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>{

}
