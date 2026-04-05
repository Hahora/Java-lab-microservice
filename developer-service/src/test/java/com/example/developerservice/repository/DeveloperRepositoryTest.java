package com.example.developerservice.repository;

import com.example.developerservice.model.Developer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest настраивает тестовый контекст с H2 для JPA-репозиториев
@DataJpaTest
public class DeveloperRepositoryTest {

    // @Autowired внедряет репозиторий в тестовый класс
    @Autowired
    private DeveloperRepository developerRepository;

    // Тест проверяет сохранение и поиск по ID
    @Test
    public void testSaveAndFindById() {
        Developer developer = new Developer();
        developer.setName("Ivan Petrov");
        developer.setRating(4.5);

        // Сохраняем объект в репозиторий
        Developer saved = developerRepository.save(developer);

        // Ищем объект по ID
        Optional<Developer> found = developerRepository.findById(saved.getId());

        // Проверяем, что объект найден
        assertTrue(found.isPresent());
        assertEquals("Ivan Petrov", found.get().getName());
        assertEquals(4.5, found.get().getRating());
    }

    // Тест проверяет поиск по имени
    @Test
    public void testFindByName() {
        Developer dev1 = new Developer();
        dev1.setName("Anna Smirnova");
        dev1.setRating(4.8);

        Developer dev2 = new Developer();
        dev2.setName("Petr Ivanov");
        dev2.setRating(3.5);

        developerRepository.save(dev1);
        developerRepository.save(dev2);

        // Вызов метода репозитория
        List<Developer> result = developerRepository.findByName("Anna Smirnova");

        // Проверка результата
        assertEquals(1, result.size());
        assertEquals("Anna Smirnova", result.get(0).getName());
    }

    // Тест проверяет поиск разработчиков с рейтингом >= порога
    @Test
    public void testFindByRatingGreaterThanEqual() {
        Developer dev1 = new Developer();
        dev1.setName("Senior Dev");
        dev1.setRating(4.5);

        Developer dev2 = new Developer();
        dev2.setName("Junior Dev");
        dev2.setRating(2.0);

        developerRepository.save(dev1);
        developerRepository.save(dev2);

        List<Developer> result = developerRepository.findByRatingGreaterThanEqual(4.0);

        assertEquals(1, result.size());
        assertEquals("Senior Dev", result.get(0).getName());
    }

    // Тест проверяет, что возвращается пустой список, если разработчик не найден
    @Test
    public void testFindByName_NotFound() {
        List<Developer> result = developerRepository.findByName("Unknown");
        assertTrue(result.isEmpty());
    }

    // Тест проверяет удаление разработчика
    @Test
    public void testDelete() {
        Developer developer = new Developer();
        developer.setName("To Delete");
        developer.setRating(3.0);
        Developer saved = developerRepository.save(developer);

        developerRepository.deleteById(saved.getId());

        Optional<Developer> found = developerRepository.findById(saved.getId());
        assertFalse(found.isPresent());
    }
}
