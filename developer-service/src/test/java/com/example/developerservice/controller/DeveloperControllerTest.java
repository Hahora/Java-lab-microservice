package com.example.developerservice.controller;

import com.example.developerservice.config.SecurityConfig;
import com.example.developerservice.model.Developer;
import com.example.developerservice.service.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest настраивает контекст только для веб-слоя
@WebMvcTest(DeveloperController.class)
@Import(SecurityConfig.class)
public class DeveloperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // @MockBean создаёт mock-объект сервиса
    @MockBean
    private DeveloperService developerService;

    // Тест GET /api/developers/{id} — доступен для USER
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetDeveloper_asUser() throws Exception {
        Developer developer = new Developer(1L, "Ivan Petrov", 4.5);
        when(developerService.getDeveloperById(1L)).thenReturn(developer);

        mockMvc.perform(get("/api/developers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ivan Petrov"))
                .andExpect(jsonPath("$.rating").value(4.5));
    }

    // Тест GET /api/developers/{id} — доступен для ADMIN
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetDeveloper_asAdmin() throws Exception {
        Developer developer = new Developer(1L, "Ivan Petrov", 4.5);
        when(developerService.getDeveloperById(1L)).thenReturn(developer);

        mockMvc.perform(get("/api/developers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ivan Petrov"));
    }

    // Тест POST /api/developers — только для ADMIN
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCreateDeveloper_asAdmin() throws Exception {
        Developer developer = new Developer(1L, "Anna Smirnova", 4.8);
        when(developerService.createDeveloper(any(Developer.class))).thenReturn(developer);

        mockMvc.perform(post("/api/developers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Anna Smirnova\",\"rating\":4.8}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Anna Smirnova"));
    }

    // Тест POST /api/developers — USER не имеет доступа (403 Forbidden)
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testCreateDeveloper_asUser_forbidden() throws Exception {
        mockMvc.perform(post("/api/developers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\",\"rating\":3.0}"))
                .andExpect(status().isForbidden());
    }

    // Тест GET /api/developers/{id} — разработчик не найден (404)
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetDeveloper_notFound() throws Exception {
        when(developerService.getDeveloperById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/developers/99"))
                .andExpect(status().isNotFound());
    }

    // Тест PUT /api/developers/{id} — только ADMIN
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateDeveloper_asAdmin() throws Exception {
        Developer developer = new Developer(1L, "Updated Name", 5.0);
        when(developerService.updateDeveloper(eq(1L), any(Developer.class))).thenReturn(developer);

        mockMvc.perform(put("/api/developers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Name\",\"rating\":5.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    // Тест DELETE /api/developers/{id} — только ADMIN
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteDeveloper_asAdmin() throws Exception {
        when(developerService.deleteDeveloper(1L)).thenReturn("Разработчик с id 1 удалён");

        mockMvc.perform(delete("/api/developers/1"))
                .andExpect(status().isOk());
    }
}
