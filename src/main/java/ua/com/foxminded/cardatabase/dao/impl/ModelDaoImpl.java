package ua.com.foxminded.cardatabase.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.dao.ModelDao;
import ua.com.foxminded.cardatabase.dao.repository.ModelRepository;
import ua.com.foxminded.cardatabase.model.Model;
import java.util.List;
import java.util.Optional;

@Repository
public class ModelDaoImpl implements ModelDao {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelDaoImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void deleteModel(int modelId) {
        modelRepository.deleteById(modelId);
    }

    public Model addModel(Model model) {
        return modelRepository.save(model);
    }

    public Model updateModel(Model model) {
        return modelRepository.save(model);
    }

    public Optional<Model> getModel(int modelId) {
        return modelRepository.findById(modelId);
    }

    public Optional<Model> getModelByName(String name) {
        return modelRepository.getModelByName(name);
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }
}
