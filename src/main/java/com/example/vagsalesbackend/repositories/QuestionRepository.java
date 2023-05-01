package com.example.vagsalesbackend.repositories;

import com.example.vagsalesbackend.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
