package ca.softcraft.swoop.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The SwoopSample bootstrap system.
 */
@SpringBootApplication
public class Application {

	/**
	 * The application entry point.
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {
		/**
		 * I like how Spring Boot makes bootstrapping so clean.
		 */
		SpringApplication.run(Application.class, args);
	}

}
