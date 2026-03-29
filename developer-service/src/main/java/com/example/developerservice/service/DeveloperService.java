package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeveloperService {
    private final Map<Long, Developer> developers = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

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
}
