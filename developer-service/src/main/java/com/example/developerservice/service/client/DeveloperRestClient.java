package com.example.developerservice.service.client;

import com.example.developerservice.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// Способ 2: RestTemplate с @LoadBalanced — Spring Cloud сам балансирует по имени сервиса
@Component
public class DeveloperRestClient {

    @Autowired
    RestTemplate restTemplate;

    public Project getProject(Long projectId) {
        ResponseEntity<Project> exchange = restTemplate.exchange(
                "http://projectservice/api/projects/{projectId}",
                HttpMethod.GET,
                null,
                Project.class,
                projectId);
        return exchange.getBody();
    }
}
