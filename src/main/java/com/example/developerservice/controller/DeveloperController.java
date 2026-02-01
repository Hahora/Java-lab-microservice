package com.example.developerservice.controller;

import com.example.developerservice.model.Developer;
import com.example.developerservice.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable Long id) {
        Developer developer = developerService.getDeveloperById(id);
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