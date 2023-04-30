package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.CarDTO;
import com.example.vagsalesbackend.dto.CarEnumsResponse;
import com.example.vagsalesbackend.dto.CarInfoResponse;
import com.example.vagsalesbackend.dto.CarResponse;
import com.example.vagsalesbackend.models.Car;
import com.example.vagsalesbackend.models.enums.BodyType;
import com.example.vagsalesbackend.models.enums.FuelType;
import com.example.vagsalesbackend.models.enums.Transmission;
import com.example.vagsalesbackend.services.CarService;
import com.example.vagsalesbackend.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
public class CarController {
    private final CarService carService;
    private final ModelService modelService;

    @Autowired
    public CarController(CarService carService, ModelService modelService) {
        this.carService = carService;
        this.modelService = modelService;
    }

    @PostMapping("/car")
    public ResponseEntity<String> addNewCar(@RequestBody CarDTO carDTO) {
        carService.save(convertToCar(carDTO));
        return ResponseEntity.ok("Машина успешно добавлена.");
    }

    @GetMapping("/cars")
    public List<CarResponse> getAllCars() {
        return carService.getAll();
    }

    @GetMapping("/carInfo/{id}")
    public CarInfoResponse getCarInfo(@PathVariable Integer id) {
        return convertToCarInfoResponse(carService.getById(id));
    }

    @GetMapping("/carEnums")
    public CarEnumsResponse getAllCarEnums() {
        return new CarEnumsResponse();
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable Integer id) {
        carService.delete(id);
        return ResponseEntity.ok("Машина успешно удалена.");
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<String> updateCarById(@RequestBody CarDTO carDTO, @PathVariable Integer id) {
        carService.update(id, convertToCar(carDTO));
        return ResponseEntity.ok("Машина успешно обновлена.");
    }

    private Car convertToCar(CarDTO carDTO) {
        return Car.builder()
                .description(carDTO.getDescription())
                .engineCapacity(carDTO.getEngineCapacity())
                .year(Year.parse(carDTO.getYear()))
                .imageURL(carDTO.getImageURL())
                .bodyType(BodyType.getByPrefix(carDTO.getBodyType()))
                .fuelType(FuelType.getByPrefix(carDTO.getFuelType()))
                .transmission(Transmission.getByPrefix(carDTO.getTransmission()))
                .model(modelService.getById(carDTO.getModelId()))
                .build();
    }

    private CarInfoResponse convertToCarInfoResponse(Car car) {
        return CarInfoResponse.builder()
                .id(car.getId())
                .year(String.valueOf(car.getYear()))
                .engineCapacity(car.getEngineCapacity())
                .bodyType(car.getBodyType().getPrefix())
                .transmission(car.getTransmission().getPrefix())
                .fuelType(car.getFuelType().getPrefix())
                .brandName(car.getModel().getBrand().getName())
                .modelName(car.getModel().getName())
                .description(car.getDescription())
                .modelDescription(car.getModel().getDescription())
                .imageURL(car.getImageURL())
                .build();
    }
}
