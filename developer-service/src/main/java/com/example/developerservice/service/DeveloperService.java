package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeveloperService {

    private static final Logger log = LoggerFactory.getLogger(DeveloperService.class);
    private final Map<Long, Developer> developers = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RestTemplate restTemplate;

    public Developer getDeveloperById(Long id) {
        log.info("Получение разработчика id={}", id);
        Developer developer = developers.get(id);
        if (developer != null) {
            // Вызов project-service для получения информации о проекте (демонстрация трассировки)
            try {
                String projectInfo = restTemplate.getForObject(
                        "http://localhost:8081/api/projects/" + id, String.class);
                developer.setProjectName(projectInfo);
                log.info("Получен проект для разработчика {}: {}", id, projectInfo);
            } catch (Exception e) {
                log.warn("Не удалось получить информацию о проекте для разработчика {}", id);
            }
        }
        return developer;
    }

    public Developer createDeveloper(Developer developer) {
        long newId = counter.incrementAndGet();
        developer.setId(newId);
        developers.put(newId, developer);
        log.info("Создан разработчик: id={}, name={}", newId, developer.getName());
        return developer;
    }

    public Developer updateDeveloper(Long id, Developer developer) {
        developer.setId(id);
        developers.put(id, developer);
        return developer;
    }

    public String deleteDeveloper(Long id) {
        developers.remove(id);
        return "Разработчик с id " + id + " удалён";
    }
}
