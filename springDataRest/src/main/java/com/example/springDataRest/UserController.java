package com.example.springDataRest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
   	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required= true)
	private UserJpaRespository userJpaRespository;
	
	@GetMapping(value="/all",produces="application/hal+json")
	public Iterable<Users> findAll(){
		try {
			
			logger.info("SUCCESS",HttpStatus.OK);
			return userJpaRespository.findAll();
		
		}catch(Exception e) {
			logger.error("NOT FOUND",HttpStatus.NOT_FOUND);
		}
      return null;

	}
	@GetMapping(value="/{name}",produces="application/hal+json")
	public  Users findByName(@PathVariable final String name) {
		try {
			
			logger.info("SUCCESS",HttpStatus.OK);
			return userJpaRespository.findByName(name);
			
			}catch(Exception e) {
				logger.error("NOT A VALID NAME",HttpStatus.NOT_FOUND);
			}
	return null;
	}
	
	@PostMapping(value="/load",produces = MediaType.APPLICATION_JSON_VALUE)
	public 	Users load(@RequestBody Users users) {
		try {
			
			userJpaRespository.save(users);
			logger.info("SUCCESS",HttpStatus.CREATED);
			return userJpaRespository.findByName(users.getName());
			
			}catch(Exception e) {
				logger.error("USER IS NOT CREATED",HttpStatus.NO_CONTENT);
			}
		
			return null;
		
	}

}
