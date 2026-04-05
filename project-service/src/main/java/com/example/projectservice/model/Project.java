package com.example.projectservice.model;

public class Project {
    private Long id;
    private String title;
    private Double budget;

    public Project() {}
    public Project(Long id, String title, Double budget) {
        this.id = id; this.title = title; this.budget = budget;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
}
