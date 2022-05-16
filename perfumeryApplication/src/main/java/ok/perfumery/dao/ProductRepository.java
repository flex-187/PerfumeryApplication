package ok.perfumery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ok.perfumery.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	List<Product> findByName(String name);
	

}
