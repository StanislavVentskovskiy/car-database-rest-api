package ua.com.foxminded.cardatabase.service;

import ua.com.foxminded.cardatabase.model.Model;
import java.util.List;
import java.util.Optional;

public interface ModelService {

    void deleteModel(int modelId);

    Model addModel(Model model);

    Optional<Model> updateModel(Model model);

    Optional<Model> getModel(int modelId);

    Optional<Model> getModelByName(String name);

    List<Model> getAllModels();
}
