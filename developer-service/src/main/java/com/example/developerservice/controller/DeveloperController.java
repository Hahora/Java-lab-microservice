package com.example.developerservice.controller;

import com.example.developerservice.model.Developer;
import com.example.developerservice.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    // Доступен USER и ADMIN
    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable Long id) {
        Developer developer = developerService.getDeveloperById(id);
        return developer != null ? ResponseEntity.ok(developer) : ResponseEntity.notFound().build();
    }

    // Доступен USER и ADMIN — показывает текущего пользователя
    @GetMapping("/whoami")
    public ResponseEntity<String> whoami(Authentication authentication) {
        return ResponseEntity.ok(String.format(
                "Пользователь: %s\nРоли: %s",
                authentication.getName(),
                authentication.getAuthorities()));
    }

    // Только ADMIN
    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.createDeveloper(developer));
    }

    // Только ADMIN
    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.updateDeveloper(id, developer));
    }

    // Только ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeveloper(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.deleteDeveloper(id));
    }
}
