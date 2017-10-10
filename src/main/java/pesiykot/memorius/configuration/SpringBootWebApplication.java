package pesiykot.memorius.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pesiykot.memorius.controller.WelcomeController;

@SpringBootApplication
@ComponentScan(basePackageClasses = WelcomeController.class)
public class SpringBootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
