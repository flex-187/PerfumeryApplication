package ok.perfumery.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ok.perfumery.entity.Product;
import ok.perfumery.service.ProducerService;
import ok.perfumery.service.ProductService;

@Controller
@RequestMapping("/")
public class OverviewController {
	
	private final ProducerService producerService;
	private final ProductService productService;
	
	public OverviewController (ProducerService producerService, ProductService productService) {
		this.producerService = producerService;
		this.productService = productService;
	}
	
	@ModelAttribute(name = "product")
	public List<Product> products() {
		return productService.getAllProducts();
	}
	
	@GetMapping
	public String displayOverview(Model model) {
		model.addAttribute("producers",producerService.getAllProducers());
		return  "overview";
	}

}
