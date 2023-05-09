package saramdle.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
//@SpringBootApplication
//public class BlogApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BlogApplication.class, args);
//	}
//
//}

@EnableJpaAuditing
@SpringBootApplication
public class BlogApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:application-oauth.yml,"
			+ "classpath:application-db.yml,"
			+ "classpath:aws.yml";
	public static void main(String[] args) {
		new SpringApplicationBuilder(BlogApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
