package ok.perfumery.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
public class Producer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull(message = "Name can't be null")
	@Size(min=3, message="Name has to be at least 3 characters long")
	private String name;
	
	
	private String countryOrigin;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "producer")
	private List<Product> products = new ArrayList<>();
	

	
	public Producer(String name, String origin) {
		this.name = name;
		this.countryOrigin = origin;
		this.products = new ArrayList<>();
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public String toString( ) {
		return "Producer " + this.name + ", country of origin: " + this.countryOrigin;
	}
}
