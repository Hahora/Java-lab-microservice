package com.example.developerservice.service;

import com.example.developerservice.model.Developer;
import com.example.developerservice.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class) инициализирует моки Mockito
@ExtendWith(MockitoExtension.class)
public class DeveloperServiceTest {

    // @Mock создаёт mock-объект репозитория
    @Mock
    private DeveloperRepository developerRepository;

    // @InjectMocks внедряет моки в сервис
    @InjectMocks
    private DeveloperService developerService;

    // Тест проверяет получение разработчика по ID
    @Test
    public void testGetDeveloperById() {
        // Подготовка данных
        Developer developer = new Developer(1L, "Ivan Petrov", 4.5);

        // Мокируем вызов репозитория
        when(developerRepository.findById(1L)).thenReturn(Optional.of(developer));

        // Вызов метода сервиса
        Developer found = developerService.getDeveloperById(1L);

        // Проверка результатов
        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Ivan Petrov", found.getName());
        assertEquals(4.5, found.getRating());

        // Проверяем, что метод репозитория был вызван ровно один раз
        verify(developerRepository, times(1)).findById(1L);
    }

    // Тест проверяет, что метод возвращает null, если разработчик не найден
    @Test
    public void testGetDeveloperById_NotFound() {
        when(developerRepository.findById(99L)).thenReturn(Optional.empty());

        Developer found = developerService.getDeveloperById(99L);

        assertNull(found);
        verify(developerRepository, times(1)).findById(99L);
    }

    // Тест проверяет создание разработчика
    @Test
    public void testCreateDeveloper() {
        Developer developer = new Developer();
        developer.setName("Anna Smirnova");
        developer.setRating(4.8);

        Developer saved = new Developer(1L, "Anna Smirnova", 4.8);
        when(developerRepository.save(developer)).thenReturn(saved);

        Developer result = developerService.createDeveloper(developer);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Anna Smirnova", result.getName());

        verify(developerRepository, times(1)).save(developer);
    }

    // Тест проверяет удаление разработчика
    @Test
    public void testDeleteDeveloper() {
        doNothing().when(developerRepository).deleteById(1L);

        String result = developerService.deleteDeveloper(1L);

        assertTrue(result.contains("1"));
        verify(developerRepository, times(1)).deleteById(1L);
    }
}
