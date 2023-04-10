package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.dto.BrandResponse;
import com.example.vagsalesbackend.models.Brand;
import com.example.vagsalesbackend.repositories.BrandRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    public List<BrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        return modelMapper.map(brands, new TypeToken<List<BrandResponse>>() {}.getType());
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

    public void delete(int id){
        brandRepository.delete(getById(id));
    }
}
