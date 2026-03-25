package com.example.projectservice.model;

public class Project {
    private Long id;
    private String title;
    private Double budget;

    // ПУСТОЙ конструктор
    public Project() {
    }

    // Конструктор со всеми полями
    public Project(Long id, String title, Double budget) {
        this.id = id;
        this.title = title;
        this.budget = budget;
    }

    // === ГЕТТЕРЫ И СЕТТЕРЫ ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", budget=" + budget +
                '}';
    }
}
