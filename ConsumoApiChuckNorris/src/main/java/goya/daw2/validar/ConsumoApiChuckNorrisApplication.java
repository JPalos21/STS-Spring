package goya.daw2.validar;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumoApiChuckNorrisApplication {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConsumoApiChuckNorrisApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ConsumoApiChuckNorrisApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Chiste chiste = restTemplate.getForObject(
					"https://api.chucknorris.io/jokes/random", Chiste.class);
			log.info(chiste.toString());
		};
	}

}
