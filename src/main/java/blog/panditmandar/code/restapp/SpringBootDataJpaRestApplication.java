package blog.panditmandar.code.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author MandarPandit
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages ={"blog.panditmandar.code.restapp.controller"})
public class SpringBootDataJpaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaRestApplication.class, args);
	}
}
