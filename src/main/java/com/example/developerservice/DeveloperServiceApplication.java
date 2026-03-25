package com.example.developerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.developerservice.config.DeveloperConfig;

@SpringBootApplication
@EnableConfigurationProperties(DeveloperConfig.class)
public class DeveloperServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeveloperServiceApplication.class, args);
    }
}
