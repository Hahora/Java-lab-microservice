package com.example.projectservice.service;

import com.example.projectservice.model.Project;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProjectService {
    private final Map<Long, Project> projects = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Project getProjectById(Long id) {
        return projects.get(id);
    }

    public Project createProject(Project project) {
        long newId = counter.incrementAndGet();
        project.setId(newId);
        projects.put(project.getId(), project);
        return project;
    }

    public Project updateProject(Long id, Project updatedProject) {
        updatedProject.setId(id);
        projects.put(id, updatedProject);
        return updatedProject;
    }

    public String deleteProject(Long id) {
        projects.remove(id);
        return String.format("Проект с id %d удалён", id);
    }
}
