package masterSpringMVC7;

import masterSpringMVC7.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSpringMvc7Application {

	public static void main(String[] args) {
		SpringApplication.run(MasterSpringMvc7Application.class, args);
	}
}
