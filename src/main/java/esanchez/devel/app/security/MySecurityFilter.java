package esanchez.devel.app.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author Enrique Sanchez Jordan
 * 
 * Custom Filter to be used before the authentication.
 * It must be configured in the MySecurityConfig class
 *
 */

/*
 * Implements default Filter interface
 */
public class MySecurityFilter implements Filter {

/*
 * Extends GenericFilterBean is used when you want to initialize 
 * a parameter in the filter and this class will generate the getters
 * and setters automatically injecting the parameter
 */
//public class MySecurityFilter extends GenericFilterBean {
	
/*
 * Extends OncePerRequestFilter for execute the filter only
 * one time per request. The doFilter method is different in this case.
 */
//public class MySecurityFilter extends OncePerRequestFilter {
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
