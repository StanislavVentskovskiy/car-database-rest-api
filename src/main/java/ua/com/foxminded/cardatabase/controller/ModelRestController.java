package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Model;
import ua.com.foxminded.cardatabase.service.impl.ModelServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static ua.com.foxminded.cardatabase.url.UrlContainer.*;

@RestController
public class ModelRestController {
    private final ModelServiceImpl modelService;

    @Autowired
    public ModelRestController(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping(getAllModels)
    public ResponseEntity<List<Model>> getAllModels() {
        List<Model> models = new ArrayList<>();
        models = modelService.getAllModels();

        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping(getSingleModel + "{id}")
    public ResponseEntity<Model> getSingleModel(@PathVariable("id") Integer modelId) {
        try {
            Model model = modelService.getModel(modelId).get();

            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postModel)
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model addedModel = modelService.addModel(model);

        return new ResponseEntity<>(addedModel, HttpStatus.CREATED);
    }

    @PutMapping(editModel + "{id}")
    public ResponseEntity<Model> updateModel(@PathVariable("id") Integer id, @RequestBody Model model) {
        if (modelService.getModel(id).isPresent()) {
            model.setId(id);

            return new ResponseEntity<>(modelService.addModel(model), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(editModel +  "{id}")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable("id") Integer id) {
        try {
            modelService.deleteModel(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
