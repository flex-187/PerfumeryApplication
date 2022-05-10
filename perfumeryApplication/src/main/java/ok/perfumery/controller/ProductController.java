package ok.perfumery.controller;



import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import ok.perfumery.entity.Product;
import ok.perfumery.service.ProducerService;
import ok.perfumery.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	private ProducerService producerService;
	
	
	public ProductController(ProductService productService, ProducerService producerService) {
		this.productService = productService;
		this.producerService = producerService;
	}

	
	@GetMapping("/")
	public String displayOverview(Model model) {
		model.addAttribute("producers",producerService.getAllProducers());
		return  "overview";
	}
	
	@GetMapping("/{id}")
	public String showProductDetails(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute(product);		
		return "productDetails";
	}
	
	@GetMapping("/{id}/edit")
	public String showFormForEditProduct(@PathVariable int id,	Model model) {
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("producers", producerService.getAllProducers());
		return "productForm";
	}
	
	@GetMapping("/new") 
	public String showProductForm(Model model){
		model.addAttribute("product", new Product());
		model.addAttribute("producers", producerService.getAllProducers());
		return "productForm";
	}
	
	@PostMapping("/new")
	public String saveProduct(@ModelAttribute("product") @Valid Product product, Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			model.addAttribute("producers", producerService.getAllProducers());
			return "productForm";
		}
		
		if(product.getId() != 0) {
			productService.updateProduct(product);
		} else {
			productService.createNewProduct(product);
		}
		
		return "redirect:/products/"+ product.getId();
	}

}
