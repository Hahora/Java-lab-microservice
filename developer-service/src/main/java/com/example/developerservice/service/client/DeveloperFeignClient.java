package com.example.developerservice.service.client;

import com.example.developerservice.model.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Способ 3: Feign — декларативный HTTP-клиент, Spring Cloud генерирует реализацию автоматически
@FeignClient("projectservice")
public interface DeveloperFeignClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/projects/{projectId}",
            consumes = "application/json")
    Project getProject(@PathVariable("projectId") Long projectId);
}
