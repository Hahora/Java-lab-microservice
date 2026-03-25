package com.example.developerservice.service.client;

import com.example.developerservice.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Способ 1: Spring Discovery Client — вручную получаем адрес из Eureka
@Component
public class DeveloperDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Project getProject(Long projectId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("projectservice");
        if (instances.isEmpty()) return null;

        String serviceUri = String.format("%s/api/projects/%s",
                instances.get(0).getUri().toString(), projectId);

        ResponseEntity<Project> exchange = restTemplate.exchange(
                serviceUri, HttpMethod.GET, null, Project.class);
        return exchange.getBody();
    }
}
