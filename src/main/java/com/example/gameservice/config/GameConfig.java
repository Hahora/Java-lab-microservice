package com.example.gameservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "game.default")
public class GameConfig {
    private String genre;
    private Double price;
    private Integer minAge;

    // Геттеры и сеттеры
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }

    @Override
    public String toString() {
        return String.format("Default Game: Genre='%s', Price=$%.2f, Age=%d+",
                genre, price, minAge);
    }
}