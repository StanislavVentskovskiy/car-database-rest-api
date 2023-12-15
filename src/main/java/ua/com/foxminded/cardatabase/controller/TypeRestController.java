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
import ua.com.foxminded.cardatabase.model.Type;
import ua.com.foxminded.cardatabase.service.impl.TypeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static ua.com.foxminded.cardatabase.url.UrlContainer.*;

@RestController
public class TypeRestController {
    private final TypeServiceImpl typeService;

    @Autowired
    public TypeRestController(TypeServiceImpl typeService) {
        this.typeService = typeService;
    }

    @GetMapping(getAllTypes)
    @Operation(summary = "Get all types from DB")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned all types if exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Type.class)) }) })
    public ResponseEntity<List<Type>> getAllTypes() {
        List<Type> types = new ArrayList<>();
        types = typeService.getAllTypes();

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping(getSingleType + "{id}")
    @Operation(summary = "Get single type by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned type with given id",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Type.class)) }),
        @ApiResponse(responseCode = "404", description = "No type with given id found",
            content = @Content) })
    public ResponseEntity<Type> getSingleType(@PathVariable("id") Integer typeId) {
        try {
            Type type = typeService.getType(typeId).get();

            return new ResponseEntity<>(type, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postType)
    @Operation(summary = "Create new type", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New type created",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Type.class)) }) })
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        Type addedType = typeService.addType(type);

        return new ResponseEntity<>(addedType, HttpStatus.CREATED);
    }

    @PutMapping(editType + "{id}")
    @Operation(summary = "Delete type found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Type found by id and deleted or not exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Type.class)) }) })
    public ResponseEntity<Type> updateType(@PathVariable("id") Integer id, @RequestBody Type type) {
        if (typeService.getType(id).isPresent()) {
            type.setId(id);

            return new ResponseEntity<>(typeService.updateType(type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
