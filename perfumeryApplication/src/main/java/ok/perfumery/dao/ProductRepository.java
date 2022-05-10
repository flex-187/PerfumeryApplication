package ok.perfumery.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ok.perfumery.entity.Product;


public interface ProductRepository extends CrudRepository<Product,Integer> {
	
	List<Product> findByName(String name);
	

}
