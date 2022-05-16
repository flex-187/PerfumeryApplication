package ok.perfumery.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

	Product product;
	
	@BeforeEach
	void setUp() {
		Producer producer = new Producer();
		producer.setName("testProducer");
		producer.setCountryOrigin("country");
		
		product = new Product();
		product.setName("testProduct");
		product.setProducer(producer);
		product.setMaxVolume(100);
		product.setCurrentVolume(100);
		product.setBatchCode("XXXX");
		product.setType(Product.Type.PRODUCT);
	}
	
	
	@Test
	void shouldDecant() {
		
		Decant decant = product.decant(10);
		assertEquals(product.getDecants().get(0),decant);
	}
	
	@Test
	void shouldFailDecantIfExceedsCurrentVolume() {
		Decant decant = product.decant(product.getCurrentVolume()+1);
		assertTrue(decant==null,"Decant volume can't exceed product's current volume");
	}

}
