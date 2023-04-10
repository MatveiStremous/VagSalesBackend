package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.models.Car;
import com.example.vagsalesbackend.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAll() {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Car getById(int id) {
        Optional<Car> foundCar = carRepository.findById(id);
        return foundCar.orElse(null);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public void update(int id, Car updatedCar) {
        updatedCar.setId(id);
        carRepository.save(updatedCar);
    }
}
