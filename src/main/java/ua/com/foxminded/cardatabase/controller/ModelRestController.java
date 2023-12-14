package ua.com.foxminded.cardatabase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Get all models from DB")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned all models if exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Model.class)) }) })
    public ResponseEntity<List<Model>> getAllModels() {
        List<Model> models = new ArrayList<>();
        models = modelService.getAllModels();

        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping(getSingleModel + "{id}")
    @Operation(summary = "Get single model by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned model with given id",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Model.class)) }),
        @ApiResponse(responseCode = "404", description = "No model with given id found",
            content = @Content) })
    public ResponseEntity<Model> getSingleModel(@PathVariable("id") Integer modelId) {
        try {
            Model model = modelService.getModel(modelId).get();

            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postModel)
    @Operation(summary = "Create new model", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New model created",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Model.class)) }) })
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model addedModel = modelService.addModel(model);

        return new ResponseEntity<>(addedModel, HttpStatus.CREATED);
    }

    @PutMapping(editModel + "{id}")
    @Operation(summary = "Edit model found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found model with given id and edited",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Model.class)) }),
        @ApiResponse(responseCode = "404", description = "No model with given id found",
            content = @Content) })
    public ResponseEntity<Model> updateModel(@PathVariable("id") Integer id, @RequestBody Model model) {
        if (modelService.getModel(id).isPresent()) {
            model.setId(id);

            return new ResponseEntity<>(modelService.addModel(model), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(editModel +  "{id}")
    @Operation(summary = "Delete model found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Model found by id and deleted",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Model.class)) }),
        @ApiResponse(responseCode = "500", description = "No model with given id found",
            content = @Content) })
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable("id") Integer id) {
        try {
            modelService.deleteModel(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
