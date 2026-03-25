package com.example.developerservice.controller;

import com.example.developerservice.model.Developer;
import com.example.developerservice.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @Value("${greeting.message:Привет!}")
    private String greetingMessage;

    @Value("${app.name:Developer Service}")
    private String appName;

    @GetMapping("/config")
    public ResponseEntity<String> getConfig() {
        return ResponseEntity.ok(String.format("Приложение: %s\nСообщение: %s", appName, greetingMessage));
    }

    // Базовый GET
    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable Long id) {
        Developer developer = developerService.getDeveloperById(id);
        return developer != null ? ResponseEntity.ok(developer) : ResponseEntity.notFound().build();
    }

    // GET с вызовом project-service через указанный clientType: discovery / rest / feign
    @GetMapping("/{id}/{clientType}")
    public ResponseEntity<Developer> getDeveloperWithProject(
            @PathVariable Long id,
            @PathVariable String clientType) {
        Developer developer = developerService.getDeveloperWithProject(id, clientType);
        return developer != null ? ResponseEntity.ok(developer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.createDeveloper(developer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.updateDeveloper(id, developer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeveloper(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.deleteDeveloper(id));
    }
}
