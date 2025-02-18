package goya.daw2.validar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumoApiRestApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumoApiRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumoApiRestApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//Quote quote // del tutorial de consumo de api de quoters
			//List<Pet> pets // para la api de pets
			Joke joke // para la api de jokes
			= restTemplate.getForObject(
					//"http://localhost:8080/api/random", Quote.class); // quoters
					//"http://petstore-demo-endpoint.execute-api.com/petstore/pets", List.class); // Nos devuelve la lista de pets
					"http://official-joke-api.appspot.com/random_joke", Joke.class); // api joke
			//log.info(quote.toString());
			//log.info(pets.toString());
			log.info(joke.toString());
		};
	}

}
