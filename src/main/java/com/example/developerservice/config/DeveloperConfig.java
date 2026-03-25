package com.example.developerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "developer.default")
public class DeveloperConfig {
    private String specialization;
    private Double minRating;
    private Integer maxProjects;

    // Геттеры и сеттеры
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Double getMinRating() { return minRating; }
    public void setMinRating(Double minRating) { this.minRating = minRating; }

    public Integer getMaxProjects() { return maxProjects; }
    public void setMaxProjects(Integer maxProjects) { this.maxProjects = maxProjects; }

    @Override
    public String toString() {
        return String.format("Default Developer: Specialization='%s', MinRating=%.1f, MaxProjects=%d",
                specialization, minRating, maxProjects);
    }
}
