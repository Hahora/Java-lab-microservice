package com.example.developerservice.controller;

import com.example.developerservice.config.SecurityConfig;
import com.example.developerservice.service.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @Import(SecurityConfig.class) подключает конфигурацию безопасности
@WebMvcTest(DeveloperController.class)
@Import(SecurityConfig.class)
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperService developerService;

    // Тест: неавторизованный запрос GET → 401 Unauthorized
    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/api/developers/1"))
                .andExpect(status().isUnauthorized());
    }

    // Тест: авторизованный USER может выполнить GET-запрос
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testAuthorizedAccess_User() throws Exception {
        mockMvc.perform(get("/api/developers/1"))
                .andExpect(status().isNotFound()); // 404 т.к. mock возвращает null
    }

    // Тест: USER не может выполнить POST-запрос → 403 Forbidden
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testForbiddenAccess_UserCannotPost() throws Exception {
        mockMvc.perform(post("/api/developers")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\",\"rating\":3.0}"))
                .andExpect(status().isForbidden());
    }

    // Тест: USER не может выполнить DELETE-запрос → 403 Forbidden
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testForbiddenAccess_UserCannotDelete() throws Exception {
        mockMvc.perform(delete("/api/developers/1"))
                .andExpect(status().isForbidden());
    }

    // Тест: ADMIN может выполнить POST-запрос
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminCanPost() throws Exception {
        mockMvc.perform(post("/api/developers")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Dev\",\"rating\":4.0}"))
                .andExpect(status().isOk());
    }

    // Тест: ADMIN может выполнить DELETE-запрос
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminCanDelete() throws Exception {
        mockMvc.perform(delete("/api/developers/1"))
                .andExpect(status().isOk());
    }
}
