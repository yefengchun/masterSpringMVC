package masterSpringMVC5;

import masterSpringMVC5.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSpringMvc5Application {

	public static void main(String[] args) {
		SpringApplication.run(MasterSpringMvc5Application.class, args);
	}
}
