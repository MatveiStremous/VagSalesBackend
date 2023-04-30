package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.ModelDTO;
import com.example.vagsalesbackend.dto.ModelResponse;
import com.example.vagsalesbackend.models.Model;
import com.example.vagsalesbackend.services.BrandService;
import com.example.vagsalesbackend.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModelController {
    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @PostMapping("/model")
    public ResponseEntity<String> addNewModel(@RequestBody ModelDTO modelDTO) {
        modelService.save(convertToModel(modelDTO));
        return ResponseEntity.ok("Модель успешно удалена.");
    }

    @GetMapping("/models")
    public List<ModelResponse> getAllModels(){
        return modelService.getAll();
    }

    @GetMapping("/models/{brandId}")
    public List<ModelResponse> getAllModels(@PathVariable Integer brandId){
        return modelService.getAllByBrandId(brandId);
    }

    @DeleteMapping("/model/{id}")
    public ResponseEntity<String> deleteModelById(@PathVariable Integer id){
        modelService.delete(id);
        return ResponseEntity.ok("Модель успешно удалена.");
    }

    @PutMapping("/model/{id}")
    public ResponseEntity<String> updateModelById(@RequestBody ModelDTO modelDTO, @PathVariable Integer id){
        modelService.update(id, convertToModel(modelDTO));
        return ResponseEntity.ok("Модель успешно обновлена.");
    }


    private Model convertToModel(ModelDTO modelDTO){
        return Model.builder()
                .name(modelDTO.getName())
                .description(modelDTO.getDescription())
                .brand(brandService.getById(modelDTO.getBrandId()))
                .build();
    }
}
