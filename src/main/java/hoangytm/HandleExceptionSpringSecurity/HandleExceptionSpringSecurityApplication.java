package hoangytm.HandleExceptionSpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication

public class HandleExceptionSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandleExceptionSpringSecurityApplication.class, args);
	}

}
