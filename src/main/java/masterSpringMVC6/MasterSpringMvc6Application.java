package masterSpringMVC6;

import masterSpringMVC6.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSpringMvc6Application {

	public static void main(String[] args) {
		SpringApplication.run(MasterSpringMvc6Application.class, args);
	}
}
