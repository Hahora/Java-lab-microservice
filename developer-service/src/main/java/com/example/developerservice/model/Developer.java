package com.example.developerservice.model;

public class Developer {
    private Long id;
    private String name;
    private Double rating;
    private Long projectId;
    private String comment;

    // ПУСТОЙ конструктор
    public Developer() {
    }

    // Конструктор со всеми полями
    public Developer(Long id, String name, Double rating, Long projectId) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.projectId = projectId;
    }

    // === ГЕТТЕРЫ И СЕТТЕРЫ ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", projectId=" + projectId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
