package org.cibertec.edu.pe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.cibertec.edu.pe.dao.UserRepository;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginApiApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@BeforeAll
	public static void init(@Autowired UserRepository userRepository, @Autowired UserService userService) {
		User user = new User();
		user.setUserName("test");
		user.setUserFirstName("firstName");
		user.setUserLastName("lastName");
		user.setUserPassword(userService.getEncodedPassword("test"));
		userRepository.save(user);
	}
	
	@Test
	public void createUserTest() {
		User user = new User();
		user.setUserName("test");
		user.setUserFirstName("firstName");
		user.setUserLastName("lastName");
		user.setUserPassword(userService.getEncodedPassword("test"));
		User newUser = userRepository.save(user);
		assertTrue(newUser.getUserPassword().equalsIgnoreCase(user.getUserPassword()));
	}
	
	@Test
	public void findUserTest() {
		Optional<User> dbUser = userRepository.findById("test");
		assertNotNull(dbUser);
	}
	
	@Test
	public void updateUserTest() {
		User dbUser = userRepository.findById("test").get();		
		dbUser.setUserPassword(userService.getEncodedPassword("newPassword"));
		User newUser = userRepository.save(dbUser);
		assertTrue(newUser.getUserPassword().equalsIgnoreCase(dbUser.getUserPassword()));
	}
	
	@Test
	public void deleteUserTest(@Autowired UserRepository userRepository) {
		userRepository.deleteById("test");
		Optional<User> dbUser = userRepository.findById("test");
		assertEquals(dbUser, Optional.empty());
	}
		
	@AfterAll
	public static void clean(@Autowired UserRepository userRepository) {
		Optional<User> dbUser = userRepository.findById("test");			
		if(dbUser.isPresent()) userRepository.deleteById("test");
	}

}
