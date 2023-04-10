package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.models.Model;
import com.example.vagsalesbackend.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getAll() {
        return modelRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Model getById(int id) {
        Optional<Model> foundModel = modelRepository.findById(id);
        return foundModel.orElse(null);
    }

    public void save(Model model) {
        modelRepository.save(model);
    }

    public void update(int id, Model updatedModel) {
        updatedModel.setId(id);
        modelRepository.save(updatedModel);
    }
}
