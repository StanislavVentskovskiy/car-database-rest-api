package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Model;
import ua.com.foxminded.cardatabase.service.impl.ModelServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ModelRestController {
    private final ModelServiceImpl modelService;

    @Autowired
    private ModelRestController(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/models/")
    public ResponseEntity<List<Model>> getAllModels() {
        List<Model> models = new ArrayList<>();
        models = modelService.getAllModels();

        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<Model> getSingleModel(@PathVariable("id") Integer modelId) {
        try {
            Model model = modelService.getModel(modelId).get();

            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/models")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model addedModel = modelService.addModel(model);

        return new ResponseEntity<>(addedModel, HttpStatus.CREATED);
    }

    @PutMapping("/models/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Model> updateModel(@PathVariable("id") Integer id, @RequestBody Model model) {
        Optional<Model> modelData = modelService.getModel(id);

        if(modelData.isPresent()) {
            Model updateModel = modelData.get();
            updateModel.setName(model.getName());

            return new ResponseEntity<>(modelService.addModel(updateModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/models/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable("id") Integer id) {
        try {
            modelService.deleteModel(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
