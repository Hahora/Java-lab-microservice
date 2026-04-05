package com.example.projectservice.service;

import com.example.projectservice.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    private final Map<Long, Project> projects = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public ProjectService() {
        // Предзаполнение данными
        projects.put(1L, new Project(1L, "Spring Boot Microservices", 50000.0));
        projects.put(2L, new Project(2L, "Cloud Native App", 75000.0));
        projects.put(3L, new Project(3L, "Mobile Backend API", 30000.0));
    }

    public Project getProjectById(Long id) {
        log.info("Получение проекта id={}", id);
        return projects.get(id);
    }

    public Project createProject(Project project) {
        long newId = counter.incrementAndGet() + projects.size();
        project.setId(newId);
        projects.put(newId, project);
        log.info("Создан проект: id={}, title={}", newId, project.getTitle());
        return project;
    }
}
