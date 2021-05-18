package esanchez.devel.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityProjectApplicationTests {

	@Test
	void testPasswordEncoders() {
		System.out.println("Password: " + new BCryptPasswordEncoder().encode("password"));
		System.out.println("Password: " + new Pbkdf2PasswordEncoder().encode("password"));
		System.out.println("Password: " + new SCryptPasswordEncoder().encode("password"));
		
		/*
		 * DelegatingPasswordEncoder is used for have multiple encoders, and 
		 * use each one when we want using one single object
		 */
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		System.out.println("Delegating PasswordEncoder: " + new DelegatingPasswordEncoder("bcrypt", encoders).encode("password"));
	}

}
