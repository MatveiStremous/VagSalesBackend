package com.example.vagsalesbackend.repositories;

import com.example.vagsalesbackend.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
}
