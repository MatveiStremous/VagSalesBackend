package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.BrandDTO;
import com.example.vagsalesbackend.dto.BrandResponse;
import com.example.vagsalesbackend.models.Brand;
import com.example.vagsalesbackend.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/brand")
    public ResponseEntity<String> addNewBrand(@RequestBody BrandDTO brandDTO) {
        brandService.save(convertToBrand(brandDTO));
        return ResponseEntity.ok("Бренд успешно добавлен.");
    }

    @GetMapping("/brands")
    public List<BrandResponse> getAllBrands(){
        return brandService.getAll();
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<String> deleteBrandById(@PathVariable Integer id){
        brandService.delete(id);
        return ResponseEntity.ok("Бренд успешно удалён.");
    }

    @PutMapping("/brand/{id}")
    public ResponseEntity<String> updateBrandById(@RequestBody BrandDTO brandDTO, @PathVariable Integer id){
        brandService.update(id, convertToBrand(brandDTO));
        return ResponseEntity.ok("Бренд успешно обновлён.");
    }


    private Brand convertToBrand(BrandDTO brandDTO){
        return this.modelMapper.map(brandDTO, Brand.class);
    }
}
