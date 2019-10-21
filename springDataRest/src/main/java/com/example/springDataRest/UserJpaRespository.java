package com.example.springDataRest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserJpaRespository extends CrudRepository <Users,Long> {
  
	Users findByName(String Name);
}
