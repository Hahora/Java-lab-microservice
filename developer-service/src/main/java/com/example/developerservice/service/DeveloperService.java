package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeveloperService {

    private final Map<Long, Developer> developers = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();
    private final Counter createCounter;

    public DeveloperService(MeterRegistry meterRegistry) {
        this.createCounter = Counter.builder("developer.created.total")
                .description("Количество созданных разработчиков")
                .register(meterRegistry);
    }

    public Developer getDeveloperById(Long id) {
        return developers.get(id);
    }

    public Developer createDeveloper(Developer developer) {
        long newId = counter.incrementAndGet();
        developer.setId(newId);
        developers.put(newId, developer);
        createCounter.increment();
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
