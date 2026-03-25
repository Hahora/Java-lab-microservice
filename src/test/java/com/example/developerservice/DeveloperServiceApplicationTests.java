package com.example.developerservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.cloud.config.enabled=false",
        "developer.default.specialization=Backend",
        "developer.default.minRating=1.0",
        "developer.default.maxProjects=5"
})
class DeveloperServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
