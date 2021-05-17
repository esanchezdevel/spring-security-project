package esanchez.devel.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import esanchez.devel.app.security.MyAuthenticationProvider;

/**
 * 
 * @author Enrique Sanchez Jordan
 * 
 * This Custom Security configuration class is where we can 
 * configure how to authenticate and authorize to the users
 * for access to our app.
 *
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * Pre-charge the passwordEncoder using the Bean that we have defined
	 * at the bottom of the class
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
	
	/*
	 * This configure method allow as to configure the Authentication Manager.
	 * 
	 * Configuration for use our custom Authentication Provider "MyAuthenticationProvider"
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider);
	}
	
//	/*
//	 * This configure method allow as to configure the Authentication Manager.
//	 * 
//	 * Configuration for use UsersDetails in Memory with a custom PasswordEncoder
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		/*
//		 * In this case we will use an authentication with registered in memory
//		 */
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//		
//		/*
//		 * create the user in memory
//		 */
//		UserDetails user = User.withUsername("tom").password(passwordEncoder.encode("qwertyui")).authorities("read").build();
//		userDetailsService.createUser(user);
//		
//		/*
//		 * define the authentication user details service with the encoder
//		 */
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//	}

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
		//http.httpBasic();
		
		/*
		 * In this case we use a form base authentication. We will see an authentication
		 * form in the browser for enter our user credentials and access to our endpoints
		 */
		http.formLogin();
		
		/*
		 * authorize any request
		 */
		//http.authorizeRequests().anyRequest().authenticated();
		
		/*
		 * authorize only endpoint /hello
		 */
		//http.authorizeRequests().antMatchers("/hello").authenticated();
		
		/*
		 * adding the anyRequest().denyAll() we make that only authenticated users can access to hello
		 * and the other endpoints are not accesible for any user
		 */
		http.authorizeRequests().antMatchers("/hello").authenticated().anyRequest().denyAll();
	}

	/*
	 * Create a Bean with the passwordEncoder for be used and have it 
	 * pre-charged with the @Autowired
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
