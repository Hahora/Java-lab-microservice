package com.example.developerservice.repository;

import com.example.developerservice.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<Developer> findByName(String name);
    List<Developer> findByRatingGreaterThanEqual(Double rating);
}
