package ok.perfumery.dao;

import org.springframework.data.repository.CrudRepository;

import ok.perfumery.entity.user.User;

public interface UserRepository  extends CrudRepository<User,Long>{
	
	User findByUsername(String username);

}
