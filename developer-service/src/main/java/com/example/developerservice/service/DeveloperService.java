package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import com.example.developerservice.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public Developer getDeveloperById(Long id) {
        return developerRepository.findById(id).orElse(null);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    public Developer createDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public Developer updateDeveloper(Long id, Developer developer) {
        developer.setId(id);
        return developerRepository.save(developer);
    }

    public String deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
        return "Разработчик с id " + id + " удалён";
    }
}
