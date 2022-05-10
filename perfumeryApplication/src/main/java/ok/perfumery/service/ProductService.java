package ok.perfumery.service;

import java.util.List;

import ok.perfumery.entity.Product;
import ok.perfumery.entity.Product.Type;

public interface ProductService {
	
	void createNewProduct(String name, int producerId, String batchCode, int maxVolume, int currentVolume, Type type);
	
	void updateProduct(Product product);
	
	void deleteProductById(int id);
	
	void deleteProduct(Product product);
	
	Product getProductById(int id);
	
	Product getProductByName(String name);
	
	List<Product> getAllProducts();
	
	List<Product> getProductByProducerId(int id);

	void createNewProduct(Product product);
	
	
	
	
	

}
