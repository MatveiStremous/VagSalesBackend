package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.requests.RequestDTO;
import com.example.vagsalesbackend.dto.responses.RequestResponse;
import com.example.vagsalesbackend.models.Request;
import com.example.vagsalesbackend.models.enums.RequestStatus;
import com.example.vagsalesbackend.services.CarService;
import com.example.vagsalesbackend.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {
    private final RequestService requestService;
    private final CarService carService;

    @Autowired
    public RequestController(RequestService requestService, CarService carService) {
        this.requestService = requestService;
        this.carService = carService;
    }

    @PostMapping("/request")
    public ResponseEntity<String> addNewRequest(@RequestBody RequestDTO requestDTO) {
        requestService.save(convertToRequest(requestDTO));
        return ResponseEntity.ok("Заявка успешно добавлена.");
    }

    @GetMapping("/requests")
    public List<RequestResponse> getAllRequests() {
        return requestService.getAll();
    }

    @GetMapping("/requests/{email}")
    public List<RequestResponse> getAllRequestsByEmail(@PathVariable String email) {
        return requestService.getAllByUserEmail(email);
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<String> deleteRequestById(@PathVariable Integer id) {
        requestService.delete(id);
        return ResponseEntity.ok("Заявка успешно удалена.");
    }

    @PutMapping("/request/{id}")
    public ResponseEntity<String> updateRequestById(@RequestBody RequestDTO requestDTO, @PathVariable Integer id) {
        requestService.update(id, convertToRequest(requestDTO));
        return ResponseEntity.ok("Заявка успешно обновлена.");
    }

    @PutMapping("/request/status/{id}")
    public ResponseEntity<String> updateRequestStatusById(@RequestBody String status, @PathVariable Integer id) {
        Request request = requestService.getById(id);
        request.setStatus(RequestStatus.valueOf(status));
        requestService.update(id, request);
        return ResponseEntity.ok("Заявка успешно обновлена.");
    }

    private Request convertToRequest(RequestDTO requestDTO) {
        return Request.builder()
                .name(requestDTO.getName())
                .phone(requestDTO.getPhone())
                .email(requestDTO.getEmail())
                .car(carService.getById(requestDTO.getCarId()))
                .status(RequestStatus.getByPrefix(requestDTO.getStatus()))
                .build();
    }
}
