package esanchez.devel.app.security;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Enrique Sanchez Jordan
 * 
 * Custom Authentication Provider for manage the user authentication
 * using username and password. 
 * If This Authentication Provider exists and it's configured in the "configure" method of the MySecurityConfig class
 * then is not needed to put the authentication details with the custom UserDetails and PasswordEncription, because 
 * the authentication will be done in this class
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	/*
	 * The method that we use for check the authentication of the user, checking
	 * the username and password provided in the Authentication object
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if ("tom".equals(username) && "qwertyui".equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
		} else {
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	/*
	 * The method for check if this AuthenticationProvider supports one specific Authentication method. 
	 * In this case the UsernamePasswordAuthenticationToken.class, that is the one used in the 
	 * "authenticate" method
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
