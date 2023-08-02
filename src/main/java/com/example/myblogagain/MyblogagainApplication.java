package com.example.myblogagain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyblogagainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyblogagainApplication.class, args);
  }

}
