package esanchez.devel.app.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author Enrique Sanchez Jordan
 * 
 * Custom Filter to be used before the authentication.
 * It must be configured in the MySecurityConfig class
 *
 */
public class MySecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*
		 * code executed in the request of the filter
		 */
		System.out.println("Before");
		
		chain.doFilter(request, response);
		
		/*
		 * code execute in the response of the filter
		 */
		System.out.println("After");
	}

}
