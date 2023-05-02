package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.dto.responses.RequestResponse;
import com.example.vagsalesbackend.dto.responses.StatisticDataResponse;
import com.example.vagsalesbackend.models.Request;
import com.example.vagsalesbackend.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
                        .carName(request.getCar().getModel().getBrand().getName()+" "
                                +request.getCar().getModel().getName())
                        .phone(request.getPhone())
                        .status(request.getStatus().getPrefix())
                        .date(request.getDate())
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
        updatedRequest.setDate(requestRepository.getById(id).getDate());
        requestRepository.save(updatedRequest);
    }

    public List<StatisticDataResponse> getStatisticByDays() {
        List<RequestResponse> requests = getAll();
        Map<DayOfWeek, Long> countsByDayOfWeek = requests.stream()
                .filter(request -> request.getDate().isAfter(LocalDate.now().minusMonths(1)))
                .collect(Collectors.groupingBy(
                        request -> request.getDate().getDayOfWeek(),
                        Collectors.counting()));
        long totalCount = countsByDayOfWeek.values().stream().mapToLong(Long::longValue).sum();

        List<StatisticDataResponse> statistics = new ArrayList<>();
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            long count = countsByDayOfWeek.getOrDefault(dayOfWeek, 0L);
            double percentage = totalCount > 0 ? ((double) count / totalCount) * 100 : 0.0;
            statistics.add(new StatisticDataResponse(dayOfWeek.toString(), (int) percentage));
        }
        return statistics;
    }

    public List<StatisticDataResponse> getStatisticByCars() {
        List<Request> requests = requestRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        Map<String, Long> counts = requests.stream()
                .filter(request -> request.getDate().isAfter(LocalDate.now().minusMonths(1)))
                .collect(Collectors.groupingBy(request -> request.getCar().getModel(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingLong(Map.Entry::getValue)))
                .limit(7)
                .collect(Collectors.toMap(entry -> entry.getKey().getBrand().getName() + " " + entry.getKey().getName(), Map.Entry::getValue));

        List<StatisticDataResponse> statistics = new ArrayList<>();
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue().intValue();
            statistics.add(new StatisticDataResponse(key, value));
        }
        return statistics;
    }

    public void delete(int id) {
        requestRepository.delete(getById(id));
    }
}
