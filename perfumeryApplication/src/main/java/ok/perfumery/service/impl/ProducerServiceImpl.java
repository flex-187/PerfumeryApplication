package ok.perfumery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import ok.perfumery.dao.ProducerRepository;
import ok.perfumery.entity.Producer;
import ok.perfumery.exception.ProducerNotFoundException;
import ok.perfumery.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

	private ProducerRepository producerRepo;
	
	public ProducerServiceImpl(ProducerRepository producerRepo) {
		this.producerRepo = producerRepo;
	}
	
	@Override
	public void createNewProducer(String name, String countryOrigin) {
		if(existsByName(name)) throw new IllegalArgumentException("Producer already exists");
		
		Producer newProducer = new Producer();
		newProducer.setName(name);
		newProducer.setCountryOrigin(countryOrigin);
		producerRepo.save(newProducer);
	}

	@Override
	public void update(Producer producer) {
		producerRepo.save(producer);
		
	}

	@Override
	public Producer findById(int id) {
		return producerRepo.findById(id).orElseThrow(ProducerNotFoundException::new);
	}

	@Override
	public void deleteById(int id) {
		producerRepo.deleteById(id);
		
	}

	@Override
	public List<Producer> findAll() {
		Iterable<Producer> allProducers = producerRepo.findAll();
		List<Producer> result = Lists.newArrayList(allProducers);
		return result;
	}

	@Override
	public boolean existsByName(String name) {
		return !producerRepo.findByName(name).isEmpty();
	}

	@Override
	public Producer findByName(String name) {
		return producerRepo.findByName(name).get(0);
	}

	@Override
	public void createNewProducer(Producer producer) {
		producerRepo.save(producer);
		
	}
	
	

}
