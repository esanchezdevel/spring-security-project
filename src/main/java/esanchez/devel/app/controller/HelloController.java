package esanchez.devel.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World with Spring Security!";
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "Get Lost!";
	}
}
