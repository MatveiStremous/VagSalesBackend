package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.models.Brand;
import com.example.vagsalesbackend.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand getById(int id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);
        return foundBrand.orElse(null);
    }

    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    public void update(int id, Brand updatedBrand) {
        updatedBrand.setId(id);
        brandRepository.save(updatedBrand);
    }
}
