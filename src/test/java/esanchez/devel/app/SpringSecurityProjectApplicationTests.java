package esanchez.devel.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityProjectApplicationTests {

	@Test
	void testPasswordEncoders() {
		System.out.println("Password: " + new BCryptPasswordEncoder().encode("password"));
		System.out.println("Password: " + new Pbkdf2PasswordEncoder().encode("password"));
		System.out.println("Password: " + new SCryptPasswordEncoder().encode("password"));
	}

}
