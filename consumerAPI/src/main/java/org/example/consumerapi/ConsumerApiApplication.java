package org.example.consumerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(
	// ProvinceRepository repository
	// ) {
	// 	return args -> {
	// 		var province = Province.builder()
	// 		.code("123")
	// 		.name("Ha noi")
	// 		.provinceType("Thanh Pho")
	// 		.nameEnglish(null)
	// 		.build();


	// 		repository.save(province);
    //     };
	// }
}
