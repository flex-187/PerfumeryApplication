package ok.perfumery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ok.perfumery.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	List<Product> findByName(String name);
	

}
