package ua.com.foxminded.cardatabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.cardatabase.dao.impl.ModelDaoImpl;
import ua.com.foxminded.cardatabase.model.Model;
import ua.com.foxminded.cardatabase.service.ModelService;
import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelDaoImpl modelDao;

    @Autowired
    public ModelServiceImpl(ModelDaoImpl modelDao) {
        this.modelDao = modelDao;
    }

    public void deleteModel(int modelId) {
        modelDao.deleteModel(modelId);
    }

    public Model addModel(Model model) {
        return modelDao.addModel(model);
    }

    public Model updateModel(Model model) {
        Model other = modelDao.getModel(model.getId()).get();
        if (model.getName() != null) {
            other.setName(model.getName());
        }

        return modelDao.updateModel(other);
    }

    public Optional<Model> getModel(int modelId) {
        return modelDao.getModel(modelId);
    }

    public Optional<Model> getModelByName(String name) {
        return modelDao.getModelByName(name);
    }

    public List<Model> getAllModels() {
        return modelDao.getAllModels();
    }
}
