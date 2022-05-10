package ok.perfumery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ok.perfumery.dao.ProducerRepository;
import ok.perfumery.dao.ProductRepository;
import ok.perfumery.entity.Producer;
import ok.perfumery.entity.Product;
import ok.perfumery.entity.Product.Type;
import ok.perfumery.service.ProducerService;
import ok.perfumery.service.ProductService;

@SpringBootApplication
public class PerfumeryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfumeryApplication.class, args);	
	}
	// HUJ 88
	  @Bean
	  public CommandLineRunner dataLoader(ProducerRepository repo, ProductRepository repoProduct, ProducerService producerService, ProductService productService) {
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	        
	    	  repo.save(new Producer("Acqua Di Parma","Italy"));
	    	  repo.save(new Producer("Caron","France"));

	    	  Producer prod = producerService.getProducerByName("Acqua Di Parma");
	    	  Producer prod2 = producerService.getProducerByName("Caron");

	    	  Product product1 = new Product();
	    	  product1.setName("Colonia Essenza");
	    	  product1.setCurrentVolume(100);
	    	  product1.setMaxVolume(100);
	    	  product1.setProducer(prod);
	    	  product1.setType(Type.PRODUCT_SEALED);

	    	  
	    	  Product product2 = new Product();
	    	  product2.setName("Pour Un Homme");
	    	  product2.setCurrentVolume(60);
	    	  product2.setMaxVolume(100);
	    	  product2.setProducer(prod2);
	    	  product2.setType(Type.TESTER);
	    	  
	    	  
	    	  
	    	  repoProduct.save(product1);
	    	  repoProduct.save(product2);
	    	  
	    	  Product peja = productService.getProductByName("Pour Un Homme");
	    	  System.out.println(peja);
	        
	        
	      }
	    };
	  }

}
