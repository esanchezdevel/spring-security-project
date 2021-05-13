package esanchez.devel.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * This configure method allow as to configure the Authentication Manager.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * In this case we will use an authentication with registered in memory
		 */
		
		/*
		 * create a passwordEncoder for encrypt the password stored in memory and pass 
		 * it as the encoder to the userDetailsService
		 */
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		
		/*
		 * create the user in memory
		 */
		UserDetails user = User.withUsername("tom").password(passwordEncoder.encode("qwertyui")).authorities("read").build();
		userDetailsService.createUser(user);
		
		/*
		 * define the authentication user details service with the encoder
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	/*
	 * This configure method allow us to set which kind of authentication we 
	 * will use. It could be form based authentication, http basic authentication, 
	 * etc...
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * In this case we use an http basic authenticated and we 
		 * permit to access to the resources only to the authenticated users
		 * because we use the method authenticated().
		 * If we want to permit access to all the resources to all requests, we 
		 * must use permitAll() method instead
		 */
		http.httpBasic();
		http.authorizeRequests().anyRequest().authenticated();
	}

	
}
