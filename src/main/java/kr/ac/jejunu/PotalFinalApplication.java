package kr.ac.jejunu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PotalFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotalFinalApplication.class, args);
    }

}