package com.example.vagsalesbackend.repositories;

import com.example.vagsalesbackend.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> getRequestsByEmail(String email);
}
