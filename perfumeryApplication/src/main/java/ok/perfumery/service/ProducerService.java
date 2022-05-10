package ok.perfumery.service;

import java.util.List;

import ok.perfumery.entity.Producer;

public interface ProducerService {

	void createNewProducer(String name, String countryOrigin);
	void updateProducer(Producer producer);
	Producer getProducerById(int id);
	Producer getProducerByName(String name);
	void deleteProducerById(int id);
	
	List<Producer> getAllProducers();
	
	boolean producerExistsByName(String name);
	void createNewProducer(Producer producer);
	
	
	
}
