package pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"pizzeria"})
public class PizzeriaApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PizzeriaApplication.class, args);
    }
}