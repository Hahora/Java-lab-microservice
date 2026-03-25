package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import com.example.developerservice.model.Project;
import com.example.developerservice.service.client.DeveloperDiscoveryClient;
import com.example.developerservice.service.client.DeveloperFeignClient;
import com.example.developerservice.service.client.DeveloperRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeveloperService {

    private final Map<Long, Developer> developers = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private DeveloperDiscoveryClient developerDiscoveryClient;

    @Autowired
    private DeveloperRestClient developerRestClient;

    @Autowired
    private DeveloperFeignClient developerFeignClient;

    public Developer getDeveloperById(Long id) {
        return developers.get(id);
    }

    public Developer createDeveloper(Developer developer) {
        long newId = counter.incrementAndGet();
        developer.setId(newId);
        developers.put(developer.getId(), developer);
        return developer;
    }

    public Developer updateDeveloper(Long id, Developer updatedDeveloper) {
        updatedDeveloper.setId(id);
        developers.put(id, updatedDeveloper);
        return updatedDeveloper;
    }

    public String deleteDeveloper(Long id) {
        developers.remove(id);
        return String.format("Разработчик с id %d удалён", id);
    }

    // Получить разработчика с информацией о проекте через указанный клиент
    public Developer getDeveloperWithProject(Long id, String clientType) {
        Developer developer = developers.get(id);
        if (developer == null) return null;

        if (developer.getProjectId() != null) {
            Project project = retrieveProjectInfo(developer.getProjectId(), clientType);
            if (project != null) {
                developer.setComment(String.format(
                        "Проект: %s, Бюджет: %.2f (клиент: %s)",
                        project.getTitle(), project.getBudget(), clientType));
            }
        }
        return developer;
    }

    private Project retrieveProjectInfo(Long projectId, String clientType) {
        switch (clientType.toLowerCase()) {
            case "discovery": return developerDiscoveryClient.getProject(projectId);
            case "rest":      return developerRestClient.getProject(projectId);
            case "feign":     return developerFeignClient.getProject(projectId);
            default:          return null;
        }
    }
}
