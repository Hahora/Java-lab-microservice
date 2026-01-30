package com.example.gameservice.model;

public class Game {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String genre;
    private String systemRequirements;

    // ПУСТОЙ конструктор
    public Game() {
    }

    // Конструктор со всеми полями
    public Game(Long id, String title, Integer releaseYear, String genre, String systemRequirements) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
    }

    // === ГЕТТЕРЫ И СЕТТЕРЫ (ОБЯЗАТЕЛЬНО!) ===

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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(String systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", systemRequirements='" + systemRequirements + '\'' +
                '}';
    }
}