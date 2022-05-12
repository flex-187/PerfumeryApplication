package ok.perfumery.service.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import ok.perfumery.dao.ProductRepository;
import ok.perfumery.entity.Producer;
import ok.perfumery.entity.Product;
import ok.perfumery.entity.Product.Type;
import ok.perfumery.exception.ProductNotFoundException;
import ok.perfumery.service.ProducerService;
import ok.perfumery.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepo;
	private ProducerService producerService;
	
	public ProductServiceImpl(ProductRepository productRepo, ProducerService producerService) {
		this.productRepo = productRepo;
		this.producerService = producerService;
	}

	
	@Override
	public void createNewProduct(String name, int producerid, String batchCode, int maxVolume, int currentVolume, Type type) {
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setProducer(producerService.findById(producerid));
		newProduct.setBatchCode(batchCode);
		newProduct.setMaxVolume(maxVolume);
		newProduct.setCurrentVolume(currentVolume);
		newProduct.setType(type);
		productRepo.save(newProduct);
		
	}

	@Override
	public void update(Product product) {
		productRepo.save(product);
		
	}

	@Override
	public void deleteById(int id) {
		productRepo.deleteById(id);
		
	}

	@Override
	public void delete(Product product) {
		productRepo.delete(product);
		
	}

	@Override
	public Product findById(int id) {
		return productRepo.findById(id)
				.orElseThrow(ProductNotFoundException::new);
	}

	@Override
	public List<Product> findAll() {
		Iterable<Product> allProducts = productRepo.findAll();
		List<Product> result = Lists.newArrayList(allProducts);
		return result;
	}

	@Override
	public List<Product> findByProducerId(int id) {
		Producer producer = producerService.findById(id);
		return producer.getProducts();
	}


	@Override
	public Product findByName(String name) {
		return productRepo.findByName(name).get(0);
	}


	@Override
	public void createNewProduct(Product product) {
		productRepo.save(product);
		
	}


	@Override
	public Page<Product> findAll(Pageable page) {
		return productRepo.findAll(page);
	}



}
