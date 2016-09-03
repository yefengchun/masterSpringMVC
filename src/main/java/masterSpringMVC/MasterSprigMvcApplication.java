package masterSpringMVC;

import masterSpringMVC.config.PictureUploadProperties;
import masterSpringMVC.profile.PictureUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSprigMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterSprigMvcApplication.class, args);
	}
}
