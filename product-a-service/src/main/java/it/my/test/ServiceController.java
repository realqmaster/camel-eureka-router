package it.my.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ServiceController {
	
	@PostMapping("")
	public String callService(@RequestBody String body) {
		return "I am service A, and I received [" + body + "]";
	}

}
