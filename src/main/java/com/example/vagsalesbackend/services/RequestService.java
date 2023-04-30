package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.dto.responses.RequestResponse;
import com.example.vagsalesbackend.models.Request;
import com.example.vagsalesbackend.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<RequestResponse> getAll() {
        List<Request> requests = requestRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return requests
                .stream()
                .map(request -> RequestResponse.builder()
                        .id(request.getId())
                        .name(request.getName())
                        .email(request.getEmail())
                        .carId(request.getCar().getId())
                        .phone(request.getPhone())
                        .status(request.getStatus().getPrefix())
                        .build())
                .toList();
    }

    public List<RequestResponse> getAllByUserEmail(String email) {
        List<RequestResponse> requests = this.getAll()
                .stream()
                .filter(requestResponse -> requestResponse.getEmail().equals(email))
                .toList();
        return requests;
    }

    public Request getById(int id) {
        Optional<Request> foundRequest = requestRepository.findById(id);
        return foundRequest.orElse(null);
    }

    public void save(Request Request) {
        requestRepository.save(Request);
    }

    public void update(int id, Request updatedRequest) {
        updatedRequest.setId(id);
        requestRepository.save(updatedRequest);
    }

    public void delete(int id) {
        requestRepository.delete(getById(id));
    }
}
