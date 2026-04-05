package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeveloperService {

    private static final Logger log = LoggerFactory.getLogger(DeveloperService.class);
    private final Map<Long, Developer> developers = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Developer getDeveloperById(Long id) {
        log.info("Поиск разработчика по id: {}", id);
        return developers.get(id);
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
        log.info("Обновлён разработчик: id={}", id);
        return developer;
    }

    public String deleteDeveloper(Long id) {
        developers.remove(id);
        log.warn("Удалён разработчик: id={}", id);
        return "Разработчик с id " + id + " удалён";
    }
}
