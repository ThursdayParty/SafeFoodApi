package me.thursdayParty.safeFoodApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SafeFoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeFoodApiApplication.class, args);
	}

}

@RestController
class hello {
	@GetMapping("/hello")
	public String hello() {
		return "Hi~";
	}
}