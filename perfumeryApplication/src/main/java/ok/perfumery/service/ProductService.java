package ok.perfumery.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ok.perfumery.entity.Product;
import ok.perfumery.entity.Product.Type;

public interface ProductService {

	void createNewProduct(String name, int producerId, String batchCode, int maxVolume, int currentVolume, Type type);

	void createNewProduct(Product product);

	void update(Product product);

	void deleteById(int id);

	void delete(Product product);

	Product findById(int id);

	Product findByName(String name);

	List<Product> findAll();

	List<Product> findByProducerId(int id);

	Page<Product> findAll(Pageable page);

}
