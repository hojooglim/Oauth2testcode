package com.example.myblogagain;

import com.example.myblogagain.config.properties.AppProperties;
import com.example.myblogagain.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class MyblogagainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogagainApplication.class, args);
    }

}
