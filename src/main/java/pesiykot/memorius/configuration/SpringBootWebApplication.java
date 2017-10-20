package pesiykot.memorius.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pesiykot.memorius.configuration", "pesiykot.memorius.web", "pesiykot.memorius.error",
"pesiykot.memorius.service", "pesiykot.memorius.security"})
public class SpringBootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
