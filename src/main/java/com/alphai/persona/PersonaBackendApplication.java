package com.alphai.persona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@ComponentScan("com.alphai")
@EnableJpaRepositories("com.alphai")
@EnableAutoConfiguration*/
public class PersonaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaBackendApplication.class, args);
	}
}
