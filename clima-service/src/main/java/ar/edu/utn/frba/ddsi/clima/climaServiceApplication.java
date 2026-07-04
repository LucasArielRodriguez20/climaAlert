package ar.edu.utn.frba.ddsi.clima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class climaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(climaServiceApplication.class, args);
    }
}
