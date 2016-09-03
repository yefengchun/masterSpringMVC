package masterSpringMVC;

import masterSpringMVC.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;


@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterSpringMvcApplication.class, args);
	}

	/*@Bean
	public Filter etagFilter() {
		return new ShallowEtagHeaderFilter();
	}*/
}
