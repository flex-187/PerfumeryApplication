package ok.perfumery.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Product {

	public enum Type {
		PRODUCT_SEALED("Sealed product"), PRODUCT("Unsealed product"), TESTER("Tester"), DECANT("Decant"),
		SAMPLE("Sample");

		private final String displayValue;

		private Type(String displayValue) {
			this.displayValue = displayValue;
		}

		public String getDisplayValue() {
			return this.displayValue;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Type type;

	@NotNull(message = "Name can't be null")
	@Size(min = 3, message = "Name has to be at least 3 characters long")
	private String name;

	@JsonBackReference
	@NotNull(message = "Producer can't be null")
	@ManyToOne
	private Producer producer;

	private String batchCode;

	@NotNull(message = "Max volume can't be null")
	@PositiveOrZero(message = "Max volume has to be greater than 0")
	private Integer maxVolume;

	@NotNull(message = "Current volume can't be null")
	private Integer currentVolume = maxVolume;

	private Date placedAt = new Date();

	@OneToMany
	private List<Decant> decants = new ArrayList<>();

	public Decant decant(int volume) {
		if (volume > this.currentVolume) return null;
		Decant newDecant = new Decant();
		newDecant.setParentProduct(this);
		newDecant.setCurrentVolume(volume);
		this.decants.add(newDecant);
		return newDecant;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return this.type;
	}

	public String toString() {

		return String.format("id: %s name: %s, producer: %s, batch: %s, maxVol: %s, currentVol: %s, date: %s",
				this.getId(), this.getName(), this.getProducer(), this.getBatchCode(), this.getMaxVolume(),
				this.getCurrentVolume(), this.getPlacedAt());
	}

}
