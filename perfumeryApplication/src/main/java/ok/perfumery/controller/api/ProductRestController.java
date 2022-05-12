package ok.perfumery.controller.api;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ok.perfumery.entity.Product;
import ok.perfumery.service.ProductService;

@RestController
@RequestMapping(path="/api/products", produces="application/json")
@CrossOrigin(origins="http://orlowski:8080")
public class ProductRestController {

	private ProductService productService;
	
	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(params="recent")
	public Iterable<Product> recentProducts() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("placedAt").descending());
		return productService.findAll(page).getContent();
	}
	
	@GetMapping("/{id}")
	public Optional<Product> productById(@PathVariable("id") int id) {
		return Optional.ofNullable(productService.findById(id));
	}
}
