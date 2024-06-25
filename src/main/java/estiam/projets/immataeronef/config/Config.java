package estiam.projets.immataeronef.config;

import estiam.projets.immataeronef.data.ImmatCSVReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
    ImmatCSVReader getImmatCSVReader() {
		return new ImmatCSVReader();
	}
	
}
