package com.example.demo.jwt.db;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

//import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jwt.domain.User;

import lombok.extern.slf4j.Slf4j;

//public interface UserRepository extends JpaRepository<User,Integer> {
//    User findByUserName(String username);
//}

@Slf4j
@Repository
public class UserRepository {

	public List<User> getAllUsers() {
		User user1 = new User(1,"admin","admin","");
		User user2 = new User(2,"test","test","");
		User user3 = new User(3,"poc","poc","");
		return Arrays.asList(user1,user2,user3);
	}
	
	public User findByUserName(String name) {
		User output = getAllUsers().stream()
				.filter(e -> e.getUserName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
		log.info("*** Repository output: "+output);
		return output;
	}

}