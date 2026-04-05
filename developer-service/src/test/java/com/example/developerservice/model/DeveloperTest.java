package com.example.developerservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Тестирование модели данных Developer
public class DeveloperTest {

    // Тест проверяет корректность геттеров и сеттеров
    @Test
    public void testDeveloperEntity() {
        // Создаём новый объект Developer
        Developer developer = new Developer();

        // Устанавливаем значения полей
        developer.setId(1L);
        developer.setName("Ivan Petrov");
        developer.setRating(4.5);

        // Проверяем, что значения установлены корректно
        assertEquals(1L, developer.getId());
        assertEquals("Ivan Petrov", developer.getName());
        assertEquals(4.5, developer.getRating());
    }

    // Тест проверяет конструктор с параметрами
    @Test
    public void testDeveloperConstructor() {
        Developer developer = new Developer(2L, "Anna Smirnova", 4.8);

        assertEquals(2L, developer.getId());
        assertEquals("Anna Smirnova", developer.getName());
        assertEquals(4.8, developer.getRating());
    }

    // Тест проверяет конструктор по умолчанию
    @Test
    public void testDeveloperDefaultConstructor() {
        Developer developer = new Developer();

        assertNull(developer.getId());
        assertNull(developer.getName());
        assertNull(developer.getRating());
    }

    // Тест проверяет метод toString
    @Test
    public void testDeveloperToString() {
        Developer developer = new Developer(1L, "Ivan Petrov", 4.5);
        String result = developer.toString();

        assertTrue(result.contains("Ivan Petrov"));
        assertTrue(result.contains("4.5"));
    }
}
