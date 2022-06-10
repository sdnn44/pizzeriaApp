package pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pizzeria.printer.Printer;

@SpringBootApplication
public class PizzeriaApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PizzeriaApplication.class, args);
        Printer printer = run.getBean(Printer.class);
        printer.process();
    }
}
