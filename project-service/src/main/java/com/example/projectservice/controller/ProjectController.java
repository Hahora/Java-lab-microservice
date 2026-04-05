package com.example.projectservice.controller;

import com.example.projectservice.model.Project;
import com.example.projectservice.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    // Micrometer Tracing автоматически добавит TraceID в логи
    @GetMapping("/{id}")
    public ResponseEntity<String> getProject(@PathVariable Long id) {
        log.info("Получен запрос для project id: {}", id);
        Project project = projectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project.getTitle());
        }
        return ResponseEntity.ok("Проект не найден");
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        log.info("Создание проекта: {}", project.getTitle());
        return ResponseEntity.ok(projectService.createProject(project));
    }
}
