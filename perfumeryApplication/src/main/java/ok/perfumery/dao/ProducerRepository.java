package ok.perfumery.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ok.perfumery.entity.Producer;

public interface ProducerRepository extends CrudRepository<Producer,Integer> {
	
	 List<Producer> findByName(String name);

}
